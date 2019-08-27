package com.ruoyi.project.es.activity.service;

import com.google.common.collect.Lists;
import com.ruoyi.common.utils.QueryUtils;
import com.ruoyi.project.es.activity.domain.Activity;
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
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private FilesRepository filesRepository;
    @Resource
    private QueryUtils queryUtils;

    /**
     * @Author haien
     * @Description 多字段匹配，分页高亮查询
     * @Date 2019/8/22
     * @Param [term关键词, pageNum, pageSize, hfieldNames高亮字段, fieldNames查询字段]
     * @return org.springframework.data.domain.Page<com.ruoyi.project.es.activity.domain.Activity>
     **/
    @Override
    public List<Activity> findActivity(
            String term,String[] hfieldNames,String... fieldNames){

        //多字段查询器
        QueryBuilder query=QueryBuilders.multiMatchQuery(term,fieldNames);
        //构造查询
        SearchQuery searchQuery=queryUtils.buildQuery(query,hfieldNames);
        //执行查询
        return (List<Activity>)queryUtils.executeQuery(searchQuery,Activity.class);
    }

    /**
     * @Author haien
     * @Description 匹配sys_files文件表remark字段,返查活动表
     * @Date 2019/8/23
     * @Param []
     * @return org.springframework.data.domain.Page<com.ruoyi.project.es.activity.domain.Activity>
     **/
    @Override
    public List<Activity> findActivityFromFiles(String term,String fieldName){
        //查询器
        QueryBuilder query=QueryBuilders.matchQuery(fieldName,term);
        //构造查询
        SearchQuery searchQuery=queryUtils.buildQuery(query,null);
        //执行查询
        List<Files> filesList=Lists.newArrayList(
                filesRepository.search(searchQuery));

        if(filesList.size()==0)
            return new ArrayList<>();

        //返查活动表
        List<Integer> ids=new ArrayList<>();
        for (Files files:filesList){
            ids.add(files.getId());
        }
        return findByIntroductionIn(ids);
    }

    public List<Activity> findByIntroductionIn(List<Integer> introductions){
        QueryBuilder query=QueryBuilders.termsQuery("introduction",introductions);
        //构造查询
        SearchQuery searchQuery=queryUtils.buildQuery(query,null);
        //执行查询
        return (List<Activity>)queryUtils.executeQuery(searchQuery,Activity.class);
    }

    @Override
    public List<Activity> findAll() {
        QueryBuilder query=QueryBuilders.matchAllQuery();
        //构造查询
        SearchQuery searchQuery=queryUtils.buildQuery(query,null);
        //执行查询
        return (List<Activity>)queryUtils.executeQuery(searchQuery,Activity.class);
    }
}























