package cn.itsource.maomaogo.service;

import cn.itsource.maomaogo.domain.Product;
import cn.itsource.maomaogo.query.ProductQuery;
import cn.itsource.maomaogo.util.PageList;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 商品 服务类
 * </p>
 *
 * @author LemonTreeXL
 * @since 2019-05-20
 */
public interface IProductService extends IService<Product> {

    PageList<Product> getByQuery(ProductQuery query);
}
