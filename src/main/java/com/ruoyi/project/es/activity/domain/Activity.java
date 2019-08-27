package com.ruoyi.project.es.activity.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;

@Document(indexName = "llc_activity",type = "_doc",
        indexStoreType = "fs",refreshInterval = "-1")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Activity extends BaseEntity {
    @Id
    private Integer id;
    /**
     * 活动名称
     */
    private String name;
    /**
     * 活动发布者
     */
    private String author;
    /**
     * 活动参与者
     */
    @JsonProperty("people_list")
    private String peopleList;
    /**
     * 开始时间
     */
    @JsonProperty("start_date")
    private Date startDate;
    /**
     * 结束时间
     */
    @JsonProperty("end_date")
    private Date endDate;
    /**
     * 活动简介
     */
    private Integer introduction;
    /**
     * 组别
     */
    private String model;
    /**
     * 活动类型,通知、会议、招聘、活动等
     */
    @JsonProperty("activity_type")
    private String activityType;
    /**
     * 是否发布出去
     */
    @JsonProperty("is_publish")
    private Integer isPublish;

    public Activity() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setPeopleList(String peopleList) {
        this.peopleList = peopleList;
    }

    public String getPeopleList() {
        return peopleList;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setIntroduction(Integer introduction) {
        this.introduction = introduction;
    }

    public Integer getIntroduction() {
        return introduction;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setIsPublish(Integer isPublish) {
        this.isPublish = isPublish;
    }

    public Integer getIsPublish() {
        return isPublish;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id" , getId())
                .append("name" , getName())
                .append("author" , getAuthor())
                .append("peopleList" , getPeopleList())
                .append("startDate" , getStartDate())
                .append("endDate" , getEndDate())
                .append("introduction" , getIntroduction())
                .append("model" , getModel())
                .append("activityType" , getActivityType())
                .append("isPublish" , getIsPublish())
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Activity that=(Activity) o;
        return that.getId().equals(this.getId());
    }

    @Override
    public int hashCode() {
        final int prime=31;
        return prime+((this.getId()==null)?0:this.getId().hashCode());
    }
}