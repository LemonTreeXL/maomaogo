package cn.itsource.maomaogo.service;

import cn.itsource.maomaogo.domain.Brand;
import cn.itsource.maomaogo.query.BrandQuery;
import cn.itsource.maomaogo.util.PageList;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 品牌信息 服务类
 * </p>
 *
 * @author LemonTreeXL
 * @since 2019-05-16
 */
public interface IBrandService extends IService<Brand> {

    PageList<Brand> getByQuery(BrandQuery query);
}
