package com.ruoyi.project.es.activity.service;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.project.es.activity.domain.Activity;
import com.ruoyi.project.system.files.domain.Files;
import com.ruoyi.project.system.files.service.FilesRepository;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivityServiceImplTest {

    @Resource
    ActivityService activityService;
    @Resource
    FilesRepository filesRepository;

    @Test
    public void findActivityFromFiles(){
        String[] fieldNames=new String[]{"name","author",
                "people_list","model","activity_type"};
        List<Activity> activities=activityService.findActivity(
                "实验室",fieldNames,fieldNames);
        System.out.println(activities.get(0));
    }

    @Test
    public void test(){
        NativeSearchQueryBuilder queryBuilder= new NativeSearchQueryBuilder();

        //查询器
        queryBuilder.withQuery(QueryBuilders.matchQuery("remark","test"));

        //分页
        queryBuilder.withPageable(PageRequest.of(0,5));

        Page<Files> filesPage= filesRepository.search(queryBuilder.build());
        System.out.println(filesPage.getTotalElements());
    }

    public static void main(String[] args) {
        /*final Pattern SET_PATTERN = Pattern.compile("set\\p{javaUpperCase}\\w*");
        Matcher m = SET_PATTERN.matcher("setAgename");
        System.out.println(m.matches());*/

        String isPublish=StringUtils.convertToCamelCase("is_publish");
        System.out.println(isPublish);
    }
}