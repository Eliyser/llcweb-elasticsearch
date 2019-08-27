package com.ruoyi.project.es.patent.service;

import com.google.common.collect.Lists;
import com.ruoyi.common.utils.QueryUtils;
import com.ruoyi.project.es.patent.domain.Patent;
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
public class PatentServiceImpl implements PatentService {
    @Autowired
    private FilesRepository filesRepository;
    @Resource
    private QueryUtils queryUtils;

    @Override
    public List<Patent> findPatent(String term, String[] hfieldNames, String... fieldNames) {
        //多字段查询器
        QueryBuilder query=QueryBuilders.multiMatchQuery(term,fieldNames);
        //构造查询
        SearchQuery searchQuery=queryUtils.buildQuery(query,hfieldNames);
        //查询活动的index，匹配字段为：
        //"name","author","people_list","model","activity_type"
        return (List<Patent>)queryUtils.executeQuery(searchQuery,Patent.class);
    }

    @Override
    public List<Patent> findPatentFromFiles(String term, String fieldName) {
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
        List<Patent> patents1=findByIntroductionIn(ids);
        List<Patent> patents=findBySourceFileIn(ids);
        patents.addAll(patents1);

        return patents;
    }

    private List<Patent> findByIntroductionIn(List<Integer> introductions){
        QueryBuilder query=QueryBuilders.termsQuery("introduction",introductions);
        //构造查询
        SearchQuery searchQuery=queryUtils.buildQuery(query,null);
        //执行查询
        List<Patent> patents=new ArrayList<>();
        return (List<Patent>)queryUtils.executeQuery(searchQuery,Patent.class);
    }

    private List<Patent> findBySourceFileIn(List<Integer> sourceFiles){
        //查询器
        QueryBuilder query=QueryBuilders.termsQuery("source_file",sourceFiles);
        //构造查询
        SearchQuery searchQuery=queryUtils.buildQuery(query,null);
        //执行查询
        return (List<Patent>)queryUtils.executeQuery(searchQuery,Patent.class);
    }

    @Override
    public List<Patent> findAll() {
        QueryBuilder query=QueryBuilders.matchAllQuery();
        //构造查询
        SearchQuery searchQuery=queryUtils.buildQuery(query,null);
        //执行查询
        return (List<Patent>)queryUtils.executeQuery(searchQuery,Patent.class);
    }

}























