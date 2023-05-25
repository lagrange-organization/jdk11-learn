import com.google.common.base.CaseFormat;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import com.groupdocs.viewer.Viewer;
import com.groupdocs.viewer.options.PdfViewOptions;
import lombok.Data;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.sql.SQLOutput;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @author szy
 * @date 2022/8/15 9:29
 */
public class MyTest {
    //change1

    //method1

    //method2

    @Test
    public void skfja() {
        int startIndex = 0;
        int endIndex = 0;
        ArrayList<Integer> serialNoSgList = new ArrayList<>();
        for (int i = 1; i <= 1000; i++) {
            serialNoSgList.add(i);

        }
        for (int i = 0; i < Math.ceil(serialNoSgList.size() / 1000.0); i++) {
            endIndex = Math.min(startIndex + 1000, serialNoSgList.size());
            List<Integer> subSerialNoSgList = serialNoSgList.subList(startIndex, endIndex);
            System.out.println(startIndex + "--->" + endIndex);
            startIndex = endIndex;
        }

    }

    @Test
    public void name() {
        Map<String, Integer> map = this.dictMap();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "-->" + entry.getValue());
        }
    }

    protected Map<String, Integer> dictMap() {
        Map<String, Integer> map = Maps.newLinkedHashMap();
        Arrays.stream(ProductNameEnum.values()).forEach(e -> map.put(e.oldName, e.code));
        return map;
    }

    @Test
    public void asfadsf() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date1 = dateFormat.parse("2023-01-09 00:00:00");
            Date date2 = dateFormat.parse("2023-01-09 00:01:20");
            BigDecimal minutes = BigDecimal.valueOf((date2.getTime() - date1.getTime()) / (60.0 * 1000))
                    .setScale(2, RoundingMode.HALF_UP);
            System.out.println(minutes);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void sjdhfa() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date1 = dateFormat.parse("2023-1-4  10:04:06");
            Date date2 = dateFormat.parse("2023-1-4  10:01:12");
            System.out.println(BigDecimal.valueOf((date1.getTime()-date2.getTime())/(60.0*1000)).setScale(2, RoundingMode.HALF_UP));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void iuyib() {
        BigDecimal num1 = null;
        BigDecimal num2 = new BigDecimal(10);
        BigDecimal number1 = Optional.ofNullable(num1).orElse(new BigDecimal(0));
        BigDecimal number2 = Optional.ofNullable(num2).orElse(new BigDecimal(0));
        System.out.println(number1.compareTo(number2));
    }


    @Test
    public void erewqr() {
        String input = "-0x2A";
        Integer result = Integer.decode(input);
        //result = Integer.valueOf("2A", 16);
        System.out.println(result);
    }

    @Test
    public void werwer() {
        System.out.println(String.join(",", new ArrayList<String>()).length());
    }

    @Test
    public void sdfjak() {
        System.out.println(DateUtils.addMonths(new Date(), -3));
    }

    @Test
    public void regexTest() throws Exception{
        String regex;
        String fileTimeString;
        String fileName = "CUSTOMERSATISFACTIONS_202303011.csv";

        // 数据示例文件：ETWXT_QSXNGL_20220906162000_15.csv
        if (RegexUtils.match("^CUSTOMERSATISFACTION_\\d*.csv$", fileName)) {
            regex = "(?<=CUSTOMERSATISFACTION_).*?(?=.csv)";
            fileTimeString = RegexUtils.substringFirst(regex, fileName);
        }
         else {
            throw new Exception("【未知的文件格式】：" + fileName);
        }

        System.out.println(Long.parseLong(fileTimeString));
    }

    @Test
    public void asdf() {
        String filename = "D:\\download\\msb\\20230301\\GRP.INQUIRY.ZX\\新文件 4.txt";
        int count = 0; // 行数计数器
        long start = System.currentTimeMillis();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            while (reader.readLine() != null) { // 逐行读取文件
                count++; // 每读取一行，计数器加1
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("The number of lines in the file is: " + count);
        System.out.println("time is : " + (end - start));

    }

    @Test
    public void iwreyo() throws IOException {
        File file = new File("D:\\download\\msb\\20230301\\GRP.INQUIRY.ZX\\新文件 4.txt");
        long start = System.currentTimeMillis();
        List<String> list = Files.readLines(file, StandardCharsets.UTF_8);
        long end = System.currentTimeMillis();
        System.out.println(list.size());
        System.out.println("time is : " + (end - start));
    }

    @Test
    public void ihaksdf() {
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);

        int k = 2; // 滑动窗口大小
        int sum = 0; // 当前窗口的和
        int i = 0; // 当前键值对的位置
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            int value = (int) map.entrySet().toArray(new Map.Entry[0])[i].getValue();
            System.out.println("第" + (i+ 1) + "个的值是：" + value);
            i++;
        }
    }

    //@Data
    class Student {
        private String name;
        private Integer age;
        private Boolean isPass;
    }

    enum ProductNameEnum {

        DATA_LINE_CIRCUIT_HIRE("数据专线-电路租用", "传输租赁", 3),
        IPVPN("IPVPN", "IPVPN", 4),
        INTERNET_100("互联网专线（极速版1:10）-100M套餐（每月110元）", "互联网专线", 1),
        INTERNET_500("互联网专线（极速版1:10）-500M套餐（每月300元）", "互联网专线", 1),
        DATA_LINE_INTERNET_ACCESS("数据专线_互联网接入", "互联网专线", 1),
        CENTREX("统一Centrex", "IMS多媒体语音桌面专线", 2),
        CLOUD_CONVERGENCE("云汇聚", "传输租赁", 3),
        CLOUD_CROSS("云跨线", "传输租赁", 3),
        ENTERPRISE_CALL_CENTER("企业呼叫中心", "IMS多媒体语音桌面专线", 2),
        ;
        private final String oldName;
        private final String targetName;
        private final Integer code;

        public String oldName() {
            return oldName;
        }

        public String targetName() {
            return targetName;
        }

        ProductNameEnum(String oldName, String targetName, Integer code) {
            this.oldName = oldName;
            this.targetName = targetName;
            this.code = code;
        }


    }

    String doSelectSql2(Class<?> cls) {
        String className = cls.getSimpleName();
        List<String> fieldList = Arrays.stream(cls.getDeclaredFields())
                .map(p -> String.format(" %s as %s ", CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, p.getName()), p.getName()))
                .collect(Collectors.toList());
        return String.format(" select %s from %s where 1=1 ",
                StringUtils.join(fieldList, ", "), className);
    }

    @Test
    public void tdfgs() {
        System.out.println(doSelectSql2(Student.class));
    }
}
