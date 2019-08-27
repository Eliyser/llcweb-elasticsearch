package com.ruoyi.common.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author haien
 * @Description List工具类
 * @Date 2019/8/24
 **/
public class ListUtils {
    /**
     * @Author haien
     * @Description list去重，保持顺序
     * @Date 2019/8/24
     * @Param [list]
     * @return void
     **/
    public static void removeDuplicate(List list){
        Set set=new HashSet();
        List newList=new ArrayList();
        for (Object obj:list){
            if (set.add(obj)){
                newList.add(obj);
            }
        }
        list.clear();
        list.addAll(newList);
    }

    /**
     * @Author haien
     * @Description 把list添加到resultlist中
     * @Date 2019/8/25
     * @Param [resultList, list]
     * @return void
     **/
    public static void addAll(List resultList,List list){
        for (Object o:list)
            resultList.add(o);
    }
}






















