package software.ulpgc.katas.architecture.ports;

import software.ulpgc.katas.architecture.model.Series;

public interface SeriesSerializer {
    String serialize(Series series);
}
