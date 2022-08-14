package com.ocrms.ocrmsbca.controller;

import com.ocrms.ocrmsbca.dto.UserDto;
import com.ocrms.ocrmsbca.service.impl.UserServiceImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


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

    public MainController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping
    public String openUserHomePage()
    {
       return "home";
    }
    @RequestMapping(value = "/signin",method= RequestMethod.GET)
    public String openLogin()
    {
        return "login";
    }

    @RequestMapping("/success")
    public void loginPageRedirect(HttpServletRequest request, HttpServletResponse response, Authentication authResult) throws IOException, ServletException {

        String role =  authResult.getAuthorities().toString();

        if(role.contains("ROLE_USER")){
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/user/landing"));
        }
        else if(role.contains("ROLE_ADMIN")) {
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/admin/landing"));
        }
    }

    @GetMapping("/signup")
    public String getSignup(Model model){
        model.addAttribute("userDto",new UserDto());
        return "user/signup";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute UserDto userDto, Model model)  {
        UserDto save=userService.save(userDto);
        try{

            model.addAttribute("message","user register successfully");
        }catch (Exception e)
        {
            model.addAttribute("message","user register failed !! try again");
            e.printStackTrace();

        }
        model.addAttribute("userDto",userDto);
        return "user/signup";
    }
}