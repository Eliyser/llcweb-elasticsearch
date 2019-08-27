package com.ruoyi.project.monitor.druid;

import com.ruoyi.framework.web.controller.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * druid 监控
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/admin/monitor/data")
public class DruidController extends BaseController
{
    private String prefix = "/monitor/druid";

    @RequiresPermissions("monitor:data:view")
    @GetMapping()
    public String index()
    {
        return redirect("http://gdauto.gdut.edu.cn"+prefix + "/index");
    }

//    @RequiresPermissions("monitor:data:view")
//    @GetMapping()
//    public ModelAndView swagger() {
//        return new ModelAndView(new RedirectView(prefix + "/index"));
//    }
}
