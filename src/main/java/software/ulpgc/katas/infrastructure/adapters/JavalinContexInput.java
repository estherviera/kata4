package software.ulpgc.katas.infrastructure.adapters;

import software.ulpgc.katas.architecture.ports.Input;

import io.javalin.http.Context;
import javax.swing.text.AbstractDocument;

public class JavalinContexInput implements Input {
    private final Context context;

    public JavalinContexInput(Context context) {
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
