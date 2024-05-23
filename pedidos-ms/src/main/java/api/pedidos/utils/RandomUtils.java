package api.pedidos.utils;

import java.util.Random;

public class RandomUtils {

    private static final int NUMERIC = 48;
    private static final int LETTER = 122;

    public static String generateCode() {
        return new Random().ints(NUMERIC, LETTER + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(30)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
    }
}
