package joce.practice.concurrency.st.utils;

import com.google.gson.Gson;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * @Author ts-jiajia.hu
 * @Date 2019/10/07
 */
public class JsonUtils {
    private static final Gson gson = new Gson();

    public static <T> String toJson(T t) {
        return gson.toJson(t);
    }

    public static <T> T toJson(InputStream stream, Class<T> klass) {
        Reader reader = new InputStreamReader(stream);
        return gson.fromJson(reader, klass);
    }

}
