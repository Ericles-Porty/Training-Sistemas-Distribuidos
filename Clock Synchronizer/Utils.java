import java.time.Instant;

public class Utils {
    public static String clockFormatString(Instant instant) {
        return instant.toString().split("T")[1].substring(0, 8);
    }
}
