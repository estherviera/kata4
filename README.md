# Kata 4 – Servicio HTTP de la serie de Fibonacci en Java

**Asignatura:** Ingeniería de Software 2  
**Universidad:** Universidad de Las Palmas de Gran Canaria (ULPGC)  
**Repositorio:** https://github.com/estherviera/kata4  
**Rama principal de entrega:** `master`

## 1. Objetivo

El objetivo de esta kata es implementar un servicio HTTP en Java que calcule la serie de Fibonacci y devuelva sus términos en formato JSON.

La práctica permite reforzar la separación de responsabilidades mediante una arquitectura hexagonal, diferenciando el modelo de datos, el caso de uso, los puertos y los adaptadores que conectan la aplicación con el servidor HTTP y la serialización JSON.

## 2. Tecnologías utilizadas

- Java (JDK 22, según `pom.xml`).
- IntelliJ IDEA como entorno de desarrollo.
- Maven para la gestión del proyecto.
- Git y GitHub para el control de versiones.
- Javalin 7.2.4 para crear el servidor HTTP y gestionar las peticiones.
- Gson 2.15.0 para serializar la serie a JSON.
- SLF4J Simple 2.0.20 para el registro de mensajes.

## 3. Estructura del proyecto

El proyecto está organizado dentro del paquete `software.ulpgc.katas`, siguiendo una arquitectura hexagonal que permite separar el modelo, la lógica de la aplicación, las interfaces y los adaptadores externos.

### Organización de paquetes

```text
software.ulpgc.katas
│
├── architecture
│   ├── model
│   │   └── Series.java
│   │
│   ├── domain
│   │   ├── UserCase.java
│   │   └── CalculateFibonacciSeriesUseCase.java
│   │
│   └── ports
│       ├── Input.java
│       ├── Output.java
│       └── SeriesSerializer.java
│
└── infrastructure
    ├── Main.java
    └── adapters
        ├── JavalinContextInput.java
        └── JsonSeriesSerializer.java
```

### Descripción de los componentes

| Paquete | Clase o interfaz | Responsabilidad |
|---|---|---|
| `architecture.model` | `Series` | Almacena los términos de la serie y permite añadir valores y consultar su longitud, primer elemento y último elemento. |
| `architecture.domain` | `UserCase` | Define el contrato de ejecución de los casos de uso. |
| `architecture.domain` | `CalculateFibonacciSeriesUseCase` | Obtiene el número de términos, calcula la serie de Fibonacci y delega la serialización del resultado. |
| `architecture.ports` | `Input` | Define el acceso a los parámetros de entrada como texto o como número entero. |
| `architecture.ports` | `Output` | Define el acceso al resultado textual del caso de uso. |
| `architecture.ports` | `SeriesSerializer` | Define la interfaz para serializar una serie sin depender de una biblioteca concreta. |
| `infrastructure.adapters` | `JavalinContextInput` | Adapta los parámetros de consulta de Javalin al puerto `Input`. |
| `infrastructure.adapters` | `JsonSeriesSerializer` | Convierte una serie a JSON utilizando Gson. |
| `infrastructure` | `Main` | Inicia el servidor, registra la ruta `/fibonacci` y envía la respuesta con el tipo de contenido `application/json`. |

### Funcionamiento general

```text
     Petición HTTP GET /fibonacci?n=10
                     │
                     ▼
            JavalinContextInput
          (Obtiene el parámetro n)
                     │
                     ▼
     CalculateFibonacciSeriesUseCase
         (Calcula la serie de Fibonacci)
                     │
                     ▼
                   Series
           (Almacena los términos)
                     │
                     ▼
           JsonSeriesSerializer
            (Convierte a JSON)
                     │
                     ▼
             Respuesta HTTP JSON
```

El caso de uso utiliza los puertos `Input`, `Output` y `SeriesSerializer` para mantener separada la lógica del cálculo de los detalles de Javalin y Gson. La clase `Main` conecta los componentes y gestiona la respuesta HTTP.

## 4. Compilación y ejecución

### Requisitos

Tener instalado JDK 22 y Maven, conexión a Internet para descargar las dependencias y el puerto `8081` disponible.

### Pasos

1. Clonar el repositorio:

   ```bash
   git clone https://github.com/estherviera/kata4.git
   ```

2. Abrir el proyecto en IntelliJ IDEA.
3. Cargar el proyecto Maven mediante el archivo `pom.xml` y seleccionar JDK 22.
4. Compilar el proyecto desde su carpeta raíz:

   ```bash
   mvn compile
   ```

5. Ejecutar desde IntelliJ IDEA la clase principal `software.ulpgc.katas.infrastructure.Main`.
6. Con el servidor en marcha, abrir en el navegador:

   ```text
   http://localhost:8081/fibonacci?n=10
   ```

La aplicación utiliza el parámetro `n` para indicar cuántos términos de la serie se deben calcular. Para **10 términos**, la respuesta esperada es:

```json
{"values":[0,1,1,2,3,5,8,13,21,34]}
```

Para detener el servidor, utilizar el botón de parada de IntelliJ IDEA.

## 5. Verificación

Para comprobar el correcto funcionamiento de la aplicación, se pueden realizar los siguientes casos de verificación manual:

| Petición | Resultado esperado |
|---|---|
| `/fibonacci?n=10` | `{"values":[0,1,1,2,3,5,8,13,21,34]}` |
| `/fibonacci?n=1` | `{"values":[0]}` |
| `/fibonacci?n=0` | `{"values":[]}` |
| `/fibonacci?n=-3` | `{"values":[]}` |
| `/fibonacci` | `{"values":[0]}` |
| `/fibonacci?n=abc` | `{"values":[0]}` |

También se debe comprobar que la respuesta utiliza el tipo de contenido `application/json` y que el JSON generado puede volver a interpretarse correctamente.

Si el parámetro se omite o no puede convertirse a un entero, se utiliza `1`. Si es cero o negativo, se genera una serie vacía. El cálculo utiliza `int`, por lo que a partir de 48 términos aparecen valores desbordados.

Estos resultados se deducen del código y se presentan como guía de comprobación; no acreditan pruebas ejecutadas.

## 6. Vídeo explicativo

**Vídeo de la kata:** https://youtu.be/X91GTjuRjIg
