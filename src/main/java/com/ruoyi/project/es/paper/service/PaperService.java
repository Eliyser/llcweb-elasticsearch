package com.ruoyi.project.es.paper.service;

import com.ruoyi.project.es.paper.domain.Paper;

import java.util.List;

public interface PaperService {
    /**
     * @Author haien
     * @Description 根据关键词搜索论文
     * @Date 2019/8/20
     * @Param [key]
     * @return java.util.List<com.haien.llcweb.activity.domain.Activity>
     **/
    public List<Paper> findPaper(String term, String[] hfieldNames, String... fieldNames);

    public List<Paper> findPaperFromFiles(String term,String fieldName);

    List<Paper> findAll();
}
