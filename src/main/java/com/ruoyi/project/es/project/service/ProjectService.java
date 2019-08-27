package com.ruoyi.project.es.project.service;

import com.ruoyi.project.es.project.domain.Project;

import java.util.List;

public interface ProjectService {
    /**
     * @Author haien
     * @Description 根据关键词搜索论文
     * @Date 2019/8/20
     * @Param [key]
     * @return java.util.List<com.haien.llcweb.activity.domain.Activity>
     **/
    public List<Project> findProject(String term, String[] hfieldNames, String... fieldNames);

    public List<Project> findProjectFromFiles(String term, String fieldName);

    public List<Project> findAll();
}
