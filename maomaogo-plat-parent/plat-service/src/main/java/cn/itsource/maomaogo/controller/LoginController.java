package cn.itsource.maomaogo.controller;

import cn.itsource.maomaogo.domain.Employee;
import cn.itsource.maomaogo.service.IEmployeeService;
import cn.itsource.maomaogo.util.AjaxResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 暴露给外部的一个接口
 */
@RestController
public class LoginController {

    @Autowired
    private IEmployeeService employeeService;

    /**
     * 暴露给外部的一个服务
     * @param map
     * @return
     */
    @PostMapping("/login")
    @ApiOperation(value = "登录接口")
    public AjaxResult login(@RequestBody Map<String,Object> map){

        //获取请求参数
        String username = (String) map.get("username");
        String password = (String) map.get("password");


        Employee employee = employeeService.login(username,password);

        if(null!=employee){
            //登录成功
            return AjaxResult.me();
        }
        //登录失败
        return AjaxResult.me().setSuccess(false).setMessage("用户名或密码错误!");



//        //判断 用户传入的。参数是否合法
//        if (null==username||"".equals(username)){
//            //表示 用户名输入不合法。返回提示信息给用户
//            return  AjaxResult.me().setSuccess(false).setMessage("用户名不能为空。请输入合法的用户名!!!");
//        }
//
//        if (null==password||"".equals(password)){
//            //表示 用户名输入不合法。返回提示信息给用户
//            return  AjaxResult.me().setSuccess(false).setMessage("密码不能为空。请输入合法的密码!!!");
//        }
//
//        //模拟数据库查询处理的 用户名和密码
//        if ("admin".equals(username)){
//            if ("admin".equals(password)){
//                //表示登录成功
//                return AjaxResult.me().setSuccess(true);
//            }else {
//                //表示密码错误
//             return  AjaxResult.me().setSuccess(false).setMessage("密码输入错误，请重新输入!!!");
//            }
//        }else {
//           //表示用户名错误
//            return AjaxResult.me().setSuccess(false).setMessage("用户名输入错误，请重新输入!!!");
//        }

    }


}
