package com.ocrms.ocrmsbca.controller.admin;

import com.ocrms.ocrmsbca.Enum.EComplainStatus;
import com.ocrms.ocrmsbca.dto.AdminDto;
import com.ocrms.ocrmsbca.dto.ComplainDto;
import com.ocrms.ocrmsbca.service.impl.AdminServiceImpl;
import com.ocrms.ocrmsbca.service.impl.ComplainServiceImpl;
import com.ocrms.ocrmsbca.service.impl.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.Collections;
import java.util.List;

/**
 * @author CHHATRA SAUD
 * @product IntelliJ IDEA
 * @project ocrmsbca
 * @Date 04/07/2022
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    private final AdminServiceImpl adminService;
    private final ComplainServiceImpl complainService;
    private final UserServiceImpl userService;


    public AdminController(AdminServiceImpl adminService, ComplainServiceImpl complainService, UserServiceImpl userService) {
        this.adminService = adminService;
        this.complainService = complainService;
        this.userService = userService;
    }

    @RequestMapping("/landing")
    private String landingPage(){
        return "admin/adminHome";
    }

    @GetMapping("/dashboard")
    public String dashboard(){
        return "admin/dashboard";

    }

    @GetMapping
    public String showAddAdmin(Model model)
    {
        model.addAttribute("adminDto",new AdminDto());
        return "admin/addAdmin";
    }
    @PostMapping
    public String saveAdmin(@ModelAttribute AdminDto adminDto,Model model)
    {
        try {
            AdminDto save=adminService.save(adminDto);
            model.addAttribute("message","Admin added successfully");
        }catch (Exception e)
        {
            model.addAttribute("message","Admin added failed !! tye again");
            e.printStackTrace();
        }
       model.addAttribute("adminDto",adminDto);
        return "admin/addAdmin";
    }
    @GetMapping("/adminList")
    public String getAdminList(Model model)
    {
        model.addAttribute("adminList",adminService.findAll());
        return "admin/adminList";
    }
    @GetMapping("/delete/{id}")
    public String deleteAdmin(@PathVariable("id")Long id)
    {
        adminService.deleteById(id);
        return "admin/adminList";
    }

    @GetMapping("/complainList")
    public String showCrime(Model model) throws IOException
    {
        model.addAttribute("complainList",complainService.findAllComplain());
        return "admin/complainList";
    }
    @GetMapping("/approve/{id}")
    public String verifyPage(@PathVariable("id")Long id) throws ParseException, IOException {
        ComplainDto complainDto=complainService.findById(id);
        complainDto.setComplainStatus(EComplainStatus.APPROVE);
        complainService.save(complainDto);
        return "admin/complainList";
    }
}
