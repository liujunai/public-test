package cn.lele.controller;

import cn.lele.model.QueryCondition;
import cn.lele.model.User;
import cn.lele.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by liu on 16-12-1.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    //查询
    @RequestMapping("/listUser.do")
    public String listUser(QueryCondition qc, Model model){
        List<User> uList = userService.selectUserAll(qc);
        model.addAttribute("uList",uList);
        model.addAttribute("qc",qc);
        return "list";
    }

    //跳转到添加页面
    @RequestMapping("/toSave.do")
    public String toSave(){
        return "save";
    }

    //添加
    @RequestMapping("/save.do")
    public String save(User user){
        userService.saveUser(user);
        return "redirect:listUser.do";
    }

    //根据ID查询
    @RequestMapping("/selectUserId.do")
    public String selectUserId(Integer userId,Model model){
        User user = userService.selectUserById(userId);
        model.addAttribute("user",user);
        return "update";
    }

    //修改
    @RequestMapping("/update.do")
    public String update(User user){
        userService.update(user);
        return "redirect:listUser.do";
    }

    //删除
    @RequestMapping("/delete.do")
    public String delete(Integer userId){
        userService.delete(userId);
        return "redirect:listUser.do";
    }

}
