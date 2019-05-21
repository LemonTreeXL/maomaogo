package cn.itsource.maomaogo.controller;

import cn.itsource.maomaogo.domain.Brand;
import cn.itsource.maomaogo.query.BrandQuery;
import cn.itsource.maomaogo.service.IBrandService;
import cn.itsource.maomaogo.util.AjaxResult;
import cn.itsource.maomaogo.util.LetterUtil;
import cn.itsource.maomaogo.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

@RestController
public class BrandController {

    @Autowired
    public IBrandService brandService;

    /**
    * 保存和修改公用的
    * @param brand  传递的实体
    * @return Ajaxresult转换结果
    */
    @RequestMapping(value="/brand",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody Brand brand){
        try {
            //获取当前系统时间对应的毫秒数
//            Long t = System.currentTimeMillis();
//            //把毫秒数转化为时间日期格式 【倒是时区不对】
//            Date date2=new Date();
//            date2.setTime(t);
//            System.out.println(date2);
//            System.out.println(brand);
            if(brand.getId()!=null){ //为修改操作【编辑】
                brand.setUpdateTime(System.currentTimeMillis());
                brand.setSortIndex(100);
                brandService.updateById(brand);

            }else{ //为新增操作
                //设置首字母
                String firstLetter = LetterUtil.getFirstLetter(brand.getName());
                brand.setFirstLetter(firstLetter);
                //设置时间
                brand.setCreateTime(System.currentTimeMillis());
                //设置默认排序为100
                brand.setSortIndex(100);
                brandService.save(brand);
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
    @RequestMapping(value="/brand/{id}",method=RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") Long id){
        try {
            brandService.removeById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }

    /**
     * 批量删除对象信息  使用数组来接收。方便我们操作
     * @param ids
     * @return
     */
    @RequestMapping(value="/brand/ids",method=RequestMethod.DELETE)
    public AjaxResult delete(Long[] ids){
        try {
//            System.out.println(ids.length);
            for (Long id : ids) {
//                System.out.println(id);
                 brandService.removeById(id);
            }
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("批量删除对象失败！"+e.getMessage());
        }
    }

    //获取
    @RequestMapping(value = "/brand/{id}",method = RequestMethod.GET)
    public Brand get(@PathVariable("id") Long id)
    {
        return brandService.getById(id);
    }


    /**
    * 查看所有信息
    * @return
    */
    @RequestMapping(value = "/brand/list",method = RequestMethod.GET)
    public List<Brand> list(){
        return brandService.list();
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @RequestMapping(value = "/brand/page",method = RequestMethod.POST)
    public PageList<Brand> json(@RequestBody BrandQuery query)
    {
        return brandService.getByQuery(query);
    }



}
