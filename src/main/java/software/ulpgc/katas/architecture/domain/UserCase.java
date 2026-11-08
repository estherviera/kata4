package software.ulpgc.katas.architecture.domain;

import software.ulpgc.katas.architecture.ports.Input;
import software.ulpgc.katas.architecture.ports.Output;

public interface UserCase {
    Output execute(Input input);
}
