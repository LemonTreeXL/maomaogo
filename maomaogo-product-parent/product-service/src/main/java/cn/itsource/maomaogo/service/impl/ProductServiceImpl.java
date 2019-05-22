package cn.itsource.maomaogo.service.impl;

import cn.itsource.maomaogo.domain.Product;
import cn.itsource.maomaogo.domain.ProductExt;
import cn.itsource.maomaogo.domain.Sku;
import cn.itsource.maomaogo.domain.Specification;
import cn.itsource.maomaogo.mapper.ProductExtMapper;
import cn.itsource.maomaogo.mapper.ProductMapper;
import cn.itsource.maomaogo.mapper.SkuMapper;
import cn.itsource.maomaogo.mapper.SpecificationMapper;
import cn.itsource.maomaogo.query.ProductQuery;
import cn.itsource.maomaogo.service.IProductService;
import cn.itsource.maomaogo.util.PageList;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * <p>
 * 商品 服务实现类
 * </p>
 *
 * @author LemonTreeXL
 * @since 2019-05-20
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {


    @Autowired
    private ProductExtMapper productExtMapper;

    @Autowired
    private SpecificationMapper specificationMapper;

    @Autowired
    private SkuMapper skuMapper;

    @Override
    public PageList<Product> getByQuery(ProductQuery query) {

        //需要一个page对象
        Page<Product> page = new Page<>(query.getPage(),query.getSize());
        IPage<Product> iPage = baseMapper.selectByQuery(page,query);
        //封装PageList
        return new PageList<Product>(iPage.getTotal(),iPage.getRecords());

    }


    /**
     * 把当前 商品的 显示属性 保存到该商品对应的【先查询出来。再把该商品的显示属性字段修改之后保存。修改操作】
     * viewProperties字段中【采用了反三范式NF，通过一定的数据冗余。来提高我的查询效率。不在连表查询】
     * @param specifications 封装了当前商品 对应的显示属性的值
     * @param productId 当前商品的id
     */
    @Override
    public void saveViewProperties(List<Specification> specifications, Long productId) {
        //先根据商品id查询出商品
        Product product = baseMapper.selectById(productId);
        //设置 创建时间
        product.setCreateTime(new Date().getTime());
        //修改domain的viewProperties属性
        //1 自定义要保存的属性
        //2 直接放入Specification
        // SimplePropertyPreFilter第二个参数：传入需要序列化属性的名称
        //SimplePropertyPreFilter filter = new SimplePropertyPreFilter(Specification.class, "id","specName","value");
        //把 前端传入json字符串,转化为json数组
        String jsonString = JSONArray.toJSONString(specifications);
        //保存到 显示属性中去
        product.setViewProperties(jsonString);
        //执行update方法
        baseMapper.updateById(product);
    }

    /**
     * 查询 显示属性 通过 商品类型id查询
     * @param productId
     * @return
     */
    @Override
    public List<Specification> getViewProperties(Long productId) {
        //1.先从 product商品表中获取【如果有说明 原来设置过显示属性值】
        Product product = baseMapper.selectOne(new QueryWrapper<Product>().eq("id", productId));
        //从通过商品id查询出的 商品对象 获取ViewProperties属性【显示属性】看是否存在
        String viewProperties = product.getViewProperties();
        //如果不存在表示 没有维护过 就是新增
        if (StringUtils.isEmpty(viewProperties)){
            //2.如果没有查询到。表示没有设置过显示属性值。从属性表中查询【通过商品表中的外键字段 商品类型id取查询】
            //获取 商品类型id 从商品属性表中查询 显示属性名
            Long productTypeId = product.getProductTypeId();
            //通过当前商品对应的商品id 查询到 该商品类型下的 显示属性【通用属性】
            List<Specification> specifications = specificationMapper.selectList(new QueryWrapper<Specification>().eq("productTypeId", productTypeId).eq("isSku", 0));
            return specifications;
        }
        //表示 商品表中 查询到了 。取出来转化为 json字符串【数组】
        List<Specification> specifications = JSONArray.parseArray(viewProperties, Specification.class);
        return specifications;

    }


    /**
     * 获取 sku属性 步骤和思路于获取 显示属性一致
     * @param productId
     * @return
     */
    @Override
    public List<Specification> getSkuProperties(Long productId) {
        //先从product中查询
        Product product = baseMapper.selectById(productId);
        String skuProperties = product.getSkuProperties();
        if(StringUtils.isEmpty(skuProperties)){
            //再查询属性表 通过商品类型id
            Long productTypeId = product.getProductTypeId();
            List<Specification> specifications = specificationMapper.selectList(new QueryWrapper<Specification>().eq("productTypeId", productTypeId).eq("isSku", 1));
            return specifications;
            //因为 sku的价格和库存是存放在 sku表中
        }
        return JSONArray.parseArray(skuProperties,Specification.class);
    }

    /**
     * 保存 sku属性
     * @param specifications
     * @param productId
     * @param skus
     */
    @Override
    public void saveSkuProperties(List<Specification> specifications, Long productId, List<Map<String, Object>> skus) {
        //先根据商品id查询出商品
        Product product = baseMapper.selectById(productId);
        String jsonString = JSONArray.toJSONString(specifications);
        product.setSkuProperties(jsonString);
        baseMapper.updateById(product);
        //添加或者修改sku表
        //先删除该商品之前的sku
        skuMapper.delete(new QueryWrapper<Sku>().eq("productId",productId));
        //再重新添加
        List<Sku> skuList = new ArrayList<>();
        for (Map<String, Object> skuMap : skus) {
            Sku sku = new Sku();
            sku.setProductId(productId);
            //使用 Map<String, Object>  需要先把Object转化为string类型。再转化为需要的类型
            sku.setAvailableStock(Integer.valueOf(skuMap.get("availableStock").toString()));
            sku.setCreateTime(new Date().getTime());
            sku.setPrice(Integer.valueOf(skuMap.get("price").toString()));
            sku.setSkuIndex(skuMap.get("sku_index").toString());

            //获取除了sku_index price,availableStock之外的所有属性
            //name    sku_properties
            String name = "";
            Map<String,String> sku_properties = new LinkedHashMap<>();
            for (Map.Entry<String, Object> entry : skuMap.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue().toString();
                //排除sku_index,price,availableStock
                if(key.equals("price")||key.equals("sku_index")||key.equals("availableStock")){
                    continue;
                }
                name += value;
                sku_properties.put(key,value);
            }
            sku.setSkuName(name);
            sku.setSkuProperties(JSONObject.toJSONString(sku_properties));
            //保存
            skuMapper.insert(sku);
        }
    }

    /**
     * 修改 状态
     * @param id
     */
    @Override
    public void updateUpState(Long id) {

        baseMapper.updateUpState(id);

    }

    @Override
    @Transactional
    public boolean save(Product entity) {
        try {
            super.save(entity);//保存product   是否获取id -- mybatisplus默认是自动获取id的
            //保存商品详情
            ProductExt productExt = new ProductExt();
            productExt.setDescription(entity.getDescription());
            productExt.setRichContent(entity.getContent());
            productExt.setProductId(entity.getId());
            productExtMapper.insert(productExt);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
