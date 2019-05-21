package cn.itsource.maomaogo.service.impl;

import cn.itsource.maomaogo.domain.Product;
import cn.itsource.maomaogo.domain.ProductExt;
import cn.itsource.maomaogo.mapper.ProductExtMapper;
import cn.itsource.maomaogo.mapper.ProductMapper;
import cn.itsource.maomaogo.query.ProductQuery;
import cn.itsource.maomaogo.service.IProductService;
import cn.itsource.maomaogo.util.PageList;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public PageList<Product> getByQuery(ProductQuery query) {

        //需要一个page对象
        Page<Product> page = new Page<>(query.getPage(),query.getSize());
        IPage<Product> iPage = baseMapper.selectByQuery(page,query);
        //封装PageList
        return new PageList<Product>(iPage.getTotal(),iPage.getRecords());

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
