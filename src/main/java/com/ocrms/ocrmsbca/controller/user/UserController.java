package com.ocrms.ocrmsbca.controller.user;

import com.ocrms.ocrmsbca.dto.UserDto;
import com.ocrms.ocrmsbca.service.user.UserService;
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
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/u")
    public String getUser(Model model)
    {
        model.addAttribute("user",userService.findAll());
        return "user/userRegistration";
    }

    @PostMapping
  public String saveUser(@ModelAttribute UserDto userDto)  {
       UserDto user=userService.save(userDto);
       return "registration suceessfully";
    }

}
