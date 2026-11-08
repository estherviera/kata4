package software.ulpgc.katas.architecture.model;

import java.lang.reflect.Array;
import java.util.Arrays;

public record Series(int[] values) {

    public Series(){
        this(new int[0]);
    }
    public Series put(int value){
        int[] values = Arrays.copyOf(
                this.values,
                this.values.length + 1
        );
        values[this.values.length] = value;
        return new Series(values);
    }
    public int length(){
        return values.length;
    }
    public int first(){
        return values[0];
    }
    public int last(){
        return values[length() - 1];
    }
}
