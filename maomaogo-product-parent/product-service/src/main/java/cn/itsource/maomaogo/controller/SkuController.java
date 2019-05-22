package cn.itsource.maomaogo.controller;

import cn.itsource.maomaogo.service.ISkuService;
import cn.itsource.maomaogo.domain.Sku;
import cn.itsource.maomaogo.query.SkuQuery;
import cn.itsource.maomaogo.util.AjaxResult;
import cn.itsource.maomaogo.util.PageList;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SkuController {
    @Autowired
    public ISkuService skuService;

    /**
    * 保存和修改公用的
    * @param sku  传递的实体
    * @return Ajaxresult转换结果
    */
    @RequestMapping(value="/sku",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody Sku sku){
        try {
            if(sku.getId()!=null){
                skuService.updateById(sku);
            }else{
                skuService.save(sku);
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
    @RequestMapping(value="/sku/{id}",method=RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") Long id){
        try {
            skuService.removeById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }

    //获取
    @RequestMapping(value = "/sku/{id}",method = RequestMethod.GET)
    public Sku get(@PathVariable("id") Long id)
    {
        return skuService.getById(id);
    }


    /**
    * 查看所有信息
    * @return
    */
    @RequestMapping(value = "/sku/list",method = RequestMethod.GET)
    public List<Sku> list(){
        return skuService.list();
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @RequestMapping(value = "/sku/page",method = RequestMethod.POST)
    public PageList<Sku> json(@RequestBody SkuQuery query)
    {
        IPage<Sku> skuIPage = skuService.page(new Page<>(query.getPage(), query.getSize()));
        return new PageList<>(skuIPage.getTotal(),skuIPage.getRecords());
    }

    /**
     * 通过 商品id查询 对应的sku信息
     */
    @RequestMapping(value = "/sku/getSkus",method = RequestMethod.GET)
    public List<Sku> getSkus(@Param("productId") Long productId){
        return skuService.getSkus(productId);
    }
}
