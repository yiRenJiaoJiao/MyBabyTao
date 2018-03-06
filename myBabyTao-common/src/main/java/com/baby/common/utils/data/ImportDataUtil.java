package com.baby.common.utils.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017-09-06.
 */
public class ImportDataUtil {
    private List<String> sysFiled;
    public  ImportDataUtil()
    {
        sysFiled=new ArrayList<String>();

    }

    /**
     * 数据验证处理
     * email或mobile不对的写入错误列表中
     * @param importData
     * @param fileNames
     */
    public static void disposeImportData(List<List<String>> importData,String fileNames) {
        String[] fileds=fileNames.replace(",-1","").replace("-1,", "").split(",");

        List<List<String>> errorRow=new ArrayList<List<String>>();
        for(List<String> row:importData){
            int colIndex=0;
            for(String filed:fileds)
            {
                String strCol=row.get(colIndex);
                if(filed.equals("email")) //邮箱号码验证
                {
                    if(!VerifyData.checkEmail(strCol))//不正常的Email格式跳过整行并写入错误信息表
                    {
                        row.add("Email格式不对");
                        errorRow.add(row); //加入错误信息列
                        break;
                    }
                }
                if (filed.equals("mobile"))
                {
                    if(!VerifyData.checkMobile(strCol))//不正常的Mobile格式跳过整行并写入错误信息表
                    {
                        row.add("手机号格式不对");
                        errorRow.add(row); //加入错误信息列
                        break;
                    }
                }
                System.out.print(filed+":"+strCol+" , ");
                colIndex++;
            }
            System.out.println();
        }
        int len=fileds.length;
    }


}

