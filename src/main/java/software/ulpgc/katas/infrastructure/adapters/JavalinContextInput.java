package software.ulpgc.katas.infrastructure.adapters;

import software.ulpgc.katas.architecture.ports.Input;

import io.javalin.http.Context;

public class JavalinContextInput implements Input {
    private final Context context;

    public JavalinContextInput(Context context) {
        this.context = context;
    }

    @Override
    public Parameter get(String n) {
        return new Parameter() {
            @Override
            public String asString() {
                return context.queryParam(n);
            }

            @Override
            public int asInteger() {
                try{
                    return Integer.parseInt(asString());
                }catch (NumberFormatException e){
                    return 1;
                }
            }
        };
    }
}
