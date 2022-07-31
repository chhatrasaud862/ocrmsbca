package com.ocrms.ocrmsbca.controller;

import com.ocrms.ocrmsbca.components.AuthorizeUser;
import com.ocrms.ocrmsbca.dto.UserDto;
import com.ocrms.ocrmsbca.entity.admin.Admin;
import com.ocrms.ocrmsbca.entity.user.User;
import com.ocrms.ocrmsbca.service.impl.AdminServiceImpl;
import com.ocrms.ocrmsbca.service.impl.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author CHHATRA SAUD
 * @product IntelliJ IDEA
 * @project ocrmsbca
 * @Date 04/07/2022
 */
@Controller
@RequestMapping("/")
public class MainController {
    private final UserServiceImpl userService;
    private final AdminServiceImpl adminService;

    public MainController(UserServiceImpl userService, AdminServiceImpl adminService) {
        this.userService = userService;
        this.adminService = adminService;
    }


    @GetMapping
    public String openUserHomePage()
    {
        if (AuthorizeUser.getUserStatus().ordinal()==0)
        {
            User user=userService.findUserByEmail(AuthorizeUser.getUser().getEmail());
            AuthorizeUser.setUser(null);
            //assign again
            AuthorizeUser.setUser(user);
            return "user/userHome";
        }else if (AuthorizeUser.getUserStatus().ordinal() == 1){
            Admin admin=adminService.findAdminByEmail(AuthorizeUser.getAdmin().getEmail());
            AuthorizeUser.setAdmin(null);
            AuthorizeUser.setAdmin(admin);
            return "admin/adminHome";
        }else {
            return null;
        }
    }
    @GetMapping("/login")
    public String openLogin(Model model)
    {
        model.addAttribute("userDto",new UserDto());
        return "signupandlogin";
    }
}