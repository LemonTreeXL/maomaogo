package cn.itsource.maomaogo.mapper;

import cn.itsource.maomaogo.domain.Brand;
import cn.itsource.maomaogo.query.BrandQuery;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 品牌信息 Mapper 接口
 * </p>
 *
 * @author LemonTreeXL
 * @since 2019-05-16
 */
public interface BrandMapper extends BaseMapper<Brand> {

    IPage<Brand> selectByQuery(Page<Brand> page, @Param("query") BrandQuery query);
}
