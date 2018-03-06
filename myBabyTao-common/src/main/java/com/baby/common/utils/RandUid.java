package com.baby.common.utils;

/**
 * Created by SEELE on 2017/8/3.
 */
import java.util.Random;

/**
 * @version 创建时间：2015-5-5 上午11:39:21
 * 根据随机数和时间戳生成唯一id
 */
public class RandUid {

    public synchronized static String getUid(){
        StringBuffer sb = new StringBuffer();
        long id=(long)((Math.random()*9+1)*100000);
        String num = String.format("%06d", id);
        sb.append(num);
        //SimpleDateFormat df = new SimpleDateFormat("yyMMddHHmm");
        //sb.append(df.format(new Date()));
        long time= System.currentTimeMillis();
        sb.append(time);
        Random random = new Random();
        sb.append(random.nextInt(100)+10);
        return sb.toString();
    }

    public static String getClientSecret(int length)
    {
        String val = "";
        Random random = new Random();
        for(int i = 0; i < length; i++)
        {
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num"; // 输出字母还是数字

            if("char".equalsIgnoreCase(charOrNum)) // 字符串
            {
                int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; //取得大写字母还是小写字母
                val += (char) (choice + random.nextInt(26));
            }
            else if("num".equalsIgnoreCase(charOrNum)) // 数字
            {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }

    public static void main(String[] args) {
		/*String uid = RandTimeUid.getUid();*/
        String secret = getClientSecret(32);
       System.out.println(secret);
    }

}