package com.ocrms.ocrmsbca.controller.complain;

import com.ocrms.ocrmsbca.dto.ComplainDto;
import com.ocrms.ocrmsbca.service.complain.ComplainService;
import com.ocrms.ocrmsbca.service.impl.ComplainServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.text.ParseException;

/**
 * @author CHHATRA SAUD
 * @product IntelliJ IDEA
 * @project ocrmsbca
 * @Date 04/07/2022
 */
@Controller
@RequestMapping("/complain")
public class ComplainController {
    private final ComplainServiceImpl complainService;

    public ComplainController(ComplainServiceImpl complainService) {
        this.complainService = complainService;
    }

    @GetMapping
    public String complainPage(Model model)
    {
        model.addAttribute("complainDto",new ComplainDto());
        return "user/addComplain";
    }
    @PostMapping
   public String saveComplain(@ModelAttribute ComplainDto complainDto,Model model) throws ParseException, IOException {
        try{
            ComplainDto save=complainService.save(complainDto);
            model.addAttribute("message","Complain added successfully");
        }catch (Exception e)
        {
            model.addAttribute("message","Complain added failed !! try again");
            e.printStackTrace();
        }
      model.addAttribute("complainDto",complainDto);
        return "user/addComplain";
    }

    @GetMapping("/complainList")
    public String complainList(Model model) throws IOException {
        model.addAttribute("complainList",complainService.findAll());
          return "user/complainList";
    }
}
