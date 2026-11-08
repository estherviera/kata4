package software.ulpgc.katas.infrastructure.adapters;

import com.google.gson.Gson;
import software.ulpgc.katas.architecture.model.Series;
import software.ulpgc.katas.architecture.ports.SeriesSerializer;

public class JsonSeriesSerializer implements SeriesSerializer {
    @Override
    public String serialize(Series series) {
        return new Gson().toJson(series);
    }
}
