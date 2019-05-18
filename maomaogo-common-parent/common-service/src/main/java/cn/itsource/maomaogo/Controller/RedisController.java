package cn.itsource.maomaogo.Controller;

import cn.itsource.maomaogo.util.RedisUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {

    //ribbon调用使用restTemplate   feign调用需要feign的接口

    /**
     * 添加数据到redis中
     * @param key
     * @param value
     */
    @PostMapping("/redis")
    public void set(String key,String value){
        //调用工具类的方法set 保存到 redis数据库中
        RedisUtils.INSTANCE.set(key,value);
    }

    /**
     * 根据key获取value
     * @param key
     * @return
     */
    @GetMapping("/redis")
    public String get(String key){
        //调用工具类的方法get 从redis数据库中。通过key取值
        return RedisUtils.INSTANCE.get(key);
    }


}
