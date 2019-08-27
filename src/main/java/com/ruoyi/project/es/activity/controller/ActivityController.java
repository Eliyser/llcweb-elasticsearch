package com.ruoyi.project.es.activity.controller;

import com.google.common.collect.Lists;
import com.ruoyi.common.utils.ListUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.es.activity.domain.Activity;
import com.ruoyi.project.es.activity.service.ActivityService;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Api("活动全文检索")
@Controller
@RequestMapping("/search/activity")
public class ActivityController extends BaseController {
    @Resource
    private ActivityService activityService;

    /**
     * @return com.ruoyi.framework.web.page.TableDataInfo
     * @Author haien
     * @Description 根据关键词搜索活动、论文、专利、项目、软著
     * @Date 2019/8/20
     * @Param [key]
     **/
    @PostMapping()
    @ResponseBody
    public TableDataInfo searchForActivity(String key) {
        List<Activity> activities;

        //空搜
        if (StringUtils.isEmpty(key)) {
            //应该是查询全部才对
            activities=activityService.findAll();
        }else { //有关键词

            //活动表匹配字段
            String[] fieldNames = new String[]{"name", "author",
                    "people_list", "model", "activity_type"};
            String[] pinyinFieldNames = new String[]{"name.pinyin", "author.pinyin",
                    "people_list.pinyin", "model.pinyin", "activity_type.pinyin"};
            //文件表匹配字段
            String remark = "remark";
            String remarkPinyin = "remark.pinyin";

            List<Activity> activities1, activities2;

            //查询活动
            activities1 = activityService.findActivity(
                    key, fieldNames, fieldNames);
            activities2 = activityService.findActivityFromFiles(key, remark);

            //查询结果为空且为英文，则查询拼音字段
            if (activities1.size() == 0 && activities2.size() == 0) {
                activities1 = activityService.findActivity(
                        key, fieldNames, pinyinFieldNames);
                activities2 = activityService.findActivityFromFiles(
                        key, remarkPinyin);
            }

            activities = Lists.newArrayList(activities1);
            if (activities2 != null) {
                activities.addAll(activities2);
                //去重
                ListUtils.removeDuplicate(activities);
            }
        }

        return getPage(activities);
    }
}
