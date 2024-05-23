package api.pedidos.utils;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtils {

    private static final String REGEX = "(dd-MM-yyyy)'T'(HH:mm:ss.SSSSSSXXX)' ['VV']'";

    public static String getZoneTimeWithClock() {
        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));
        return zonedDateTime.format(DateTimeFormatter.ofPattern(REGEX));
    }
}
