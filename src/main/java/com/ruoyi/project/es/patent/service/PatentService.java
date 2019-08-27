package com.ruoyi.project.es.patent.service;

import com.ruoyi.project.es.patent.domain.Patent;

import java.util.List;

public interface PatentService {
    /**
     * @Author haien
     * @Description 根据关键词搜索论文
     * @Date 2019/8/20
     * @Param [key]
     * @return java.util.List<com.haien.llcweb.activity.domain.Activity>
     **/
    public List<Patent> findPatent(String term, String[] hfieldNames, String... fieldNames);

    public List<Patent> findPatentFromFiles(String term, String fieldName);

    List<Patent> findAll();
}
