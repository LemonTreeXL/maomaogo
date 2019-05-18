package cn.itsource.maomaogo.Controller;

import cn.itsource.maomaogo.util.VelocityUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class TemplateController {

    @PostMapping("/page")
    public void createStaticPage(@RequestBody Map<String,Object> params){
        //如果三个参数都打印成功，则表示这个接口调用成功
//        System.out.println(model);
//        System.out.println("templatePath:"+templatePath);
//        System.out.println("targetPath:"+targetPath);

        Object model = params.get("model");
        String templatePath = (String) params.get("templatePath");
        String targetPath = (String) params.get("targetPath");

        VelocityUtils.staticByTemplate(model,templatePath,targetPath);
    }

}
