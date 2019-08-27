package com.ruoyi.project.es.project.controller;

import com.google.common.collect.Lists;
import com.ruoyi.common.utils.ListUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.es.project.domain.Project;
import com.ruoyi.project.es.project.service.ProjectService;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Api("项目全文检索")
@Controller
@RequestMapping("/search/project")
public class ProjectController extends BaseController {
    @Resource
    private ProjectService projectService;

    @PostMapping("")
    @ResponseBody
    public TableDataInfo searchForProject(String key) {
        List<Project> projects;

        //空搜
        if (StringUtils.isEmpty(key)) {
            //应该是查询全部才对
            projects=projectService.findAll();
        }else { //有关键词

            //项目表匹配字段
            String[] fieldNames = new String[]{"project_aim", "project_type",
                    "host_unit", "responsible_person", "research_field",
                    "team", "require_num", "co_unit", "project_des", "undertake_unit",
                    "members", "name", "status"};
            String[] pinyinFieldNames = new String[]{"project_aim.pinyin", "project_type.pinyin",
                    "host_unit.pinyin", "responsible_person.pinyin", "research_field.pinyin",
                    "team.pinyin", "require_num.pinyin", "co_unit.pinyin", "project_des.pinyin",
                    "undertake_unit.pinyin", "members.pinyin", "name.pinyin", "status.pinyin"};
            //文件表匹配字段
            String remark = "remark";
            String remarkPinyin = "remark.pinyin";

            List<Project> projects1, projects2;

            //查询论文
            projects1 = projectService.findProject(key, fieldNames, fieldNames);
            projects2 = projectService.findProjectFromFiles(key, remark);

            //查询结果为空且为英文，则查询拼音字段
            if (projects1.size() == 0 && projects2.size() == 0) {
                projects1 = projectService.findProject(key, fieldNames, pinyinFieldNames);
                projects2 = projectService.findProjectFromFiles(key, remarkPinyin);
            }

            projects = Lists.newArrayList(projects1);
            projects.addAll(projects2);
            ListUtils.removeDuplicate(projects);
        }

        return getPage(projects);
    }
}

