import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description: 正式表达式工具类
 * @author: zhangr
 * @date: 2021/5/19 13:54
 */
public class RegexUtils {

    private RegexUtils() {}

    /**
     * 正则表达式匹配
     * @param regex 正则表达式
     * @param soap 内容
     * @return 匹配结果：true-匹配，false-不匹配
     */
    public static boolean match(String regex, String soap) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(soap);
        return matcher.matches();
    }

    /**
     * 正则表达式截取
     * @param regex 正则表达式
     * @param soap 内容
     * @return 截取所有匹配的字符串
     */
    public static List<String> substring(String regex, String soap) {
        List<String> list = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(soap);
        while (m.find()) {
            list.add(m.group(0));
        }
        return list;
    }

    /**
     * 正则表达式截取，若匹配到多个的话就返回第一个
     * @param regex 正则表达式
     * @param soap 内容
     * @return 截取第一个匹配的字符串
     */
    public static String substringFirst(String regex, String soap) {
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(soap);
        if(m.find()){
            return m.group(0);
        }
        return "";
    }

    /**
     * 数字校验
     */
    public static boolean isNumeric(String text) {
		String regex = "^\\d*\\.?\\d*$";
		return match(regex, text);
	}

    /**
     * 手机号码校验
     */
    public static boolean isPhone(String text) {
        String regex = "^1[345789]\\d{9}$";
        return match(regex, text);
    }
}
