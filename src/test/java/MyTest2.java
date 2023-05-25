import com.google.common.base.CaseFormat;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author szy
 * @date 2023/3/20 18:17
 */
public class MyTest2 {
    @Test
    public void name() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 15693; i++) {
            list.add(i);
        }
        this.get(list);
    }

    private void get(List list) {
        int startIndex = 0;
        int endIndex = 0;
        for (int i = 0; i < list.size() / 5000 + 1; i++) {
            endIndex = Math.min(startIndex + 5000, list.size());
            System.out.println(startIndex + "-->" + (endIndex - 1));
            startIndex = endIndex;
        }
    }

    @Test
    public void ertwer() {
        String fDxSgOrderQuality = this.doSelectSql(MyTest.Student.class, "F_DX_SG_ORDER_QUALITY");
        System.out.println(fDxSgOrderQuality);
    }

    String doSelectSql(Class<?> cls, String className) {
        List<String> fieldList = Arrays.stream(cls.getDeclaredFields())
                .map(p -> String.format(" %s as \"%s\" ", CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, p.getName()), p.getName()))
                .collect(Collectors.toList());

        return String.format(" select %s from %s where 1=1 ",
                StringUtils.join(fieldList, ", "), className);
    }


    private String getMobileOperator(String phoneNumber) {
        // 根据手机号前3位或前4位判断运营商
        String operator = "";
        if (phoneNumber.matches("^1(3[4-9]|4[7]|5[012789]|78|8[23478])\\d{8}$")) {
            // 匹配中国移动的号码段
            operator = "中国移动";
        } else if (phoneNumber.matches("^1(3[0-2]|4[5]|5[56]|66|7[156]|8[56])\\d{8}$")) {
            // 匹配中国联通的号码段
            operator = "中国联通";
        } else if (phoneNumber.matches("^1(33|49|53|7[37]|8[019])\\d{8}$")) {
            // 匹配中国电信的号码段
            operator = "中国电信";
        } else {
            operator = "未知运营商";
        }
        return operator;
    }

    @Test
    public void jksdfakl() {
        String mobileOperator = this.getMobileOperator("13164354333");
        System.out.println(mobileOperator);
    }

    @Test
    public void oweuw() {
        //如果为2，则返回1；如果为3，则返回2，使用optional
        Optional<Integer> optional = Optional.of(3);
        Integer integer = optional.map(p -> p - 1).orElse(0);
        System.out.println(integer);
    }

    @Test
    public void getDayOfWeek() {
        // 获取当前日期
        LocalDate date = LocalDate.of(2023, 5, 10);

        // 获取当前日期的周几
        DayOfWeek dayOfWeek = date.getDayOfWeek();

        // 输出周几的英文名称
        String dayName = dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        System.out.println("Today is " + dayName);
    }

    @Test
    public void ioopip() {
        System.out.println(checkTitle("ni123故障分析会双路由隐患"));
        Map<String, String> map = new HashMap<>();
        map.put("1", "a");
        map.put("2", "b");
        map.put("3", "c");
        map.put("4", "d");
        String join = String.join("|", map.values());
        System.out.println(join);
    }


    /**
     * 判断工单标题是否符合规则：产品号码+隐患来源+隐患名称
     *
     * @param title 工单标题
     * @return true符合规则，false不符合规则
     */
    public static boolean checkTitle(String title) {
        // 定义产品号码、隐患来源和隐患名称的正则表达式
        String productNumRegex = "\\d+"; // 产品号码为纯数字
        String sourceRegex = "(例行巡检|主动排查|故障分析会|故障报告)"; // 隐患来源只有以下四个枚举值
        String issueNameRegex = "(双路由隐患|双板卡隐患|双电源隐患|双上联隐患)"; // 隐患名称只有如下几个枚举值

        // 拼接完整的正则表达式
        String regex = "^" + productNumRegex + sourceRegex + issueNameRegex + "$";

        // 判断工单标题是否符合规则
        return title.matches(regex);
    }

    public static void main(String[] args) {
        String[] fields = new String[]{"双路由隐患", null, null, null, null};
        String[] types = {"隐患名称", "隐患类别", "隐患等级", "隐患入库规则", "隐患出库规则"};
        compareFields(fields, types);

    }

    public static void compareFields(String[] fields, String[] types) {
        String[] values = {"双路由隐患", "双板卡隐患", "双电源隐患", "双上联隐患"};
        String[][] expectedValues = {
                {"双路由", "一级", "存在单路由隐患", "已满足双路由"},
                {"双板卡", "二级", "存在单板卡隐患", "已满足双板卡"},
                {"双电源", "二级", "存在单电源隐患", "已满足双电源"},
                {"双线路上联", "二级", "存在上联单点隐患", "已满足双上联线路"},
        };
        StringBuilder errorMsg = new StringBuilder();
        int i = 0;

        for (int j = 1; j < fields.length; j++) {
            if (fields[j] == null || fields[j].isEmpty()) {
                System.out.println("【" + types[j] + "】为空");
            } else if (!fields[j].equals(expectedValues[i][j - 1])) {
                System.out.println("【" + types[j] + "】不符合【隐患信息】内容");
            }
        }
        System.out.println("输入正确");
    }

    @Test
    public void aosdif() {
        Boolean rowResult = true;
        Map<String, String> data = new HashMap<>();
        data.put("name", "双路由隐患");
        data.put("type", "双路由");
        data.put("level", "一级");
        data.put("inRule", "存在单路由隐患");
        data.put("outRule", "已满足双路由");

        StringBuilder sb = new StringBuilder();
        rowResult = checkRiskInfo(data, getList(), sb, rowResult);
        System.out.println(sb);
        System.out.println(rowResult);


    }

    private Boolean checkRiskInfo(Map<String, String> data, List<Map<String, String>> list, StringBuilder errorMsg, Boolean rowResult) {
        String[] fields = new String[]{"name", "type", "level", "inRule", "outRule"};
        int[] tmpResult = new int[]{0, 0, 0, 0, 0};
        int[] result = new int[]{0, 0, 0, 0, 0};
        int score = 0;
        for (Map<String, String> map : list) {
            int tmp = 0;
            for (int i = 0; i < fields.length; i++) {
                if (map.get(fields[i]).equals(data.get(fields[i]))) {
                    tmpResult[i] = 1;
                    tmp++;
                }
                if (data.get(fields[i]) == null) {
                    tmpResult[i] = -1;
                }
            }
            if (score < tmp) {
                result = Arrays.copyOf(tmpResult, 5);
            }
        }
        for (int i = 0; i < result.length; i++) {
            if (result[i] == -1) {
                errorMsg.append("【" + fields[i] + "】" + "为空");
                rowResult = false;
            }
            if (result[i] == 0) {
                errorMsg.append("【" + fields[i] + "】" + "不符合【隐患信息】内容");
                rowResult = false;
            }
        }

        return rowResult;
    }





    private List<Map<String, String>> getList() {
        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> data1 = new HashMap<>();
        data1.put("name", "双路由隐患");
        data1.put("type", "双路由");
        data1.put("level", "一级");
        data1.put("inRule", "存在单路由隐患");
        data1.put("outRule", "已满足双路由");
        list.add(data1);

        Map<String, String> data2 = new HashMap<>();
        data2.put("name", "双板卡隐患");
        data2.put("type", "双板卡");
        data2.put("level", "二级");
        data2.put("inRule", "存在单板卡隐患");
        data2.put("outRule", "已满足双板卡");
        list.add(data2);

        Map<String, String> data3 = new HashMap<>();
        data3.put("name", "双电源隐患");
        data3.put("type", "双电源");
        data3.put("level", "二级");
        data3.put("inRule", "存在单电源隐患");
        data3.put("outRule", "已满足双电源");
        list.add(data3);

        Map<String, String> data4 = new HashMap<>();
        data4.put("name", "双上联隐患");
        data4.put("type", "双线路上联");
        data4.put("level", "二级");
        data4.put("inRule", "存在上联单点隐患");
        data4.put("outRule", "已满足双上联线路");
        list.add(data4);


        return list;
    }

    @Test
    public void uiuiosdaf() {
        //计划整改完成时间：yyyy-MM-dd
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        StringBuilder errMsg = new StringBuilder();
        try {
            sdf.parse("2023-5/1");
        } catch (ParseException e) {
            errMsg.append("【计划整改完成时间】列格式校验不标准");
        }
        System.out.println(errMsg);
    }
}
