package cn.itsource.maomaogo.service;

import cn.itsource.maomaogo.domain.Product;
import cn.itsource.maomaogo.domain.Specification;
import cn.itsource.maomaogo.query.ProductQuery;
import cn.itsource.maomaogo.util.PageList;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

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

    void updateUpState(Long id);

    List<Specification> getViewProperties(Long productId);

    void saveViewProperties(List<Specification> specifications, Long productId);

    List<Specification> getSkuProperties(Long productId);

    void saveSkuProperties(List<Specification> specifications, Long productId, List<Map<String, Object>> skus);
}
