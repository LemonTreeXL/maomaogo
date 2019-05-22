package cn.itsource.maomaogo.controller;

import cn.itsource.maomaogo.domain.Specification;
import cn.itsource.maomaogo.service.IProductService;
import cn.itsource.maomaogo.domain.Product;
import cn.itsource.maomaogo.query.ProductQuery;
import cn.itsource.maomaogo.util.AjaxResult;
import cn.itsource.maomaogo.util.PageList;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ProductController {
    @Autowired
    public IProductService productService;

    /**
    * 保存和修改公用的
    * @param product  传递的实体
    * @return Ajaxresult转换结果
    */
    @RequestMapping(value="/product",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody Product product){
        try {
            if(product.getId()!=null){
                productService.updateById(product);
            }else{
                productService.save(product);
            }
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("保存对象失败！"+e.getMessage());
        }
    }

    /**
    * 删除对象信息
    * @param id
    * @return
    */
    @RequestMapping(value="/product/{id}",method=RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") Long id){
        try {
            productService.removeById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }

    //获取
    @RequestMapping(value = "/product/{id}",method = RequestMethod.GET)
    public Product get(@PathVariable("id") Long id)
    {
        return productService.getById(id);
    }


    /**
    * 查看所有信息
    * @return
    */
    @RequestMapping(value = "/product/list",method = RequestMethod.GET)
    public List<Product> list(){
        return productService.list();
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @RequestMapping(value = "/product/page",method = RequestMethod.POST)
    public PageList<Product> json(@RequestBody ProductQuery query)
    {
        return productService.getByQuery(query);
    }

    /**
     * 上架
     * @param id
     * @return
     */
    @GetMapping("/update/{id}")
    public AjaxResult updateUpState(@PathVariable("id") Long id){

        try {
            productService.updateUpState(id);
            return AjaxResult.me().setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("修改失败");
        }
    }



    /**
     * 获取商品的显示属性
     * @param productId
     * @return
     */
    @RequestMapping(value = "/product/viewProperties",method = RequestMethod.GET)
    public List<Specification> viewProperties(@Param("productId") Long productId){
        return productService.getViewProperties(productId);
    }

    /**
     * 保存显示属性
     * @param para
     * @return
     */
    @RequestMapping(value = "/product/viewProperties",method = RequestMethod.POST)
    public AjaxResult viewProperties(@RequestBody Map<String,Object> para){
        try {
            Long productId =  ((Integer)para.get("productId")).longValue();
            List<Specification> specifications = (List<Specification>) para.get("viewProperties");
            productService.saveViewProperties(specifications,productId);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("保存失败!"+e.getMessage());
        }
    }



    /**
     * 获取商品的SKU属性
     * @param productId
     * @return
     */
    @RequestMapping(value = "/product/skuProperties",method = RequestMethod.GET)
    public List<Specification> skuProperties(@Param("productId") Long productId){
        return productService.getSkuProperties(productId);
    }

    /**
     * 保存sku属性
     * @param para
     * @return
     */
    @RequestMapping(value = "/product/skuProperties",method = RequestMethod.POST)
    public AjaxResult skuProperties(@RequestBody Map<String,Object> para){
        try {
            Long productId =  ((Integer)para.get("productId")).longValue();
            List<Specification> specifications = (List<Specification>) para.get("skuProperties");
            List<Map<String,Object>> skus = (List<Map<String, Object>>) para.get("skus");
            productService.saveSkuProperties(specifications,productId,skus);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("保存失败!"+e.getMessage());
        }
    }


}
