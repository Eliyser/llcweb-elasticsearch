package com.ruoyi.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.ScoreSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class QueryUtils {

    @Resource
    ElasticsearchTemplate elasticsearchTemplate;

    public List<?> executeQuery(SearchQuery searchQuery, Class<?> pojoClass){

        Page<?> resultPage=elasticsearchTemplate.queryForPage(
                searchQuery, pojoClass, new SearchResultMapper(){

                    @Override
                    public <T> AggregatedPage<T> mapResults(
                            SearchResponse response, Class<T> aClass, Pageable pageable) {

                        List<T> resultList=new ArrayList<>();

                        SearchHits searchHits=response.getHits();
                        SearchHit[] hits=searchHits.getHits();
                        ObjectMapper mapper=new ObjectMapper();
                        for (int i=0;i<hits.length;i++){
                            //获取文档
                            SearchHit hit=hits[i];
                            //将文档中每一个对象转为json串
                            String json=hit.getSourceAsString();
                            //将json串转为对象
                            T result= null;
                            try {
                                result = mapper.readValue(json,aClass);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            //获取高亮字段
                            Map<String,HighlightField> highMap=hit.getHighlightFields();
                            if(null!=highMap){

                                for (String highKey:highMap.keySet()){
                                    //获取字段名
                                    String fieldName=highMap.get(highKey).getName();
                                    //获取字段值片段
                                    Text highText=(highMap.get(highKey).fragments())[0];
                                    //反射调用setter将高亮属性设置进去
                                    String setMethodName="set"+
                                            StringUtils.convertToCamelCase(fieldName);
                                    try {
                                        Method setMethod=
                                                aClass.getMethod(setMethodName,String.class);
                                        setMethod.invoke(result,highText.toString());
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }

                            resultList.add(result);
                        }
                        if(resultList.size()>0){
                            return new AggregatedPageImpl<T>(resultList);
                        }
                        return null;
                    }
                });

        //为null返回新对象，防止外层NPE
        return resultPage==null?new ArrayList<>():resultPage.getContent();
    }

    public SearchQuery buildQuery(QueryBuilder queryBuilder,String[] hfieldNames){

        NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder();

        if(hfieldNames!=null){
            //字段高亮
            HighlightBuilder.Field[] hfields=new HighlightBuilder.Field[hfieldNames.length];
            for (int i=0;i<hfieldNames.length;i++){
                hfields[i]=new HighlightBuilder.Field(hfieldNames[i]).fragmentSize(250)
                        .preTags("<em style='color:red'>").postTags("</em>");
            }

            //高亮查询
            searchQueryBuilder.withHighlightFields(hfields);
        }

        // 按查询评分降序
        searchQueryBuilder.withSort(new ScoreSortBuilder().order(SortOrder.DESC));

        //分页查询
        searchQueryBuilder.withPageable(PageRequest.of(0,10000));

        //查询器
        searchQueryBuilder.withQuery(queryBuilder);

        return searchQueryBuilder.build();
    }
}
