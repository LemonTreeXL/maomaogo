package cn.itsource.maomaogo.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 分页的配置
 *
 * 调用service的page方法便可以了
 *
 * 如果你想要的自己去实现分页，在mapper层
 * mapper:
 * IPage<Employee> selectAll(Page page,EmployeeQuery query);
 * mybatisplus会自动任务你这个查询就是分页查询
 *
 * <select parameterType="EmployeeQuery">select * from xx where xxx=xxx and xxx= xxx</select>
 *
 *
 *
 *
 */
@Configuration
public class MybatisPlusConfig {

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
