package com.baby.common.utils.data;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017-09-07.
 */
public class VerifyData {

    private static String regexEmail="^\\w+(([-+.]|\\w)+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$"; //EMail验证正则
    private static String regexPhone="^(((13[0-9])|(15[0-9])|(18[0-9])|(14[1,3,5,7,8,9])|(17[1,3,5,6,7,8]))\\d{8})$|((170[0,1,2,4,5,7,8,9])\\d{7})$";//手机号码验证正则
    private static String regexFiled="[{]{3}([^{}]+)[}]{3}";//自定义字段验证正则
    private static String regexLink="(http|https):\\/\\/([\\w.]+\\/?)\\S*";//链接验证正则

    /**
     * 获取设置链接左右带空格后的内容
     *  @param content：内容
     *  @return
     * */
    public static String getLinkSpaceByContent(String content) {
        Pattern parent = Pattern.compile("\\s*[{]{3}link[}]{3}\\s*");
        Matcher matcher = parent.matcher(content);
        //获取所有自定义字段
        while (matcher.find()) {
            content= content.replace(matcher.group(0), " {{{link}}} ");
        }
        return content;
    }

    /**
     * Email格式是否正确验证
     * @param email
     * @return 验证通过返回true，不通过返回false
     */
    public static boolean checkLink(String link)
    {
        if (!link.matches(regexLink))
            return false;
        return true;
    }


    /**
     * 获取内容中自定义字段
     *  @param content：内容
     *  @return
     * */
    public static List<String> getContentFileds(String content) {
        Pattern parent = Pattern.compile(regexFiled);
        Matcher matcher = parent.matcher(content);
        List<String> filedLIst = new ArrayList<>();
        //获取所有自定义字段
        while (matcher.find()) {
            if (!filedLIst.contains(matcher.group()))
                filedLIst.add(matcher.group());
        }
        return filedLIst;
    }

    /**
     * 获取内容中自定义字段,会去掉包裹括号
     *  @param content：内容
     *  @return
     * */
    public static List<String> getFiledsByContent(String content) {
        Pattern parent = Pattern.compile(regexFiled);
        Matcher matcher = parent.matcher(content);
        List<String> filedLIst = new ArrayList<>();
        //获取所有自定义字段
        while (matcher.find()) {
            if (!filedLIst.contains(matcher.group(1)))
                filedLIst.add(matcher.group(1));
        }
        return filedLIst;
    }
    /**
     * 转为List<Integer>
     * @param list：String集合
     *
     * @return Integer集合
     */
    public static List<Integer> turnIntegerList(List<String> list) {
        List<Integer> idsList=new ArrayList<>();
        try{
            for(String value:list){
                idsList.add(Integer.parseInt(value));
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return idsList;
    }
    /**
     * 字符串是否为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        if (str == null || "".equals(str.trim())) {
            return true;
        }
        return false;
    }


    /**
     * Email格式是否正确验证
     * @param email
     * @return 验证通过返回true，不通过返回false
     */
    public static boolean checkEmail(String email)
    {
        if (!email.matches(regexEmail))
            return false;
        return true;
    }

    /**
     * Mobile格式是否正确验证
     * @param mobile
     * @return 验证通过返回true，不通过返回false
     */
    public static boolean checkMobile(String mobile)
    {
        if (!mobile.matches(regexPhone))
            return false;
        return true;
    }

    /**
     * 判断字符串是否能转换为数字类型
     * @param str
     * @return 能返回true，不能返回false
     */
    public static boolean isNumeric(String str){
        for (int i = str.length();--i>=0;){
            if (!Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }

    /**
     * 判断字符串是否能转换为数字类型（使用正则）
     * @param str
     * @return 能返回true，不能返回false
     */
    public static boolean strIsNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }

    /**
     * 用户提交的多Id做是否为数字验证，并返回之前IDS
     * @param ids
     * @return 无返回-1，有返回拼接数字
     */
    public static String idListDispose(String ids)
    {
        String[] strIds=ids.split(",");
        StringBuffer str=new StringBuffer();
        for(String id:strIds){
            if(!strIsNumeric(id)){
                continue;
            }
            str.append(",");
            str.append(id);
        }
        if(str.length()>2)
           return str.toString().substring(1);
        return "-1";
    }

    /**
     * 判断用户提交的多Id是否都为数字
     * @param ids
     * @return true为合理，false为存在异常数据
     */
    public static boolean checkIdCustom(String ids)
    {
        String[] strIds=ids.split(",");
        boolean result=true;
        for(String id:strIds){
            if(!strIsNumeric(id)){
                return  false;
            }
        }
        return result;
    }

    /**
     * 字符串是否是日期格式验证
     */
    public static boolean isValidDate(String str) {
        boolean convertSuccess=true;
        // 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        try {
            // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            // e.printStackTrace();
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            convertSuccess=false;
        }
        return convertSuccess;
    }



    /**
     * 匹配是否包含数字,字符串是否是数字验证
     * @param str 可能为中文，也可能是-19162431.1254，不使用BigDecimal的话，变成-1.91624311254E7
     * @return
     */
    public static boolean isNumericFull(String str) {
        // 该正则表达式可以匹配所有的数字 包括负数
        Pattern pattern = Pattern.compile("-?[0-9]+\\.?[0-9]*");
        String bigStr;
        try {
            bigStr = new BigDecimal(str).toString();
        } catch (Exception e) {
            return false;//异常 说明包含非数字。
        }

        Matcher isNum = pattern.matcher(bigStr); // matcher是全匹配
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }


    /**
     * 是否为数字, 只允许为整形
     *
     * @param num
     * @return
     */
    public static boolean isNum(String num) {
        if (!isEmpty(num)) {
            String regex = "[0-9]*";
            return matText(regex, num);
        }
        return false;
    }

    private static boolean matText(String expression, String text) {
        Pattern p = Pattern.compile(expression); // 正则表达式
        Matcher m = p.matcher(text); // 操作的字符串
        boolean b = m.matches();
        return b;
    }

    /**
     * list集合去重复
     * @param list
     * @return
     */
    public static List filterList(List list){
        Set set = new HashSet();
        List newList = new ArrayList();
        set.addAll(list);
        newList.addAll(set);
        return newList;
    }

    /**
     * 在字符串中判断并获取链接
     * @param content
     * @return
     */
    public static String getLink(String content){
        List<String> list=new ArrayList<>();
        String linkPtn = "<a\\s.+?</a>";
        String hrefPtn = "href\\s?=[\"\"']((http|https|ftp)[^\"\"']+)[\"\"'>]";
            Pattern pattern=Pattern.compile(linkPtn);
            Matcher matcher = pattern.matcher(content);
            if (matcher.find()) {
                Pattern pattern1 = Pattern.compile(hrefPtn);
                Matcher matcher1 = pattern1.matcher(content);
                if (matcher1.find()) {
                   return matcher1.group(1);
                }
        }
        return null;
    }
}
