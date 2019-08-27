package com.ruoyi.project.es.paper.service;

import com.google.common.collect.Lists;
import com.ruoyi.common.utils.QueryUtils;
import com.ruoyi.project.es.activity.domain.Activity;
import com.ruoyi.project.es.paper.domain.Paper;
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
public class PaperServiceImpl implements PaperService {
    @Autowired
    private FilesRepository filesRepository;
    @Resource
    private QueryUtils queryUtils;

    /**
     * @Author haien
     * @Description 多字段匹配，分页高亮查询
     * @Date 2019/8/24
     * @Param [term, hfieldNames, fieldNames]
     * @return java.lang.Iterable<com.ruoyi.project.es.activity.domain.Activity>
     **/
    @Override
    public List<Paper> findPaper(
            String term,String[] hfieldNames,String... fieldNames){

        //多字段查询器
        QueryBuilder query=QueryBuilders.multiMatchQuery(term,fieldNames);
        //构造查询
        SearchQuery searchQuery=queryUtils.buildQuery(query,hfieldNames);
        //查询活动的index，匹配字段为：
        //"name","author","people_list","model","activity_type"
        return (List<Paper>)queryUtils.executeQuery(searchQuery,Activity.class);
    }

    /**
     * @Author haien
     * @Description 匹配sys_files文件表remark字段,返查活动表
     * @Date 2019/8/23
     * @Param []
     * @return org.springframework.data.domain.Page<com.ruoyi.project.es.activity.domain.Activity>
     **/
    @Override
    public List<Paper> findPaperFromFiles(String term, String fieldName){
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
        List<Paper> papers1=findByIntroductionIn(ids);
        List<Paper> papers=findBySourceFileIn(ids);
        papers.addAll(papers1);

        return papers;
    }

    private List<Paper> findByIntroductionIn(List<Integer> introductions){
        QueryBuilder query=QueryBuilders.termsQuery("introduction",introductions);
        //构造查询
        SearchQuery searchQuery=queryUtils.buildQuery(query,null);
        //执行查询
        return (List<Paper>)queryUtils.executeQuery(searchQuery,Paper.class);
    }

    private List<Paper> findBySourceFileIn(List<Integer> sourceFiles){
        //查询器
        QueryBuilder query=QueryBuilders.termsQuery("source_file",sourceFiles);
        //构造查询
        SearchQuery searchQuery=queryUtils.buildQuery(query,null);
        //执行查询
        return (List<Paper>)queryUtils.executeQuery(searchQuery,Paper.class);
    }

    @Override
    public List<Paper> findAll() {
        QueryBuilder query=QueryBuilders.matchAllQuery();
        //构造查询
        SearchQuery searchQuery=queryUtils.buildQuery(query,null);
        //执行查询
        return (List<Paper>)queryUtils.executeQuery(searchQuery,Paper.class);
    }
}























