package cn.itsource.maomaogo.service;

import cn.itsource.maomaogo.ProductApplication;
import cn.itsource.maomaogo.client.RedisClient;
import cn.itsource.maomaogo.domain.ProductType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;



@SpringBootTest(classes = ProductApplication.class)
@RunWith(SpringRunner.class)
public class IBrandServiceTest {


    @Autowired
    private RedisClient redisClient;

    @Autowired
    private IProductTypeService productTypeService;

    @Test
    public void test() throws Exception{
        List<ProductType> list = productTypeService.list();
        for (ProductType productType : list) {
            System.out.println(productType);
        }
    }

    @Test
    public void redisClientSet() throws Exception{
        redisClient.set("username", "admin");
    }


    @Test
    public void redisClientGet() throws Exception{
        System.out.println(redisClient.get("username"));
    }
}