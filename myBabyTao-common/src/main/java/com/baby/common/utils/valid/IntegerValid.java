package com.baby.common.utils.valid;

/**
 * Created by SEELE on 2017/11/2.
 */
public class IntegerValid {

    public static boolean isBlankInteger(Integer value){
         boolean flag=false;
        if(value==null){
            flag=true;
        }
        else{
            flag=false;
        }
        return flag;
    }

}
