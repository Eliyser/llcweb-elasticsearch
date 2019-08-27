package com.ruoyi.project.es.paper.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Id;
import java.util.Date;

/**
 * 论文成果表 llc_paper
 *
 * @author ricardo
 * @date 2019-03-09
 */
@Document(indexName = "llc_paper",type = "_doc",
        indexStoreType = "fs",refreshInterval = "-1")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Paper extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**  */
    @Id
    private Integer id;
    /**
     * 题目
     */
    private String title;
    /**
     * 发表日期
     */
    @JsonProperty("public_date")
    private Date publicDate;
    /**
     * 论文介绍
     */
    private Integer introduction;
    /**
     * 作者列表
     */
    @JsonProperty("author_list")
    private String authorList;
    /**
     * 原文链接
     */
    @JsonProperty("original_link")
    private String originalLink;
    /**
     * 论文文件
     */
    @JsonProperty("source_lile")
    private Integer sourceFile;
    /**
     * 所属项目id
     */
    @JsonProperty("belong_project")
    private String belongProject;
    /**
     * 发表期刊
     */
    private String periodical;
    /**
     * 期刊类别
     */
    @JsonProperty("periodical_type")
    private String periodicalType;
    /**
     * 当前状态
     */
    private String state;
    /**
     * 项目组
     */
    private String model;
    /**
     * 是否发布
     */
    @JsonProperty("is_publish")
    private Integer isPublish;
    /**
     * 更新时间
     */
    @JsonProperty("update_time")
    private Date updateTime;

    public Paper() {
        this.updateTime = new Date();
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setPublicDate(Date publicDate) {
        this.publicDate = publicDate;
    }

    public Date getPublicDate() {
        return publicDate;
    }

    public void setIntroduction(Integer introduction) {
        this.introduction = introduction;
    }

    public Integer getIntroduction() {
        return introduction;
    }

    public void setAuthorList(String authorList) {
        this.authorList = authorList;
    }

    public String getAuthorList() {
        return authorList;
    }

    public void setOriginalLink(String originalLink) {
        this.originalLink = originalLink;
    }

    public String getOriginalLink() {
        return originalLink;
    }

    public void setSourceFile(Integer sourceFile) {
        this.sourceFile = sourceFile;
    }

    public Integer getSourceFile() {
        return sourceFile;
    }

    public void setBelongProject(String belongProject) {
        this.belongProject = belongProject;
    }

    public String getBelongProject() {
        return belongProject;
    }

    public void setPeriodical(String periodical) {
        this.periodical = periodical;
    }

    public String getPeriodical() {
        return periodical;
    }

    public void setPeriodicalType(String periodicalType) {
        this.periodicalType = periodicalType;
    }

    public String getPeriodicalType() {
        return periodicalType;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public void setIsPublish(Integer isPublish) {
        this.isPublish = isPublish;
    }

    public Integer getIsPublish() {
        return isPublish;
    }
    @Override
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    @Override
    public Date getUpdateTime() {
        return updateTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id" , getId())
                .append("title" , getTitle())
                .append("publicDate" , getPublicDate())
                .append("introduction" , getIntroduction())
                .append("authorList" , getAuthorList())
                .append("originalLink" , getOriginalLink())
                .append("sourceFile" , getSourceFile())
                .append("belongProject" , getBelongProject())
                .append("periodical" , getPeriodical())
                .append("periodicalType" , getPeriodicalType())
                .append("state" , getState())
                .append("model" , getModel())
                .append("isPublish" , getIsPublish())
                .append("updateTime" , getUpdateTime())
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Paper that=(Paper) o;
        return that.getId().equals(this.getId());
    }

    @Override
    public int hashCode() {
        final int prime=31;
        return prime+((this.getId()==null)?0:this.getId().hashCode());
    }
}
