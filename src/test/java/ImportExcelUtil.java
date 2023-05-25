import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class ImportExcelUtil {
    //2003- 版本的excel
    private final static String EXCEL2003L = ".xls";

    //2007+ 版本的excel
    private final static String EXCEL2007U = ".xlsx";

    /**
     * 描述：获取IO流中的数据，组装成List<List<Object>>对象
     *
     * @param in,fileName
     * @return
     * @throws IOException
     */
    public List<List<Object>> getBankListByExcel(InputStream in, String fileName, String format) throws Exception {
        List<List<Object>> list = null;
        //创建Excel工作薄
        Workbook work = this.getWorkbook(in, fileName);
        if (null == work) {
            throw new Exception("创建Excel工作薄为空！");
        }
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;
        list = new ArrayList<List<Object>>();
        //遍历Excel中所有的sheet
        for (int i = 0; i < work.getNumberOfSheets(); i++) {
            sheet = work.getSheetAt(i);
            //过滤为空或者没有数据的sheet页
            if (sheet == null || work.isSheetHidden(i) || work.isSheetVeryHidden(i)
                    || (sheet.getLastRowNum() == 0 && sheet.getPhysicalNumberOfRows() == 0)) {
                continue;
            }
            //遍历当前sheet中的所有行
            int cellLast;
            //记录列数,以模板标题列为准
            int rowLastNum = 0;
            for (int j = sheet.getFirstRowNum(); j < sheet.getLastRowNum() + 1; j++) {
                row = sheet.getRow(j);
                //跳过标题行
                if (row != null) {
                    if (row.equals(sheet.getRow(0))) {
                        rowLastNum = row.getLastCellNum();
                        continue;
                    }
                    cellLast = 0;
                    for (int y = 0; y < rowLastNum; y++) {
                        cellLast = cellLast + 1;
                    }
                    //判断行中首个单元格索引是否为负数，负数代表行中单元格的个数为0，需要跳过此行
                    if ((short) -1 == row.getFirstCellNum()) {
                        continue;
                    }
                    //遍历所有的列
                    List<Object> li = new ArrayList<Object>();
                    for (int y = 0; y < cellLast; y++) {
                        cell = row.getCell(y);
                        li.add(this.getCellValue(cell, format));
                    }
                    list.add(li);
                }
            }
        }
        work.close();
        return list;
    }

    /**
     * 描述：根据文件后缀，自适应上传文件的版本
     *
     * @param inStr,fileName
     * @return
     * @throws Exception
     */
    public Workbook getWorkbook(InputStream inStr, String fileName) throws Exception {
        Workbook wb = null;
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if (EXCEL2003L.equals(fileType)) {
            //2003版本的excel
            wb = new HSSFWorkbook(inStr);
        } else if (EXCEL2007U.equals(fileType)) {
            //2007以上版本的excel
            wb = new XSSFWorkbook(inStr);
        } else {
            throw new Exception("解析的文件格式有误！");
        }
        return wb;
    }


    /**
     * 描述：对表格中数值进行格式化
     * @param cell
     * @param format
     * @return
     */
    public Object getCellValue(Cell cell, String format) {
        Object value = null;
        //格式化number String字符
        DecimalFormat df = new DecimalFormat("#.##");
        //日期格式化
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //格式化数字
        DecimalFormat df2 = new DecimalFormat("0.000000000");
        //格式化数字
        DecimalFormat df3 = new DecimalFormat("0.00");
        if (cell != null) {
            //默认样式
            if (format == null) {
                switch (cell.getCellTypeEnum()) {
                    case STRING:
                        value = cell.getRichStringCellValue().getString();
                        break;
                    case NUMERIC:
                        if ("General".equals(cell.getCellStyle().getDataFormatString())) {
                            value = df.format(cell.getNumericCellValue());
                        } else if ("m/d/yy".equals(cell.getCellStyle().getDataFormatString())) {
                            value = sdf.format(cell.getDateCellValue());
                        } else {
                            value = df3.format(cell.getNumericCellValue());
                        }
                        break;
                    case BOOLEAN:
                        value = cell.getBooleanCellValue();
                        break;
                    case BLANK:
                        value = "";
                        break;
                    default:
                        break;
                }
            } else if ("format_a".equals(format)) {
                switch (cell.getCellTypeEnum()) {
                    case STRING:
                        value = cell.getRichStringCellValue().getString();
                        break;
                    case NUMERIC:
                        if ("m/d/yy".equals(cell.getCellStyle().getDataFormatString())) {
                            value = sdf.format(cell.getDateCellValue());
                        } else {
                            value = df.format(cell.getNumericCellValue());
                        }
                        break;
                    case BOOLEAN:
                        value = cell.getBooleanCellValue();
                        break;
                    case BLANK:
                        value = "";
                        break;
                    default:
                        break;
                }
            } else if ("format_b".equals(format)) {
                switch (cell.getCellTypeEnum()) {
                    case STRING:
                        value = cell.getRichStringCellValue().getString();
                        break;
                    case NUMERIC:
                        if ("General".equals(cell.getCellStyle().getDataFormatString())) {
                            value = df.format(cell.getNumericCellValue());
                        } else if ("m/d/yy".equals(cell.getCellStyle().getDataFormatString())) {
                            value = sdf.format(cell.getDateCellValue());
                        } else {
                            value = cell.getNumericCellValue();
                        }
                        break;
                    case BOOLEAN:
                        value = cell.getBooleanCellValue();
                        break;
                    case BLANK:
                        value = "";
                        break;
                    default:
                        break;
                }
            }
        } else {
            value = "";
        }
        return value;
    }


}
