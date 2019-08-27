package com.ruoyi.llcweb.activity.service.impl;

import com.ruoyi.project.es.activity.domain.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author haien
 * @Description 归并排序测试
 * @Date 2019/8/19
 **/
public class MergeSortTest {

    // 基础，合并两个有序数组
    public static List<Activity> merge2Arr(List<Activity> arr1, List<Activity> arr2) {
        int len1 = arr1.size();
        int len2 = arr2.size();
        List<Activity> res = new ArrayList(len1 + len2);

        int i = 0, j = 0, k = 0;
        while(i < len1 && j < len2) {
            //去重
            if(arr1.get(i).getId()==arr2.get(j).getId()) {
                j++;
            }else{
                res.add(arr1.get(i).getId() < arr2.get(j).getId() ? arr1.get(i++) : arr2.get(j++));
            }
        }
        while(i < len1) {
            res.add(arr1.get(i++));
        }
        while(j < len2) {
            res.add(arr2.get(j++));
        }
        return res;
    }

    public static void main(String[] args) {
        Activity activity1=new Activity();
        activity1.setId(2);
        Activity activity2=new Activity();
        activity2.setId(5);
        Activity activity3=new Activity();
        activity3.setId(10);
        Activity activity4=new Activity();
        activity4.setId(1);
        Activity activity5=new Activity();
        activity5.setId(5);
        Activity activity6=new Activity();
        activity6.setId(11);

        List<Activity> arr1=new ArrayList<>();
        arr1.add(activity1);
        arr1.add(activity2);
        arr1.add(activity3);
        List<Activity> arr2=new ArrayList<>();
        arr2.add(activity4);
        arr2.add(activity5);
        arr2.add(activity6);

        List<Activity> res = merge2Arr(arr1, arr2);
        res.forEach(System.out::println);
    }
}
