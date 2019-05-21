package cn.itsource.maomaogo.service.impl;

import cn.itsource.maomaogo.domain.Brand;
import cn.itsource.maomaogo.mapper.BrandMapper;
import cn.itsource.maomaogo.query.BrandQuery;
import cn.itsource.maomaogo.service.IBrandService;
import cn.itsource.maomaogo.util.PageList;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 品牌信息 服务实现类
 * </p>
 *
 * @author LemonTreeXL
 * @since 2019-05-16
 */
@Service
@Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements IBrandService {

    @Override
    public PageList<Brand> getByQuery(BrandQuery query) {
        Page<Brand> page = new Page<>(query.getPage(),query.getSize());
        //核心代码 mybatis-plus的分页
        IPage<Brand> iPage = baseMapper.selectByQuery(page,query);
        //从IPage中提取分页结果封装到自己的PageList中
        return new PageList<Brand>(iPage.getTotal(),iPage.getRecords());
        /*
        Page<Brand> page = new Page<Brand>(query.getPage(),query.getSize());
        QueryWrapper<Brand> wrapper = new QueryWrapper<>();
        wrapper.like("b.name",query.getKeyword()).or().like("englishName",query.getKeyword());
        IPage<Brand> iPage = baseMapper.selectByWrapper(page, wrapper);
        return new PageList<Brand>(iPage.getTotal(),iPage.getRecords());
        */
    }


    

}
