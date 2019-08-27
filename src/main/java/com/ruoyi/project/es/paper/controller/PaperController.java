package com.ruoyi.project.es.paper.controller;

import com.google.common.collect.Lists;
import com.ruoyi.common.utils.ListUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.es.paper.domain.Paper;
import com.ruoyi.project.es.paper.service.PaperService;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Api("论文全文检索")
@Controller
@RequestMapping("/search/paper")
public class PaperController extends BaseController {
    @Resource
    private PaperService paperService;

    @PostMapping("")
    @ResponseBody
    public TableDataInfo searchForPaper(String key) {
        List<Paper> papers;

        //空搜
        if (StringUtils.isEmpty(key)) {
            //应该是查询全部才对
            papers=paperService.findAll();
        }else { //有关键词

            //论文表匹配字段
            String[] fieldNames = new String[]{"belong_project", "periodical",
                    "model", "author_list", "title", "periodical_type"};
            String[] pinyinFieldNames = new String[]{"belong_project.pinyin",
                    "periodical.pinyin", "model.pinyin", "author_list.pinyin",
                    "title.pinyin", "periodical_type.pinyin"};
            //文件表匹配字段
            String remark = "remark";
            String remarkPinyin = "remark.pinyin";

            List<Paper> papers1, papers2;

            //查询论文
            papers1 = paperService.findPaper(key, fieldNames, fieldNames);
            papers2 = paperService.findPaperFromFiles(key, remark);

            //查询结果为空且为英文，则查询拼音字段
            if (papers1.size() == 0 && papers2.size() == 0) {
                papers1 = paperService.findPaper(key, fieldNames, pinyinFieldNames);
                papers2 = paperService.findPaperFromFiles(key, remarkPinyin);
            }

            papers = Lists.newArrayList(papers1);
            papers.addAll(papers2);
            ListUtils.removeDuplicate(papers);
        }

        return getPage(papers);
    }
}

