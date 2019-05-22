package cn.itsource.maomaogo.service;

import cn.itsource.maomaogo.domain.Sku;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * SKU 服务类
 * </p>
 *
 * @author LemonTreeXL
 * @since 2019-05-21
 */
public interface ISkuService extends IService<Sku> {

    List<Sku> getSkus(Long productId);
}
