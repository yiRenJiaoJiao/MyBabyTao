package com.baby.common.utils.collections;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by SEELE on 2017/8/29.
 */
public class CollectionsTool {

    public static boolean isListEmpty(List list) {
        if (list == null || list.size() == 0) {
            return true;
        }
        return false;
    }

    public static boolean isMapEmpty(Map map) {
        if (map == null || map.size() == 0) {
            return true;
        }
        return false;
    }

    //将List<String>转换成List<Integer>
    public static List<Integer> stringToIntegerList(List<String> inList){
        List<Integer> iList =new ArrayList<Integer>(inList.size());
        try{
            for(int i=0,j=inList.size();i<j;i++){
                iList.add(Integer.parseInt(inList.get(i)));
            }
        }catch(Exception  e){
        }
        return iList;
    }
}
