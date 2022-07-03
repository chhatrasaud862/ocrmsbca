package com.ocrms.ocrmsbca.controller;

import org.springframework.stereotype.Controller;
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
    @GetMapping
    public String getAdmin()
    {
        return "admin/adminHome";
    }
    @GetMapping("/user")
    public String getUser()
    {
        return "user/userHome";
    }
    @GetMapping("/admin")
    public String AddAdmin()
    {
        return "admin/addAdmin";
    }
    @GetMapping("/users")
    public String AddUser()
    {
        return "user/userRegistration";
    }
}
