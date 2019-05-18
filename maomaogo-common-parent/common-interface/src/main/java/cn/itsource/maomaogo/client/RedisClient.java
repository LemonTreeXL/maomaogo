package cn.itsource.maomaogo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "MAOMAOGO-COMMON")//对应的服务名称
@Component
public interface RedisClient {


    /**
     *  fegin接口 暴露的服务【也就是controller中的方法】
     * @param key  redis数据库是以 key-vlaue来存值的。高并发。读写效率高.支持的文件类型多。可以持久性到磁盘中
     * @param -value
     * @RequestParam 参数绑定。多个参数时使用该注解
     */
    @PostMapping("/redis")
    void set(@RequestParam("key") String key, @RequestParam("value") String value);

    @GetMapping("/redis")
    String get(@RequestParam("key") String key);


}
