package com.baby.common.utils.xls;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LJL on 2017/9/11.
 *
 * 用于生成xls文件
 *
 */
public class CreateXls {

    /**
     * 导出成
     * 导出字段,生成xls表格
     * @param list  字段的内容, 以 List<Map> 的形式,list为一行数据,map是这一行中的列
     * @param fieldNames 字段的名称 , 以List<String> ,与字段名对应的中文名称(作为xls中第一列存在)
     * @param filePath xls存放的位置,生成的名称
     */
    public void excelDispose(List<LinkedHashMap> list,List<String> fieldNames,String filePath)
    {
        //生成Workbook
        HSSFWorkbook wb = new HSSFWorkbook();
        //添加Worksheet（不添加sheet时生成的xls文件打开时会报错）
        @SuppressWarnings("FromReport")
        HSSFSheet sheet1 = wb.createSheet("FromReport");
        //保存为Excel文件
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(filePath);
            int row=0,colNum=0;

            for (int i = 0,j=list.size(); i <j ; i++) {
                Map map = list.get(i);

                if(map!=null) {
                    if (row == 0) {
                        Row rowHead = wb.getSheet("FromReport").createRow(row);
                        for (String key : fieldNames) {
                            Cell cellHead = rowHead.createCell(colNum);
                            cellHead.setCellValue(key);
                            colNum++;
                        }
                    }
                    row++;
                    colNum = 0;
                    Row rowBody = wb.getSheet("FromReport").createRow(row);
                    for (Object val : map.values()) {
                        Cell cellBody = rowBody.createCell(colNum);
                        if (val == null) {
                            val = "";
                        }
                        cellBody.setCellValue(val.toString());
                        colNum++;
                    }
                }
            }

            wb.write(out);
        } catch (IOException e) {
            System.out.println(e.toString());
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                System.out.println(e.toString());
            }
        }
    }


}
