package com.ruoyi.project.es.software.domain;

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
 * 软著成果表 llc_software
 *
 * @author ricardo
 * @date 2019-03-09
 */
@Document(indexName = "llc_software",type = "_doc",
        indexStoreType = "fs",refreshInterval = "-1")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Software extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @Id
    private Integer id;
    /**
     * 名称
     */
    private String title;
    /**
     * 申请日
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    @JsonProperty("appli_date")
    private Date appliDate;
    /**
     * 摘要
     */
    private String introduction;
    /**
     * 发明人列表
     */
    @JsonProperty("author_list")
    private String authorList;
    /**
     * 关联的文件id
     */
    @JsonProperty("source_file")
    private Integer sourceFile;
    /**
     * 所属项目
     */
    @JsonProperty("belong_project")
    private String belongProject;
    /**
     * 公开号
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
     * 状态
     */
    private String status;
    /**
     * 权利取得方式
     */
    @JsonProperty("public_method")
    private String publicMethod;
    /**
     * 权利范围
     */
    @JsonProperty("right_range")
    private String rightRange;
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

    public Software() {
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

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setAuthorList(String authorList) {
        this.authorList = authorList;
    }

    public String getAuthorList() {
        return authorList;
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

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setPublicMethod(String publicMethod) {
        this.publicMethod = publicMethod;
    }

    public String getPublicMethod() {
        return publicMethod;
    }

    public void setRightRange(String rightRange) {
        this.rightRange = rightRange;
    }

    public String getRightRange() {
        return rightRange;
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
                .append("sourceFile" , getSourceFile())
                .append("belongProject" , getBelongProject())
                .append("publicNum" , getPublicNum())
                .append("publicDate" , getPublicDate())
                .append("status" , getStatus())
                .append("publicMethod" , getPublicMethod())
                .append("rightRange" , getRightRange())
                .append("isPublish" , getIsPublish())
                .append("updateTime" , getUpdateTime())
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Software that=(Software) o;
        return that.getId().equals(this.getId());
    }

    @Override
    public int hashCode() {
        final int prime=31;
        return prime+((this.getId()==null)?0:this.getId().hashCode());
    }
}
