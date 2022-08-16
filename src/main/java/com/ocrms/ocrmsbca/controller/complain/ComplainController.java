package com.ocrms.ocrmsbca.controller.complain;

import com.ocrms.ocrmsbca.dto.ComplainDto;
import com.ocrms.ocrmsbca.entity.complain.Complain;
import com.ocrms.ocrmsbca.entity.user.User;
import com.ocrms.ocrmsbca.repository.complain.ComplainRepository;
import com.ocrms.ocrmsbca.repository.user.UserRepository;
import com.ocrms.ocrmsbca.service.impl.ComplainServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.text.ParseException;
import java.util.List;

/**
 * @author CHHATRA SAUD
 * @product IntelliJ IDEA
 * @project ocrmsbca
 * @Date 04/07/2022
 */
@Controller
@RequestMapping("/complain")
public class ComplainController {

    private final ComplainRepository complainRepository;
    private  final UserRepository userRepository;
    private final ComplainServiceImpl complainService;

    public ComplainController(ComplainRepository complainRepository, UserRepository userRepository, ComplainServiceImpl complainService) {
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
    @PostMapping
   public String saveComplain(@ModelAttribute ComplainDto complainDto,Model model,Principal principal) throws ParseException, IOException {
        try{
            String name=principal.getName();
            User user=userRepository.findUserByEmail(name);
            complainDto.setUser(user);
            ComplainDto save=complainService.save(complainDto);
            model.addAttribute("message","Complain added successfully");
        }catch (Exception e)
        {
            model.addAttribute("message","Complain added failed !! try again");
            e.printStackTrace();
        }
      model.addAttribute("complainDto",complainDto);
        return "user/complainAdd";
    }

    @GetMapping("/complainList")
    public String complainList(Model model, Principal principal) throws IOException {
        String userName=principal.getName();
        User user=userRepository.findUserByEmail(userName);
        List<Complain> complainList=this.complainRepository.getComplainList(user.getId());
       model.addAttribute("complainList",complainList);
          return "user/complainList";
    }
    @GetMapping("/update/{id}")
    public String updateComplain(@PathVariable("id") Long id, Model model,
                                 RedirectAttributes redirectAttributes) throws IOException {
        ComplainDto complainDto=complainService.findById(id);
        model.addAttribute("complainDto",complainDto);
        return "user/complainUpdatePage";
    }
}
