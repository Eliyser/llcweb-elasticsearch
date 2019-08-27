package com.ruoyi.project.es.patent.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Id;
import java.util.Date;

/**
 * 专利成果表 llc_patent
 *
 * @author ricardo
 * @date 2019-03-09
 */
@Document(indexName = "llc_patent",type = "_doc",
        indexStoreType = "fs",refreshInterval = "-1")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Patent extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**  */
    @Id
    private Integer id;
    /**
     * 专利标题
     */
    private String title;
    /**
     * 申请日
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    @JsonProperty("appli_date")
    private Date appliDate;
    /**
     * 专利介绍
     */
    private Integer introduction;
    /**
     * 作者姓名列表
     */
    @JsonProperty("author_list")
    private String authorList;
    /**
     * 原文链接
     */
    @JsonProperty("original_link")
    private String originalLink;
    /**
     * 关联的文件id
     */
    @JsonProperty("source_file")
    private Integer sourceFile;
    /**
     * 专利申请号
     */
    @JsonProperty("appli_num")
    private String appliNum;
    /**
     * 申请人（一般为广东工业大学）
     */
    @JsonProperty("appli_people")
    private String appliPeople;
    /**
     * 申请日期
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    @JsonProperty("application_date")
    private Date applicationDate;
    /**
     * 专利公开号
     */
    @JsonProperty("public_num")
    private String publicNum;
    /**
     * 公开日
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    @JsonProperty("public_date")
    private Date publicDate;
    /**
     * 授权日期
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    @JsonProperty("authorization_date")
    private Date authorizationDate;
    /**
     * 代理机构
     */
    private String agency;
    /**
     * 专利状态
     */
    private String state;
    /**
     * 组别
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

    public Patent() {
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

    public void setAppliDate(Date appliDate) {
        this.appliDate = appliDate;
    }

    public Date getAppliDate() {
        return appliDate;
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

    public void setAppliNum(String appliNum) {
        this.appliNum = appliNum;
    }

    public String getAppliNum() {
        return appliNum;
    }

    public void setAppliPeople(String appliPeople) {
        this.appliPeople = appliPeople;
    }

    public String getAppliPeople() {
        return appliPeople;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setPublicNum(String publicNum) {
        this.publicNum = publicNum;
    }

    public String getPublicNum() {
        return publicNum;
    }

    public void setPublicDate(Date publicDate) {
        this.publicDate = publicDate;
    }

    public Date getPublicDate() {
        return publicDate;
    }

    public void setAuthorizationDate(Date authorizationDate) {
        this.authorizationDate = authorizationDate;
    }

    public Date getAuthorizationDate() {
        return authorizationDate;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getAgency() {
        return agency;
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
                .append("appliDate" , getAppliDate())
                .append("introduction" , getIntroduction())
                .append("authorList" , getAuthorList())
                .append("originalLink" , getOriginalLink())
                .append("sourceFile" , getSourceFile())
                .append("appliNum" , getAppliNum())
                .append("appliPeople" , getAppliPeople())
                .append("applicationDate" , getApplicationDate())
                .append("publicNum" , getPublicNum())
                .append("publicDate" , getPublicDate())
                .append("authorizationDate" , getAuthorizationDate())
                .append("agency" , getAgency())
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

        Patent that=(Patent) o;
        return that.getId().equals(this.getId());
    }

    @Override
    public int hashCode() {
        final int prime=31;
        return prime+((this.getId()==null)?0:this.getId().hashCode());
    }
}
