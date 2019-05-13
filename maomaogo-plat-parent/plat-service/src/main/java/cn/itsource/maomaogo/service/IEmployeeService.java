package cn.itsource.maomaogo.service;

import cn.itsource.maomaogo.domain.Employee;
import com.baomidou.mybatisplus.extension.service.IService;

public interface IEmployeeService extends IService<Employee> {
    Employee login(String username, String password);
}
