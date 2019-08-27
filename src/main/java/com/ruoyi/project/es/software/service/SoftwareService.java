package com.ruoyi.project.es.software.service;

import com.ruoyi.project.es.software.domain.Software;

import java.util.List;

public interface SoftwareService {
    /**
     * @Author haien
     * @Description 根据关键词搜索软著
     * @Date 2019/8/20
     * @Param [key]
     * @return java.util.List<com.haien.llcweb.activity.domain.Activity>
     **/
    public List<Software> findSoftware(String term, String[] hfieldNames, String... fieldNames);

    public List<Software> findSoftwareFromFiles(String term, String fieldName);

    List<Software> findAll();
}
