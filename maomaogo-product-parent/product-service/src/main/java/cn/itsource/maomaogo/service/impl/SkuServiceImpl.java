package cn.itsource.maomaogo.service.impl;

import cn.itsource.maomaogo.domain.Sku;
import cn.itsource.maomaogo.mapper.SkuMapper;
import cn.itsource.maomaogo.service.ISkuService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * SKU 服务实现类
 * </p>
 *
 * @author LemonTreeXL
 * @since 2019-05-21
 */
@Service
public class SkuServiceImpl extends ServiceImpl<SkuMapper, Sku> implements ISkuService {


    @Override
    public List<Sku> getSkus(Long productId) {
        //通过 商品id 查询它对应的所有的sku信息【价格。库存】
        List<Sku> skus = baseMapper.selectList(new QueryWrapper<Sku>().eq("productId", productId));
        return skus;
    }
}
