package com.baby.common.utils.data;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2017-09-01.
 */
public class ExcelDataUtil {


//    public void ExcelDispose<T>(List<T> mongoCursor,String filePath)
//    {
//        //生成Workbook
//        HSSFWorkbook wb = new HSSFWorkbook();
//        //添加Worksheet（不添加sheet时生成的xls文件打开时会报错）
//        @SuppressWarnings("FromReport")
//        HSSFSheet sheet1 = wb.createSheet("FromReport");
//        //保存为Excel文件
//        FileOutputStream out = null;
//        try {
//            out = new FileOutputStream(filePath);
//            int row=0,colNum=0;
//            while(mongoCursor.hasNext())
//            {
//                Document decument= mongoCursor.next();
//                if(row==0) {
//                    Row rowHead = wb.getSheet("FromReport").createRow(row);
//                    for (String key : decument.keySet()) {
//                        org.apache.poi.ss.usermodel.Cell cellHead = rowHead.createCell(colNum);
//                        cellHead.setCellValue(key);
//                        colNum++;
//                    }
//                }
//                row++;colNum=0;
//                Row rowBody = wb.getSheet("FromReport").createRow(row);
//                for (Object val : decument.values()) {
//                    org.apache.poi.ss.usermodel.Cell cellBody = rowBody.createCell(colNum);
//                    cellBody.setCellValue(val.toString());
//                    colNum++;
//                }
//            }
//            wb.write(out);
//        } catch (IOException e) {
//            System.out.println(e.toString());
//        } finally {
//            try {
//                out.close();
//            } catch (IOException e) {
//                System.out.println(e.toString());
//            }
//        }
//    }

