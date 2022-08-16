package com.ocrms.ocrmsbca.controller.admin;

import com.ocrms.ocrmsbca.Enum.EComplainStatus;
import com.ocrms.ocrmsbca.dto.AdminDto;
import com.ocrms.ocrmsbca.entity.admin.Admin;
import com.ocrms.ocrmsbca.entity.complain.Complain;
import com.ocrms.ocrmsbca.repository.admin.AdminRepository;
import com.ocrms.ocrmsbca.repository.complain.ComplainRepository;
import com.ocrms.ocrmsbca.service.impl.AdminServiceImpl;
import com.ocrms.ocrmsbca.service.impl.ComplainServiceImpl;
import com.ocrms.ocrmsbca.service.impl.UserServiceImpl;
import org.aspectj.bridge.IMessage;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.MessageFormat;
import java.text.ParseException;
import java.util.Optional;

/**
 * @author CHHATRA SAUD
 * @product IntelliJ IDEA
 * @project ocrmsbca
 * @Date 04/07/2022
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private final AdminServiceImpl adminService;
    private final ComplainServiceImpl complainService;
    private final UserServiceImpl userService;
    private final ComplainRepository complainRepository;
    private final AdminRepository adminRepository;


    public AdminController(AdminServiceImpl adminService, ComplainServiceImpl complainService, UserServiceImpl userService, ComplainRepository complainRepository, AdminRepository adminRepository) {
        this.adminService = adminService;
        this.complainService = complainService;
        this.userService = userService;
        this.complainRepository = complainRepository;
        this.adminRepository = adminRepository;
    }

    @RequestMapping("/landing")
    private String landingPage(){
        return "admin/adminHome";
    }

    @GetMapping("/dashboard")
    public String dashboard(){
        return "admin/dashboard";

    }

    @GetMapping("/addAdmin")
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
    public String deleteAdmin(@PathVariable("id")Long adminId)
    {
      adminService.deleteById(adminId);
      System.out.println("admin Deleted");
        return "redirect:/admin/adminList";
    }

    @GetMapping("/complainList")
    public String showCrime(Model model) throws IOException
    {
        model.addAttribute("complainList",complainService.findAllComplain());
        return "admin/complainList";
    }

    @GetMapping("/approve/{id}")
    public String verifyPage(@PathVariable("id")Long id ,Complain complain) throws ParseException, IOException {
      /*  ComplainDto complainDto=complainService.findById(id);
        complainDto.setComplainStatus(EComplainStatus.APPROVE);
        complainService.save(complainDto);*/

        complain.setComplainStatus(EComplainStatus.APPROVE);
        complainRepository.save(complain);
        return "admin/complainList";
    }

    @GetMapping("/update/{id}")
    public String updateAdmin(@PathVariable("id")Long id, RedirectAttributes redirectAttributes){
        AdminDto adminDto=adminService.findById(id);
        if(adminDto !=null)
       redirectAttributes.addFlashAttribute("adminDto",adminDto);
        return "redirect:/admin/addAdmin";

    }
}
