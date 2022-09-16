package com.ocrms.ocrmsbca.controller.user;

import com.ocrms.ocrmsbca.repository.complain.ComplainRepository;
import com.ocrms.ocrmsbca.repository.user.UserRepository;
import com.ocrms.ocrmsbca.service.impl.ComplainServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author CHHATRA SAUD
 * @product IntelliJ IDEA
 * @project ocrmsbca
 * @Date 04/07/2022
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private final ComplainServiceImpl complainService;
    private final ComplainRepository complainRepository;
    private final UserRepository userRepository;

    public UserController(ComplainServiceImpl complainService, ComplainRepository complainRepository, UserRepository userRepository) {
        this.complainService = complainService;
        this.complainRepository = complainRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/landing")
    public String landingPage(Principal principal, Model model){
        String name=principal.getName();
        Long user= userRepository.findUserByEmail(name).getId();
        Map<String, Double> graphData = new TreeMap<>();
        graphData.put("Total Complain", Double.valueOf(complainService.getTotalComplainUser(user)));
        graphData.put("Pending Complain", Double.valueOf(complainService.getPendingComplainUser(user)));
        graphData.put("Complete Complain", Double.valueOf(complainService.getApproveComplainUser(user)));
        graphData.put("Reject Complain", Double.valueOf(complainService.getRejectComplainUser(user)));
        model.addAttribute("chartData", graphData);
        return "user/userHome";
    }

}
