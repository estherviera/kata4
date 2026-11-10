package software.ulpgc.katas.infrastructure;

import io.javalin.Javalin;
import io.javalin.config.JavalinConfig;
import io.javalin.http.Context;
import org.jetbrains.annotations.NotNull;
import software.ulpgc.katas.architecture.domain.CalculateFibonacciSeriesUseCase;
import software.ulpgc.katas.architecture.ports.Output;
import software.ulpgc.katas.infrastructure.adapters.JavalinContextInput;
import software.ulpgc.katas.infrastructure.adapters.JsonSeriesSerializer;

public class Main {
    public static void main(String[] args) {
        Javalin.create(Main::init).start(8081);
    }

    private static void init(JavalinConfig config) {
        config.routes.get("/fibonacci", Main::fibonacci);
    }

    private static void fibonacci(@NotNull Context context) {
        Output output = new CalculateFibonacciSeriesUseCase(
                new JsonSeriesSerializer()
        ).execute(new JavalinContextInput(context));

        context.contentType("application/json");
        context.result(output.result());
    }
}
