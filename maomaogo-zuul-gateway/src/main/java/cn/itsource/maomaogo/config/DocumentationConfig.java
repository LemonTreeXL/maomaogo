package cn.itsource.maomaogo.config;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

@Component
@Primary
public class DocumentationConfig implements SwaggerResourcesProvider {
    @Override
    public List<SwaggerResource> get() {
        List resources = new ArrayList<>();
       //product,user等都是网关中配置路径,而且如果有前缀,需要加上
        resources.add(swaggerResource("商品管理服务", "/services/product/v2/api-docs", "2.0"));
//        resources.add(swaggerResource("用户管理服务", "/services/user/v2/api-docs", "2.0"));
        resources.add(swaggerResource("平台管理服务", "/services/plat/v2/api-docs", "2.0"));
        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }
}