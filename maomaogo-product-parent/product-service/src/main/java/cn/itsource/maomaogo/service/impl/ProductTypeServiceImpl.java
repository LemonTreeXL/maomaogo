package cn.itsource.maomaogo.service.impl;

import cn.itsource.maomaogo.client.RedisClient;
import cn.itsource.maomaogo.client.TemplateClient;
import cn.itsource.maomaogo.domain.ProductType;
import cn.itsource.maomaogo.mapper.ProductTypeMapper;
import cn.itsource.maomaogo.service.IProductTypeService;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品目录 服务实现类
 * </p>
 *
 * @author LemonTreeXL
 * @since 2019-05-16
 */
@Service
@Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
public class ProductTypeServiceImpl extends ServiceImpl<ProductTypeMapper, ProductType> implements IProductTypeService {

    @Autowired
    private RedisClient redisClient;

    @Autowired
    private TemplateClient templateClient;


    /**
     * 生成静态化首页
     */
    @Override
    public void generateStaticPage() {

        //模板   数据   目标文件的路径
        //路径问题最好不要硬编码，可以写到属性文件或者配置文件中，再读入

        //根据product.type.vm模板生成 product.type.vm.html
        //templatePath 模板的位置
        String templatePath = "D:\\JetBrains\\IDEA_workspace\\workspace-svn\\maoamogo-vue\\maomaogo\\maomaogo-product-parent\\product-service\\src\\main\\resources\\template\\product.type.vm";
        //生成的网页 的路径
        String targetPath = "D:\\JetBrains\\IDEA_workspace\\workspace-svn\\maoamogo-vue\\maomaogo\\maomaogo-product-parent\\product-service\\src\\main\\resources\\template\\product.type.vm.html";
        //查询商品列表数据
        List<ProductType> productTypes = loadDataTree();
        //模板 + 数据 = 输出 。使用map来封装参数【模板和数据】
        Map<String,Object> params = new HashMap<>();
        params.put("model",productTypes);
        params.put("templatePath",templatePath);
        params.put("targetPath",targetPath);
        //调用 feign接口的方法
        templateClient.createStaticPage(params);

        //再根据home.vm生成home.html
        templatePath = "D:\\JetBrains\\IDEA_workspace\\workspace-svn\\maoamogo-vue\\maomaogo\\maomaogo-product-parent\\product-service\\src\\main\\resources\\template\\home.vm";
        targetPath = "D:\\JetBrains\\IDEA_workspace\\workspace-svn\\maoamogo-vue\\maomaogo-web-parent\\ecommerce\\home.html";
        params = new HashMap<>();
        //保存 封装模板和数据的
        Map<String,Object> model = new HashMap<>();
        //为了 引入 $template=$model.staticRoot+"template/home/common-link.vm" 这个模板 所以需要一个 项目的resource完全限定路径
        model.put("staticRoot","D:\\JetBrains\\IDEA_workspace\\workspace-svn\\maoamogo-vue\\maomaogo\\maomaogo-product-parent\\product-service\\src\\main\\resources\\");
        params.put("model",model);
        params.put("templatePath",templatePath);
        params.put("targetPath",targetPath);
        templateClient.createStaticPage(params);

    }


    /**
     * 加载类型树
     * （1）递归   看有没有children，有则继续查
     * （2）循环，一次性查询所有的类型，循环添加到children中
     * @return
     */
    @Override
    public List<ProductType> loadTreeData() {
        //1.先到redis中取获取【获取到的是一个json字符串】
        String productTypesStr = redisClient.get("ProductTypes");
        //2.判断 redis中是否缓存了查询的数据
        if (StringUtils.isEmpty(productTypesStr)){
            System.out.println("redis中没有数据！！！");
            //表示 redis没有数据。需要发送请求访问数据库
            //查询一级
//        return loadDataTree(0L); //递归 通过已知条件。一直查询到想要的结果。最后通过逆推的方式。赋值
            List<ProductType> productTypes = loadDataTree();//循环
            //3.把查询出来的数据。保存到 redis中【需要把查询出来的数据先转化为jsonArray数组】
            String jsonString = JSONArray.toJSONString(productTypes);
            redisClient.set("ProductTypes", jsonString);
            //返回 查询处理的数据
            return productTypes;
        }

        //4.表示 redis中有数据。直接从redis中获取数据。不发送请求访问数据库【需要把json字符串转化为list集合】
        List<ProductType> productTypes = JSONArray.parseArray(productTypesStr,ProductType.class);
        System.out.println("我是从redis中获取的数据");
        return productTypes;

    }

