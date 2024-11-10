package parameters;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class Parameters {
    public static final long SEED = 11;
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 1000;
    public static final float MARGIN = 50;
    public static final Color BACKGROUND_COLOR = new Color(235);
    public static final int STARTING_WALKER_NUMBER = 1000;
    public static final float NOISE_SCALE = 1 / 200f;
    public static final float MAGNITUDE_MULTIPLIER = .05f;
    public static final float BLUE_SHIFT_AMOUNT = .01f;
    public static final float MIN_BLUE = 2.5f;
    public static final float MAX_BLUE = 30;

    /**
     * Helper method to extract the constants in order to save them to a json file
     *
     * @return a Map of the constants (name -> value)
     */
    public static Map<String, ?> toJsonMap() throws IllegalAccessException {
        Map<String, Object> map = new HashMap<>();

        Field[] declaredFields = Parameters.class.getDeclaredFields();
        for(Field field : declaredFields) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(Parameters.class));
        }

        return Collections.singletonMap(Parameters.class.getSimpleName(), map);
    }

    public record Color (float red, float green, float blue, float alpha) {
        public Color(float red, float green, float blue) {
            this(red, green, blue, 255);
        }

        public Color(float grayscale, float alpha) {
            this(grayscale, grayscale, grayscale, alpha);
        }

        public Color(float grayscale) {
            this(grayscale, 255);
        }
    }
}
