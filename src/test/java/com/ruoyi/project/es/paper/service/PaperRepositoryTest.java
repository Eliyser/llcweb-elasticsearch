package com.ruoyi.project.es.paper.service;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PaperRepositoryTest {

    @Resource
    PaperService paperService;
/*

    @Test
    public void findByIntroductionIn(){
        List<Paper> papers1=new ArrayList<>();
        Paper paper=new Paper();
        paper.setId(112);
        papers1.add(paper);

        List<Integer> ids=new ArrayList<>();
        ids.add(1);
        //repository得到的list不能执行List类的所有方法
        List<Paper> papers=paperRepository.findByIntroductionIn(ids);

        //报错：java.lang.UnsupportedOperationException: null;只能别人加它，不能它加别人
        papers.addAll(papers1);
    }
*/

    /*@Test
    public void test(){
        List<Integer> ids=new ArrayList<>();
        ids.add(116);
        ids.add(120);
        ids.add(122);
        ids.add(123);
        ids.add(141);
        ids.add(144);
        ids.add(146);
        ids.add(150);
        ids.add(156);
        ids.add(152);
        ids.add(157);
        ids.add(119);
        ids.add(123);
        ids.add(127);
        ids.add(130);
        ids.add(132);
        ids.add(135);
        ids.add(140);
        ids.add(143);
        ids.add(148);
        ids.add(124);
        ids.add(131);
        ids.add(155);
        ids.add(160);
        ids.add(164);
        ids.add(167);
        ids.add(171);
        List<Paper> papers=paperRepository.findByIntroductionIn(ids);
        System.out.println(papers.size());
    }*/
}