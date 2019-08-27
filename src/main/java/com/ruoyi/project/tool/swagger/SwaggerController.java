package com.ruoyi.project.tool.swagger;

import com.ruoyi.framework.web.controller.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * swagger 接口
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/admin/tool/swagger")
public class SwaggerController extends BaseController
{
    @RequiresPermissions("tool:swagger:view")
    @GetMapping()
    public String index()
    {
        return redirect("http://gdauto.gdut.edu.cn"+"/swagger-ui.html");
    }

//    @RequiresPermissions("tool:swagger:view")
//    @GetMapping()
//    public ModelAndView swagger() {
//        return new ModelAndView(new RedirectView("/swagger-ui.html"));
//    }
}
