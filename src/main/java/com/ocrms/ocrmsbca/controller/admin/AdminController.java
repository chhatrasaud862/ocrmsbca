package com.ocrms.ocrmsbca.controller.admin;

import com.ocrms.ocrmsbca.Enum.EComplainStatus;
import com.ocrms.ocrmsbca.dto.AdminDto;
import com.ocrms.ocrmsbca.dto.ComplainDto;
import com.ocrms.ocrmsbca.entity.admin.Admin;
import com.ocrms.ocrmsbca.entity.complain.Complain;
import com.ocrms.ocrmsbca.repository.admin.AdminRepository;
import com.ocrms.ocrmsbca.repository.complain.ComplainRepository;
import com.ocrms.ocrmsbca.service.impl.AdminServiceImpl;
import com.ocrms.ocrmsbca.service.impl.ComplainServiceImpl;
import com.ocrms.ocrmsbca.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.text.ParseException;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;


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
    private String landingPage(Model model) {
        Map<String, Double> graphData = new TreeMap<>();
        graphData.put("Total User", Double.valueOf(userService.totalUser()));
        graphData.put("Total Complain", Double.valueOf(complainService.getTotalComplain()));
        graphData.put("Pending Complain", Double.valueOf(complainService.getPendingComplain()));
        graphData.put("Complete Complain", Double.valueOf(complainService.getApproveComplain()));
        graphData.put("Reject Complain", Double.valueOf(complainService.getRejectComplain()));
        model.addAttribute("chartData", graphData);
        return "admin/adminHome";
    }


    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        Map<String, Double> graphData = new TreeMap<>();
        graphData.put("Total User", Double.valueOf(userService.totalUser()));
        graphData.put("Total Complain", Double.valueOf(complainService.getTotalComplain()));
        graphData.put("Pending Complain", Double.valueOf(complainService.getPendingComplain()));
        graphData.put("Complete Complain", Double.valueOf(complainService.getApproveComplain()));
        graphData.put("Reject Complain", Double.valueOf(complainService.getRejectComplain()));
        model.addAttribute("chartData", graphData);
        return "admin/dashboard";
    }


    @GetMapping("/addAdmin")
    public String showAddAdmin(Model model) {
        model.addAttribute("adminDto", new AdminDto());
        return "admin/adminAdd";
    }

    @PostMapping
    public String saveAdmin(@Valid @ModelAttribute AdminDto adminDto, Model model, BindingResult bindingResult) {
        if(!bindingResult.hasErrors()) {
            try {
                AdminDto save = adminService.save(adminDto);
                model.addAttribute("message", "Admin added successfully");
            } catch (Exception e) {
                model.addAttribute("message", "Admin added failed !! tye again");
                e.printStackTrace();
            }
        }
        model.addAttribute("adminDto", adminDto);
        return "admin/adminAdd";
    }

    @GetMapping("/adminList")
    public String getAdminList(Model model) {
        model.addAttribute("adminList", adminService.findAll());
        return "admin/listOfAdmin";
    }

    @GetMapping("/delete/{id}")
    public String deleteAdmin(@PathVariable("id") Long adminId) {
        adminService.deleteById(adminId);
        System.out.println("admin Deleted");
        return "redirect:/admin/adminList";
    }

    @GetMapping("/complainList/{page}")
    public String showCrime(@PathVariable("page") Integer page, Model model) {
        Pageable pageable = PageRequest.of(page, 7);
        Page<Complain> complainList = this.complainRepository.getTotalComplain(pageable);

        model.addAttribute("complainList", complainList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPage", complainList.getTotalPages());
        /*   model.addAttribute("complainList",complainService.findAllComplain());*/
        return "admin/complainList";
    }

    @GetMapping("/approve/{id}")
    public String approvePage(@PathVariable("id") Long id) throws ParseException, IOException, IOException, ParseException {
        ComplainDto complainDto = complainService.findById(id);
        complainDto.setComplainStatus(EComplainStatus.APPROVE);
        complainService.save(complainDto);
        return "redirect:/admin/complainList/0";
    }

    @GetMapping("/reject/{id}")
    public String rejectPage(@PathVariable("id") Long id) throws ParseException, IOException, IOException, ParseException {
        ComplainDto complainDto = complainService.findById(id);
        complainDto.setComplainStatus(EComplainStatus.REJECT);
        complainService.save(complainDto);
        return "redirect:/admin/complainList/0";
    }

    @GetMapping("/update/{id}")
    public String updateAdmin(@PathVariable("id") Long id, Model model) {
        AdminDto adminDto = adminService.findById(id);
        System.out.println(adminDto);
        model.addAttribute("adminDto", adminDto);
        return "admin/updateAdmin";
    }
}