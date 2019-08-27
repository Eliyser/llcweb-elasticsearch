package com.ruoyi.project.es.activity.service;

import com.ruoyi.project.es.activity.domain.Activity;

import java.util.List;

public interface ActivityService {
    /**
     * @Author haien
     * @Description 根据关键词搜索活动
     * @Date 2019/8/20
     * @Param [key]
     * @return java.util.List<com.haien.llcweb.activity.domain.Activity>
     **/
    public List<Activity> findActivity(
            String term,String[] hfieldNames,String... fieldNames);

    /**
     * @Author haien
     * @Description 匹配sys_files文件表remark字段,返查活动表
     * @Date 2019/8/24
     * @Param [term, pageNum, pageSize, fieldName]
     * @return java.util.List<com.ruoyi.project.es.activity.domain.Activity>
     **/
    public List<Activity> findActivityFromFiles(String term,String fieldName);

    List<Activity> findAll();
}
