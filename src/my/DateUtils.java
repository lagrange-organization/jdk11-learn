import org.apache.commons.lang.time.DateFormatUtils;

import java.util.Date;

/**
 * @author szy
 * @date 2022/12/14 19:37
 */
public class DateUtils {
    public static String formatDate(Date date, Object... pattern) {
        String formatDate = null;
        if (pattern != null && pattern.length > 0) {
            formatDate = DateFormatUtils.format(date, pattern[0].toString());
        } else {
            formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
        }
        return formatDate;
    }
}
