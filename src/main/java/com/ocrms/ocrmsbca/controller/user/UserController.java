package com.ocrms.ocrmsbca.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author CHHATRA SAUD
 * @product IntelliJ IDEA
 * @project ocrmsbca
 * @Date 04/07/2022
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/landing")
    public String landingPage(){
        return "user/userHome";
    }

}
