package software.ulpgc.katas.architecture.ports;

import java.lang.reflect.Parameter;

public interface Input {
    Parameter get(String n);
    interface Parameter{
        String asString();
        int asInteger();
    }
}