    /**
     * 递归： 效率低。查询一个字级菜单就会发送一次sql语句查询数据库。数据库服务器压力大。用户体验差
     * （1）自己调用自己【核心思想】
     * （2）要有出口 当查询到的子菜单为null或者size为0.表示没有字级菜单了
     *
     * 查自己的子
     *  执行一次该方法。就相当于查询到了一级字级菜单。
     *
     *  1.通过父级菜单的pid查询所有的父级菜单[数据库中pid为0表示一级菜单]
     *  2.判断 查询出来的菜单集合[children]是否有下一级,如果有继续递归
     *  3.如果有就循环遍历查询出来的集合【children】。递归【继续调用自己的方法】
     *  4.对自己【productType】的子【c】进行循环，调用自己继续查询子的子类型【调用自己的方法进行递归】
     *  5.返回 children，只有当执行到 没有字节菜单时，才会返回children。不然就会继续执行递归。
     *  【所以此时返回的children。已经把无限级别的菜单分好层级并且保存起来了】
     * @return
     */
    private List<ProductType> loadDataTree(Long pid){
        //根据父id查询子类型
        List<ProductType> children = baseMapper.selectList(new QueryWrapper<ProductType>().eq("pid", pid));
        //递归的出口 【如果没有出口就是死循环】。每一次都要先判断再循环遍历！！！
        if(children==null||children.size()==0){
            return null;
        }
        //children 代表当前自己【当前菜单级别集合】
        for (ProductType productType : children) {
            //productType 代表当前自己【当前菜单级别】
            //对自己的子进行循环，调用自己继续查询子的子类型【调用自己的方法进行递归】
            //loadDataTree(productType.getId()); 通过自己的id取查询自己下面的字节菜单
            List<ProductType> c = loadDataTree(productType.getId());
            //将所有的子类型放入到自己的children属性中【c代表当前级别的子级菜单】children属性doamin实体类中的
            productType.setChildren(c);
        }
        return children;
    }


    /**
     * 循环
     * 1.直接查询出。所有的菜单
     * 2.把所有的菜单保存到map集合中。【等下需要用到时。直接到map集合中取】
     * 3.定义一个list集合。用来存放所有的一级菜单
     * 4.把非父级菜单。保存到它自己的父级菜单中
     *      map.get(productType.getPid()) 表示通过父级菜单的id获取当前菜单的父级菜单的对象【从存放了所有菜单的map集合中取】
     *      .getChildren() 获取到自关联的集合属性【在domain实体类中】。
     * .    add(productType) 把当前菜单。存放到它自己的父级菜单中
     * 5.返回 保存了所有父级菜单的list集合
     * @return
     */
    private List<ProductType> loadDataTree(){

        //查询所有
        List<ProductType> productTypes = baseMapper.selectList(null);
        //放到Map中--大大的降低循环次数
        Map<Long,ProductType> map = new HashMap<>();
        //productTypes 代表所有的菜单集合
        for (ProductType productType : productTypes) {
            //保存 所有菜单
            map.put(productType.getId(),productType);
        }

        //放所有的一级类型 用来存放所有的一级菜单
        List<ProductType> list = new ArrayList<>();
        //子找父  循环所有菜单集合 productTypes
        for (ProductType productType : productTypes) {
            //如果pid为0则自己就是父
            if(productType.getPid()==0){
                //把一级菜单 保存到list中
                list.add(productType);
            }else{//不是一级菜单
                /**
                 * map.get(productType.getPid()) 表示通过父级菜单的id获取当前菜单的父级菜单的对象【从存放了所有菜单的map集合中取】
                 * .getChildren() 获取到自关联的集合属性【在domain实体类中】。
                 * .add(productType) 把当前菜单。存放到它自己的父级菜单中
                 * */
                map.get(productType.getPid()).getChildren().add(productType);
            }
        }
        //返回 所有的父级菜单【因为自关联了。所有每一个父接菜单对象都有一个List<ProductType>。已经存好了它下面的所有字级菜单了】
        return list;

    }


    //=====================重写增删改，同步redis缓存，同步生成静态页面==================================
    @Override
    public boolean save(ProductType entity) {

        //先执行保存
        boolean result = super.save(entity);
        sychornizedOperate();
        return result;
    }

    @Override
    public boolean removeById(Serializable id) {
        boolean result = super.removeById(id);
        sychornizedOperate();
        return result;
    }

    @Override
    public boolean updateById(ProductType entity) {
        boolean result = super.updateById(entity);
        sychornizedOperate();
        return result;
    }


    private void updateRedis(){
        List<ProductType> productTypes = loadDataTree();
        //转成json字符串缓存到redis中
        String jsonString = JSONArray.toJSONString(productTypes);
        redisClient.set("productTypes",jsonString);
    }

    //=====================结束==================================

    /**
     * 只要是增删改操作，都要同步去做两件事情
     * （1）同步redis缓存
     * （2）同步生成静态的主页面
     */
    private void sychornizedOperate(){
        updateRedis();
        generateStaticPage();
    }


}
