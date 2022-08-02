package com.ocrms.ocrmsbca.controller.admin;

import com.ocrms.ocrmsbca.dto.AdminDto;
import com.ocrms.ocrmsbca.service.impl.AdminServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    public AdminController(AdminServiceImpl adminService) {
        this.adminService = adminService;
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
}
