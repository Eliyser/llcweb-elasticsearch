package com.ruoyi.project.es.software.controller;

import com.google.common.collect.Lists;
import com.ruoyi.common.utils.ListUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.es.software.domain.Software;
import com.ruoyi.project.es.software.service.SoftwareService;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Api("软著全文检索")
@Controller
@RequestMapping("/search/software")
public class SoftwareController extends BaseController {
    @Resource
    private SoftwareService softwareService;

    /**
     * @return com.ruoyi.framework.web.page.TableDataInfo
     * @Author haien
     * @Description 根据关键词搜索活动、论文、专利、项目、软著
     * @Date 2019/8/20
     * @Param [key]
     **/
    @PostMapping()
    @ResponseBody
    public TableDataInfo searchForSoftware(String key) {
        List<Software> softwares;

        //空搜
        if (StringUtils.isEmpty(key)) {
            //应该是查询全部才对
            softwares=softwareService.findAll();
        }else { //有关键词

            //活动表匹配字段
            String[] fieldNames = new String[]{"introduction", "title", "author_list",
                    "public_num", "status", "public_method", "right_range"};
            String[] pinyinFieldNames = new String[]{"introduction.pinyin", "title.pinyin",
                    "author_list.pinyin", "public_num.pinyin", "status.pinyin",
                    "public_method.pinyin", "right_range.pinyin"};
            //文件表匹配字段
            String remark = "remark";
            String remarkPinyin = "remark.pinyin";

            List<Software> softwares1, softwares2;

            //查询活动
            softwares1 = softwareService.findSoftware(key, fieldNames, fieldNames);
            softwares2 = softwareService.findSoftwareFromFiles(key, remark);

            //查询结果为空且为英文，则查询拼音字段
            if (softwares1.size() == 0 && softwares2.size() == 0) {
                softwares1 = softwareService.findSoftware(key, fieldNames, pinyinFieldNames);
                softwares2 = softwareService.findSoftwareFromFiles(key, remarkPinyin);
            }

            softwares = Lists.newArrayList(softwares1);
            if (softwares2 != null) {
                softwares.addAll(softwares2);
                //去重
                ListUtils.removeDuplicate(softwares);
            }
        }

        return getPage(softwares);
    }
}
