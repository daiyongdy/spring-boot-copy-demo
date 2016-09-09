/**
 * <b>项目名：</b>spring-boot-copy-demo<br/>
 * <b>包名：</b>com.hbdiy.sb.controller<br/>
 * <b>文件名：</b>UserController.java<br/>
 * <b>描述：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 * <b>日期：</b>2016年9月3日-上午10:25:29<br/>
 * <b>Copyright (c)</b> 2016<br/>
 */

package com.hbdiy.sb.controller;

import com.alibaba.fastjson.JSON;
import com.hbdiy.sb.dao.UserMapper;
import com.hbdiy.sb.model.User;
import com.hbdiy.sb.service.UserService;
import com.hbdiy.sb.util.redis.RedisUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <b>类名称：</b>UserController <br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>daiyong<br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b>2016年9月3日 上午10:25:29<br/>
 * <b>修改备注：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 */

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "添加用户", notes = "根据id获取用户")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String add(@RequestBody User user) {
        /*User user = new User();
        user.setUserId("0");
		user.setUsername("daiyong");
		user.setPassword("111111");*/

        this.userService.addByTemplate(user);

        return "创建用户成功!";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(String id) {
        this.userService.deleteByTemplate(id);

        return "删除用户成功!";
    }

    @RequestMapping("/update")
    @ResponseBody
    public String delete(User user) {
        this.userService.updateByTemplate(user);

        return "更新用户成功!";
    }

    @ApiIgnore
    @RequestMapping("/get")
    public String get(String id, Model model) {
        User user = this.userService.selectByTemplate(id);
        model.addAttribute("user", user);
        return "/user/show";
    }


    //    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long"),
//            @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
//    })
    @ApiOperation(value = "获取用户", notes = "根据id获取用户")
    @RequestMapping(value = "/get2", method = RequestMethod.GET)
    public String get2(@RequestParam String id, Model model) {
        User user = this.userService.selectByMybatis(id);
        model.addAttribute("user", user);
        return "/user/show";
    }

    @Resource
    UserMapper userMapper;

    @ApiOperation(value = "获取用户", notes = "根据id获取用户")
    @RequestMapping(value = "/get3", method = RequestMethod.GET)
    @ResponseBody
    public String get3(@RequestParam("id") String id) {
        User user = userMapper.selectByPrimaryKey(id);
        return user.toString();
    }

    /**
     * 测试读写分离与分页插件
     */
    @RequestMapping("/getByPage")
    public String getByPage(Model model, int page, int rows) {
        List<User> users = this.userService.selectByPage(page, rows);
        model.addAttribute("users", users);
        return "/user/list";
    }

    @RequestMapping("/redis")
    @ResponseBody
    public String redis() {
        User user = this.userService.selectByMybatis("0");
        RedisUtil.set("aa", JSON.toJSONString(user));
        return "redis success!";
    }

}	
