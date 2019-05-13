package cn.itsource.maomaogo.service.impl;

import cn.itsource.maomaogo.domain.Employee;
import cn.itsource.maomaogo.mapper.EmployeeMapper;
import cn.itsource.maomaogo.service.IEmployeeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper,Employee> implements IEmployeeService {

    @Override
    public Employee login(String username, String password) {
        //QueryWrapper 条件构造器。
        return baseMapper.selectOne(new QueryWrapper<Employee>()
                .eq("username",username)
                .eq("password",password));
    }
}