    /**
     *
     * @Title: getXlsHead
     * @Description: 处理xls文件,获取xls列名
     * @param @param path 需要处理的文件路径
     * @param @return
     * @throws
     * 从代码不难发现其处理逻辑：
     * 1.先用InputStream获取excel文件的io流
     * 2.然后穿件一个内存中的excel文件HSSFWorkbook类型对象，这个对象表示了整个excel文件。
     * 3.对这个excel文件的每页做循环处理
     * 4.对每页中每行做循环处理
     * 5.对每行中的每个单元格做处理，获取这个单元格的值
     * 6.把这行的结果添加到一个List数组中
     * 7.把每行的结果添加到最后的总结果中
     * 8.解析完以后就获取了一个List<List<String>>类型的对象了
     *
     */
    public static List<String> getXlsHead(String path) throws Exception {
        InputStream is = new FileInputStream(path);
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);// HSSFWorkbook 标识整个excel
        int size = hssfWorkbook.getNumberOfSheets();
        int index=-1;//默认取第一个Sheet，当第一个为Null是取后面的第一个正常的
        HSSFSheet hssfSheet;
        for (int numSheet = 0; numSheet < size; numSheet++) {  // 循环每一页，并处理当前循环页
            hssfSheet = hssfWorkbook.getSheetAt(numSheet);     // HSSFSheet 标识某一页
            System.out.println(String.format("总共有%s个第%s个Sheet名称为%s", size,numSheet, hssfSheet.getSheetName()));
            if (hssfSheet != null&&index<0)
                index=numSheet;
        }
        if (index<0)
            return null;
        hssfSheet=hssfWorkbook.getSheetAt(index);
        System.out.println(String.format("总共有%s个，取得第%s个Sheet名称为%s，开始处理", size,index, hssfSheet.getSheetName()));
        HSSFRow hssfRow = hssfSheet.getRow(0);    //取第一行列名数据
        int minColIx = hssfRow.getFirstCellNum();
        int maxColIx = hssfRow.getLastCellNum();
        List<String> result = new ArrayList();
        for (int colIx = minColIx; colIx < maxColIx; colIx++) {
            HSSFCell cell = hssfRow.getCell(colIx);    // HSSFCell 表示单元格
            if (cell == null) { //为空则进入并赋值为""
                result.add("");
                continue;
            }
            result.add(getStringVal(cell));
        }
        return result;
    }

    /**
     *
     * @Title: readXls
     * @Description: 处理xls文件
     * @param @param path
     * @param @return
     * @param @throws Exception    设定文件
     * @return List<List<String>>    返回类型
     * @throws
     *
     * 从代码不难发现其处理逻辑：
     * 1.先用InputStream获取excel文件的io流
     * 2.然后穿件一个内存中的excel文件HSSFWorkbook类型对象，这个对象表示了整个excel文件。
     * 3.对这个excel文件的每页做循环处理
     * 4.对每页中每行做循环处理
     * 5.对每行中的每个单元格做处理，获取这个单元格的值
     * 6.把这行的结果添加到一个List数组中
     * 7.把每行的结果添加到最后的总结果中
     * 8.解析完以后就获取了一个List<List<String>>类型的对象了
     *
     */
    public List<List<String>> readXls(String path) throws Exception {
        InputStream is = new FileInputStream(path);
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);// HSSFWorkbook 标识整个excel
        List<List<String>> result = new ArrayList<List<String>>();
        int size = hssfWorkbook.getNumberOfSheets();
        int index=-1;//默认取第一个Sheet，当第一个为Null是取后面的第一个正常的
        HSSFSheet hssfSheet;
        for (int numSheet = 0; numSheet < size; numSheet++) {  // 循环每一页，并处理当前循环页
            // HSSFSheet 标识某一页
            hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            System.out.println(String.format("总共有%s个第%s个Sheet名称为%s", size,numSheet, hssfSheet.getSheetName()));
            if (hssfSheet != null&&index<0)
                index=numSheet;
        }
        if (index<0)
            return null;

        hssfSheet=hssfWorkbook.getSheetAt(index);
        System.out.println(String.format("总共有%s个，取得第%s个Sheet名称为%s，开始处理", size,index, hssfSheet.getSheetName()));
        for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) { // 处理当前页，循环读取每一行
            HSSFRow hssfRow = hssfSheet.getRow(rowNum);    // HSSFRow表示行
            int minColIx = hssfRow.getFirstCellNum();
            int maxColIx = hssfRow.getLastCellNum();
            List<String> rowList = new ArrayList<String>();// 遍历改行，获取处理每个cell元素
            for (int colIx = minColIx; colIx < maxColIx; colIx++) {
                HSSFCell cell = hssfRow.getCell(colIx);    // HSSFCell 表示单元格
                if (cell == null) { //为空跳过
                    rowList.add("");
                    continue;
                }
                rowList.add(getStringVal(cell));
            }
            result.add(rowList);
        }

        return result;
    }

    /**
     *
     * @Title: readXlsx
     * @Description: 处理Xlsx文件
     * @param @param path
     * @param @return
     * @param @throws Exception    设定文件
     * @return List<List<String>>    返回类型
     * @throws
     */
    private List<List<String>> readXlsx(String path) throws Exception {
        InputStream is = new FileInputStream(path);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
        List<List<String>> result = new ArrayList<List<String>>();
        // 循环每一页，并处理当前循环页
//        for (XSSFSheet xssfSheet : xssfWorkbook) {
//            if (xssfSheet == null) {
//                continue;
//            }
//            // 处理当前页，循环读取每一行
//            for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
//                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
//                int minColIx = xssfRow.getFirstCellNum();
//                int maxColIx = xssfRow.getLastCellNum();
//                List<String> rowList = new ArrayList<String>();
//                for (int colIx = minColIx; colIx < maxColIx; colIx++) {
//                    XSSFCell cell = xssfRow.getCell(colIx);
//                    if (cell == null) {
//                        continue;
//                    }
//                    rowList.add(cell.toString());
//                }
//                result.add(rowList);
//            }
//        }
        return result;
    }

    /**
     *
     * @Title: readXls
     * @Description: 处理xls文件
     * @param @param path
     * @param @return
     * @param @throws Exception    设定文件
     * @return List<List<String>>    返回类型
     * @throws
     *
     * 从代码不难发现其处理逻辑：
     * 1.先用InputStream获取excel文件的io流
     * 2.然后穿件一个内存中的excel文件HSSFWorkbook类型对象，这个对象表示了整个excel文件。
     * 3.对这个excel文件的每页做循环处理
     * 4.对每页中每行做循环处理
     * 5.对每行中的每个单元格做处理，获取这个单元格的值
     * 6.把这行的结果添加到一个List数组中
     * 7.把每行的结果添加到最后的总结果中
     * 8.解析完以后就获取了一个List<List<String>>类型的对象了
     *
     */
    public static List<String> readXlsTitle(String path) throws Exception {
        InputStream input = new FileInputStream(path);
        // HSSFWorkbook 标识整个excel
        boolean isE2007 = false;    //判断是否是excel2007格式
        if(path.endsWith("xlsx"))
            isE2007 = true;

        Workbook hssfWorkbook  = null;
        //根据文件格式(2003或者2007)来初始化
        if(isE2007)
            hssfWorkbook = new XSSFWorkbook(input);
        else
            hssfWorkbook = new HSSFWorkbook(input);
        Sheet sheet = hssfWorkbook.getSheetAt(0);     //获得第一个表单
        List<String> result = new ArrayList();
        Iterator<Row> rows = sheet.rowIterator(); //获得第一个表单的迭代器
        while (rows.hasNext()) {
            Row row = rows.next();  //获得行数据
            Iterator<Cell> cells = row.cellIterator();    //获得第一行的迭代器
            while (cells.hasNext()) {
                Cell cell = cells.next();
               // result.add(getStringVal(cell.));
            }
        }
        return result;
    }

    /**
     * 改造poi默认的toString（）方法如下
     * @Title: getStringVal
     * @Description: 1.对于不熟悉的类型，或者为空则返回""控制串
     *               2.如果是数字，则修改单元格类型为String，然后返回String，这样就保证数字不被格式化了
     * @param @param cell
     * @param @return    设定文件
     * @return String    返回类型
     * @throws
     */
    public static String getStringVal(HSSFCell cell) {
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_BOOLEAN:
                return cell.getBooleanCellValue() ? "TRUE" : "FALSE";
            case Cell.CELL_TYPE_FORMULA:
                return cell.getCellFormula();
            case Cell.CELL_TYPE_NUMERIC://如果是数字类型的将其先转为字符串类型的
                cell.setCellType(Cell.CELL_TYPE_STRING);
                return cell.getStringCellValue();
            case Cell.CELL_TYPE_STRING:
                return cell.getStringCellValue();
            default:
                return "";
        }
    }
    /**
     * 获得Excel文件第一页第一行数据
     *
     * @param fileName 待处理文件的全路劲
     */
    public static List<String> readExcelHead(String fileName){
        List<String> listHead=new ArrayList<String>();
        try {
            InputStream input = new FileInputStream(fileName);  //建立输入流
            Workbook wb  = null;
            if(fileName.toLowerCase().endsWith("xlsx"))//根据文件格式(2003或者2007)来初始化
                wb = new XSSFWorkbook(input);
            else
                wb = new HSSFWorkbook(input);

            int size=wb.getNumberOfSheets();    //获得Excel中Sheet的个数
            int index=-1;
            for (int numSheet = 0; numSheet < size; numSheet++) {  // 循环每一页，并处理当前循环页
                Sheet sheet = wb.getSheetAt(numSheet);     //获得单个Sheet表单
                System.out.println(String.format("文件：%s总共有%s个第%s个Sheet名称为%s",fileName, size,numSheet, sheet.getSheetName()));
                if (sheet != null&&index<0)                //判断是否为空，且是否为第一个非空的Sheet
                    index=numSheet;
            }
            if (index<0) {
                System.out.println("未找到合适Sheet");
                return null;
            }

            Sheet sheet = wb.getSheetAt(index);       //获得第一个非空Sheet表单
            Row row=sheet.getRow(0);                  //获得第一行数据
            System.out.println("Row #" + row.getRowNum());
//            Iterator<Cell> cells = row.cellIterator();//获得第一行的迭代器---问题点会自动过滤数据为空的列，造成数据缺失
//            while (cells.hasNext()) {
//                Cell cell = cells.next();
//                System.out.println("Cell #" + cell.getColumnIndex()+"内容为："+getStringVal(cell));
//                listHead.add(getStringVal(cell));
//            }
            int minColIx = 0;
            int maxColIx = row.getLastCellNum();
            for (int colIx = minColIx; colIx < maxColIx; colIx++) {
                Cell cell = row.getCell(colIx);    // 每个单元格
                listHead.add(getStringVal(cell));
            }

        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println(String.format("readExcelHead()文件：%s处理异常,IOEx.Message:%s,StackTrace:%s",fileName,ex.getMessage(),ex.getStackTrace()));
        }catch (Exception ex){
            ex.printStackTrace();
            System.out.println(String.format("readExcelHead()文件：%s处理异常,ex.Message:%s,StackTrace:%s",fileName,ex.getMessage(),ex.getStackTrace()));
        }
        return listHead;
    }

    /**
     * 获取Excel中的所有数据
     * 缺陷获取的数据已第一行的列数为准，如后面的有超过的第一行列数的不会进行处理
     * @param fileName
     * @return
     */
    public static List<List<String>> readExcel(String fileName){
        List<List<String>> result = new ArrayList<List<String>>();
        try {
            InputStream input = new FileInputStream(fileName);  //建立输入流
            Workbook wb  = null;
            if(fileName.toLowerCase().endsWith("xlsx"))//根据文件格式(2003或者2007)来初始化
                wb = new XSSFWorkbook(input);
            else
                wb = new HSSFWorkbook(input);
            int index=-1;
            int size=wb.getNumberOfSheets();    //获得Excel中Sheet的个数
            for (int numSheet = 0; numSheet < size; numSheet++) {  // 循环每一页，并处理当前循环页
                Sheet sheet = wb.getSheetAt(numSheet);     //获得单个Sheet表单
                System.out.println(String.format("文件：%s总共有%s个第%s个Sheet名称为%s",fileName, size,numSheet, sheet.getSheetName()));
                if (sheet != null&&index<0)                //判断是否为空，且是否为第一个非空的Sheet
                    index=numSheet;
            }
            if (index<0) {
                System.out.println("未找到合适Sheet");
                return null;
            }
            Sheet sheet = wb.getSheetAt(index);       //获得第一个非空Sheet表单
            Iterator<Row> rows = sheet.rowIterator(); //获得Sheet表单的迭代器
            int minColIx=0,maxColIx=0;
            while (rows.hasNext()) {
                Row row = rows.next();  //获得行数据
                System.out.println("Row #" + row.getRowNum());
                if (row.getRowNum()==0) //获取第一行的列数
                {
                    minColIx = row.getFirstCellNum();
                    maxColIx = row.getLastCellNum();
                }
                List<String> listRow = new ArrayList<String>();
                for (int colIx = minColIx; colIx < maxColIx; colIx++) {
                    Cell cell = row.getCell(colIx);    // 每个单元格
                    listRow.add(getStringVal(cell));
                }
                result.add(listRow);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println(String.format("readExcelHead()文件：%s处理异常,IOEx.Message:%s,StackTrace:%s",fileName,ex.getMessage(),ex.getStackTrace()));
        }catch (Exception ex){
            ex.printStackTrace();
            System.out.println(String.format("readExcelHead()文件：%s处理异常,ex.Message:%s,StackTrace:%s",fileName,ex.getMessage(),ex.getStackTrace()));
        }
        return result;
    }

    public static String getStringVal(Cell cell) {
        if(cell==null)
            return "";
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_BOOLEAN:
                return cell.getBooleanCellValue() ? "TRUE" : "FALSE";
            case Cell.CELL_TYPE_FORMULA:
                return cell.getCellFormula();
            case Cell.CELL_TYPE_NUMERIC://如果是数字类型的将其先转为字符串类型的
                if(HSSFDateUtil.isCellDateFormatted(cell)){
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    return sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
                }
                cell.setCellType(Cell.CELL_TYPE_STRING);
                return cell.getStringCellValue();
            case Cell.CELL_TYPE_STRING:
                return cell.getStringCellValue();
            default:
                return "";
        }
    }


    /**
     * 获取Excel中的所有数据
     * @param fileName
     * @return
     */
    public static List<List<String>> readExcel(String fileName,String chooseFiled){
        List<List<String>> result = new ArrayList<List<String>>();
        try {
            InputStream input = new FileInputStream(fileName);  //建立输入流
            Workbook wb  = null;
            if(fileName.toLowerCase().endsWith("xlsx"))//根据文件格式(2003或者2007)来初始化
                wb = new XSSFWorkbook(input);
            else
                wb = new HSSFWorkbook(input);
            int index=-1;
            int size=wb.getNumberOfSheets();    //获得Excel中Sheet的个数
            for (int numSheet = 0; numSheet < size; numSheet++) {  // 循环每一页，并处理当前循环页
                Sheet sheet = wb.getSheetAt(numSheet);     //获得单个Sheet表单
                System.out.println(String.format("文件：%s总共有%s个第%s个Sheet名称为%s",fileName, size,numSheet, sheet.getSheetName()));
                if (sheet != null&&index<0)                //判断是否为空，且是否为第一个非空的Sheet
                    index=numSheet;
            }
            if (index<0) {
                System.out.println("未找到合适Sheet");
                return null;
            }
            Sheet sheet = wb.getSheetAt(index);       //获得第一个非空Sheet表单
            Iterator<Row> rows = sheet.rowIterator(); //获得Sheet表单的迭代器
            String[] sFiled=chooseFiled.split(",");//获取需要取的字段

            int minColIx=0,maxColIx=sFiled.length;//设置行取的列的起始和末尾数
            int nullRowNum=0;
            while (rows.hasNext()) {
                Row row = rows.next();  //获得行数据
                System.out.println("Row #" + row.getRowNum());
                List<String> listRow = new ArrayList<String>();
                boolean rowvalid=true;
                for (int colIx = minColIx; colIx < maxColIx; colIx++) {
                    if(sFiled[colIx].equals("-1"))//判断是否为需要取的列，如未选取则跳过
                        continue;
                    Cell cell = row.getCell(colIx);    // 每个单元格
                    String cellDaTA=getStringVal(cell);
                    if(!cellDaTA.equals("")){
                        rowvalid=false;
                    }
                    listRow.add(cellDaTA);
                }
                result.add(listRow);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println(String.format("readExcelHead()文件：%s处理异常,IOEx.Message:%s,StackTrace:%s",fileName,ex.getMessage(),ex.getStackTrace()));
        }catch (Exception ex){
            ex.printStackTrace();
            System.out.println(String.format("readExcelHead()文件：%s处理异常,ex.Message:%s,StackTrace:%s",fileName,ex.getMessage(),ex.getStackTrace()));
        }
        return result;
    }
}
