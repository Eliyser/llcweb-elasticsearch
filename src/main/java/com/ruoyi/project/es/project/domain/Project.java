package com.ruoyi.project.es.project.domain;

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
 * 项目管理表 llc_project
 *
 * @author ricardo
 * @date 2019-03-09
 */
@Document(indexName = "llc_project",type = "_doc",
        indexStoreType = "fs",refreshInterval = "-1")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Project extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @Id
    private Integer id;
    /**
     * 项目名称
     */
    private String name;
    /**
     * 项目编号
     */
    @JsonProperty("require_num")
    private String requireNum;
    /**
     * 项目介绍
     */
    private Integer introduction;
    /**
     * 介绍文件
     */
    @JsonProperty("introduction_file")
    private Integer introductionFile;
    /**
     * 项目状态
     */
    private String status;
    /**
     * 研究领域
     */
    @JsonProperty("research_field")
    private String researchField;
    /**
     * 项目经费
     */
    private String money;
    /**
     * 开始时间
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    @JsonProperty("start_date")
    private Date startDate;
    /**
     * 结束时间
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    @JsonProperty("end_date")
    private Date endDate;
    /**
     * 项目负责人
     */
    @JsonProperty("responsible_person")
    private String responsiblePerson;
    /**
     * 项目类型
     */
    @JsonProperty("project_type")
    private String projectType;
    /**
     * 项目人员
     */
    private String members;
    /**
     * 项目描述
     */
    @JsonProperty("project_des")
    private String projectDes;
    /**
     * 项目目标
     */
    @JsonProperty("project_aim")
    private String projectAim;
    /**
     * 主办单位
     */
    @JsonProperty("host_unit")
    private String hostUnit;
    /**
     * 协办单位
     */
    @JsonProperty("co_unit")
    private String coUnit;
    /**
     * 承办单位
     */
    @JsonProperty("undertake_unit")
    private String undertakeUnit;
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
    /**
     * 所属类别：用于门户分类
     */
    private String team;

    public Project() {
        this.updateTime = new Date();
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
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

    public void setRequireNum(String requireNum) {
        this.requireNum = requireNum;
    }

    public String getRequireNum() {
        return requireNum;
    }

    public void setIntroduction(Integer introduction) {
        this.introduction = introduction;
    }

    public Integer getIntroduction() {
        return introduction;
    }

    public void setIntroductionFile(Integer introductionFile) {
        this.introductionFile = introductionFile;
    }

    public Integer getIntroductionFile() {
        return introductionFile;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setResearchField(String researchField) {
        this.researchField = researchField;
    }

    public String getResearchField() {
        return researchField;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getMoney() {
        return money;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
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

    public void setResponsiblePerson(String responsiblePerson) {
        this.responsiblePerson = responsiblePerson;
    }

    public String getResponsiblePerson() {
        return responsiblePerson;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setMembers(String members) {
        this.members = members;
    }

    public String getMembers() {
        return members;
    }

    public void setProjectDes(String projectDes) {
        this.projectDes = projectDes;
    }

    public String getProjectDes() {
        return projectDes;
    }

    public void setProjectAim(String projectAim) {
        this.projectAim = projectAim;
    }

    public String getProjectAim() {
        return projectAim;
    }

    public void setHostUnit(String hostUnit) {
        this.hostUnit = hostUnit;
    }

    public String getHostUnit() {
        return hostUnit;
    }

    public void setCoUnit(String coUnit) {
        this.coUnit = coUnit;
    }

    public String getCoUnit() {
        return coUnit;
    }

    public void setUndertakeUnit(String undertakeUnit) {
        this.undertakeUnit = undertakeUnit;
    }

    public String getUndertakeUnit() {
        return undertakeUnit;
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
                .append("name" , getName())
                .append("requireNum" , getRequireNum())
                .append("introduction" , getIntroduction())
                .append("introductionFile" , getIntroductionFile())
                .append("status" , getStatus())
                .append("researchField" , getResearchField())
                .append("money" , getMoney())
                .append("startDate" , getStartDate())
                .append("endDate" , getEndDate())
                .append("responsiblePerson" , getResponsiblePerson())
                .append("projectType" , getProjectType())
                .append("members" , getMembers())
                .append("projectDes" , getProjectDes())
                .append("projectAim" , getProjectAim())
                .append("hostUnit" , getHostUnit())
                .append("coUnit" , getCoUnit())
                .append("undertakeUnit" , getUndertakeUnit())
                .append("isPublish" , getIsPublish())
                .append("updateTime" , getUpdateTime())
                .append("team",getTeam())
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Project that=(Project) o;
        return that.getId().equals(this.getId());
    }

    @Override
    public int hashCode() {
        final int prime=31;
        return prime+((this.getId()==null)?0:this.getId().hashCode());
    }
}
