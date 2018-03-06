package com.baby.common.utils.email;

/**
 * 发送公司内部邮件实体
 * Created by SEELE on 2017/10/18.
 */
public class SendInsideEmail {

    /**
     * 发送公司内部方法生成body
     * @param taskId 任务id
     * @param Subject 主题
     * @param exceptingMsg 异常
     * @param className 类名
     * @param method 方法名
     * @return
     */
    public static String  getEmailTaskName(Integer taskId,String Subject,String exceptingMsg,String className,String method){

        StringBuffer s=new  StringBuffer();
        s.append("任务id:");
        s.append(taskId.toString());
        s.append(",主题:");
        s.append(Subject);
        s.append(",异常信息:");
        s.append(exceptingMsg);
        s.append(",类名:");
        s.append(className);
        s.append(",方法名:");
        s.append(method);
        return s.toString();
    }

}
