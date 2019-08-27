package com.ruoyi.project.es.patent.controller;

import com.google.common.collect.Lists;
import com.ruoyi.common.utils.ListUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.es.patent.domain.Patent;
import com.ruoyi.project.es.patent.service.PatentService;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Api("专利全文检索")
@Controller
@RequestMapping("/search/patent")
public class PatentController extends BaseController {
    @Resource
    private PatentService patentService;

    /**
     * @return com.ruoyi.framework.web.page.TableDataInfo
     * @Author haien
     * @Description 根据关键词搜索活动、论文、专利、项目、软著
     * @Date 2019/8/20
     * @Param [key]
     **/
    @PostMapping()
    @ResponseBody
    public TableDataInfo searchForPatent(String key) {
        List<Patent> patents;

        //空搜
        if (StringUtils.isEmpty(key)) {
            //应该是查询全部才对
            patents=patentService.findAll();
        }else { //有关键词

            //活动表匹配字段
            String[] fieldNames = new String[]{"agency", "original_link", "author_list",
                    "title", "appli_num", "public_num", "model", "appli_people", "state"};
            String[] pinyinFieldNames = new String[]{"agency.pinyin", "original_link.pinyin",
                    "author_list.pinyin", "title.pinyin", "appli_num.pinyin",
                    "public_num.pinyin", "model.pinyin", "appli_people.pinyin", "state.pinyin"};
            //文件表匹配字段
            String remark = "remark";
            String remarkPinyin = "remark.pinyin";

            List<Patent> patents1, patents2;

            //查询活动
            patents1 = patentService.findPatent(key, fieldNames, fieldNames);
            patents2 = patentService.findPatentFromFiles(key, remark);

            //查询结果为空且为英文，则查询拼音字段
            if (patents1.size() == 0 && patents2.size() == 0) {
                patents1 = patentService.findPatent(key, fieldNames, pinyinFieldNames);
                patents2 = patentService.findPatentFromFiles(key, remarkPinyin);
            }

            patents = Lists.newArrayList(patents1);
            if (patents2 != null) {
                patents.addAll(patents2);
                //去重
                ListUtils.removeDuplicate(patents);
            }
        }

        return getPage(patents);
    }
}
