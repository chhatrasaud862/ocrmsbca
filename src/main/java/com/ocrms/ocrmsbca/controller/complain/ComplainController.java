package com.ocrms.ocrmsbca.controller.complain;

import com.ocrms.ocrmsbca.components.Notifications;
import com.ocrms.ocrmsbca.dto.ComplainDto;
import com.ocrms.ocrmsbca.entity.complain.Complain;
import com.ocrms.ocrmsbca.entity.user.User;
import com.ocrms.ocrmsbca.repository.complain.ComplainRepository;
import com.ocrms.ocrmsbca.repository.user.UserRepository;
import com.ocrms.ocrmsbca.service.impl.ComplainServiceImpl;
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
import java.security.Principal;
import java.text.ParseException;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author CHHATRA SAUD
 * @product IntelliJ IDEA
 * @project ocrmsbca
 * @Date 04/07/2022
 */
@Controller
@RequestMapping("/complain")
public class ComplainController {
    private final Notifications notifications;
    private final ComplainRepository complainRepository;
    private  final UserRepository userRepository;
    private final ComplainServiceImpl complainService;

    public ComplainController(Notifications notifications, ComplainRepository complainRepository, UserRepository userRepository, ComplainServiceImpl complainService) {
        this.notifications = notifications;
        this.complainRepository = complainRepository;
        this.userRepository = userRepository;
        this.complainService = complainService;
    }

    @GetMapping
    public String complainPage(Model model)
    {
        model.addAttribute("complainDto",new ComplainDto());
        return "user/complainAdd";
    }
    @GetMapping("/dashboard")
    public String dashboard(Principal principal, Model model){
        String name=principal.getName();
        Long user= userRepository.findUserByEmail(name).getId();
        Map<String, Double> graphData = new TreeMap<>();
        graphData.put("Total Complain", Double.valueOf(complainService.getTotalComplainUser(user)));
        graphData.put("Pending Complain", Double.valueOf(complainService.getPendingComplainUser(user)));
        graphData.put("Complete Complain", Double.valueOf(complainService.getApproveComplainUser(user)));
        graphData.put("Reject Complain", Double.valueOf(complainService.getRejectComplainUser(user)));
        model.addAttribute("chartData", graphData);
        return "user/dashboard";

    }
    @PostMapping
   public String saveComplain(@Valid @ModelAttribute ComplainDto complainDto, Model model, Principal principal, BindingResult bindingResult) throws ParseException, IOException {
       if(!bindingResult.hasErrors()) {
           try {
               String name = principal.getName();
               User user = userRepository.findUserByEmail(name);
               complainDto.setUser(user);
               ComplainDto save = complainService.save(complainDto);
               model.addAttribute("message", "Complain added successfully");
           } catch (Exception e) {
               model.addAttribute("message", "Complain added failed !! try again");
               e.printStackTrace();
           }
       }
      model.addAttribute("complainDto",complainDto);
        return "user/complainAdd";
    }

    @GetMapping("/complainList/{page}")
    public String complainList(@PathVariable("page") Integer page, Model model, Principal principal) throws IOException {
        //get user id
        String userName=principal.getName();
        User user=userRepository.findUserByEmail(userName);
        //pageable
        Pageable pageable= PageRequest.of(page,5);
        Page<Complain> complainList=this.complainRepository.getComplainList(user.getId(),pageable);
       //return
        model.addAttribute("complainList",complainList);
        model.addAttribute("currentPage",page);
        model.addAttribute("totalPage",complainList.getTotalPages());
        return "user/listOfComplain";
    }
    @GetMapping("/update/{id}")
    public String updateComplain(@PathVariable("id") Long id, Model model,
                                 RedirectAttributes redirectAttributes) throws IOException {
        ComplainDto complainDto=complainService.findById(id);
        model.addAttribute("complainDto",complainDto);
        return "user/updateComplain";
    }

    @PostMapping("/update")
    public String updateComplain(@ModelAttribute ComplainDto complainDto,Model model,Principal principal) throws ParseException, IOException {
        try{
            String name=principal.getName();
            User user=userRepository.findUserByEmail(name);
            complainDto.setUser(user);
            ComplainDto save=complainService.update(complainDto);
            model.addAttribute("message","Complain Update Successfully");
        }catch (Exception e)
        {
            model.addAttribute("message","Complain Update Failed !! Try Again");
            e.printStackTrace();
        }
        model.addAttribute("complainDto",complainDto);
        return "user/updateComplain";
    }

    @GetMapping("/notifications")
    public String notification(Principal principal,Model model){
       /* String userName=principal.getName();
        Long user=userRepository.findUserByEmail(userName).getId();
        String name=complainService.getUserName(user);
        String message="Approve Your Complain";
        String mes=notifications.getNotifications(name,message);
        System.out.println("**********");
        System.out.println(mes);
        model.addAttribute("message",mes);*/
        return "user/notifications";
    }


}
