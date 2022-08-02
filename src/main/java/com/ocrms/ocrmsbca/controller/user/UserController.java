package com.ocrms.ocrmsbca.controller.user;

import com.ocrms.ocrmsbca.dto.UserDto;
import com.ocrms.ocrmsbca.service.impl.UserServiceImpl;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author CHHATRA SAUD
 * @product IntelliJ IDEA
 * @project ocrmsbca
 * @Date 04/07/2022
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }
    @PostMapping
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
       return "loginPage";
    }

}
