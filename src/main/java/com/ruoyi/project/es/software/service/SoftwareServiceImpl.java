package com.ruoyi.project.es.software.service;

import com.google.common.collect.Lists;
import com.ruoyi.common.utils.QueryUtils;
import com.ruoyi.project.es.software.domain.Software;
import com.ruoyi.project.system.files.domain.Files;
import com.ruoyi.project.system.files.service.FilesRepository;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class SoftwareServiceImpl implements SoftwareService {
    @Autowired
    private FilesRepository filesRepository;
    @Resource
    private QueryUtils queryUtils;

    @Override
    public List<Software> findSoftware(
            String term, String[] hfieldNames, String... fieldNames) {
        //多字段查询器
        QueryBuilder query=QueryBuilders.multiMatchQuery(term,fieldNames);
        //构造查询
        SearchQuery searchQuery=queryUtils.buildQuery(query,hfieldNames);
        //查询活动的index，匹配字段为：
        //"name","author","people_list","model","activity_type"
        return (List<Software>)queryUtils.executeQuery(searchQuery,Software.class);
    }

    @Override
    public List<Software> findSoftwareFromFiles(String term, String fieldName) {
        //查询器
        QueryBuilder query=QueryBuilders.matchQuery(fieldName,term);
        //构造查询
        SearchQuery searchQuery=queryUtils.buildQuery(query,null);
        //执行查询
        List<Files> filesList=Lists.newArrayList(
                filesRepository.search(searchQuery));

        if(filesList.size()==0){
            return new ArrayList<>();
        }

        //返查活动表
        List<Integer> ids=new ArrayList<>();
        for (Files files:filesList){
            ids.add(files.getId());
        }
        List<Software> softwares=findBySourceFileIn(ids);

        return softwares;
    }

    private List<Software> findBySourceFileIn(List<Integer> sourceFiles){
        //查询器
        QueryBuilder query=QueryBuilders.termsQuery("source_file",sourceFiles);
        //构造查询
        SearchQuery searchQuery=queryUtils.buildQuery(query,null);
        //执行查询
        return (List<Software>)queryUtils.executeQuery(searchQuery,Software.class);
    }

    @Override
    public List<Software> findAll() {
        QueryBuilder query=QueryBuilders.matchAllQuery();
        //构造查询
        SearchQuery searchQuery=queryUtils.buildQuery(query,null);
        //执行查询
        return (List<Software>)queryUtils.executeQuery(searchQuery,Software.class);
    }

}























