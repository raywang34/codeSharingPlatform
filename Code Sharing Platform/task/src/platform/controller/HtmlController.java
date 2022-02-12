package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;
import platform.model.CodeInfo;
import platform.service.CodeInfoService;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class HtmlController {

    @Autowired
    CodeInfoService codeInfoService;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm:ss");

    @GetMapping("/code/{i}")
    public String getCodeInfo(@PathVariable String i, Model model) {
        if (i.equals("latest")) {
            model.addAttribute("title", "Latest");
            model.addAttribute("codeInfos", codeInfoService.findTop10ByNonRestrictedOrderByDateDesc());
        } else {
            CodeInfo codeInfo = codeInfoService.findById(i);

            if (codeInfo == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            } else {
                model.addAttribute("title", "Code");
                model.addAttribute("codeInfos", List.of(codeInfo));
            }
        }

        return "CodeInfos";
    }

    @GetMapping("/code/new")
    public String form() {
        return "Form";
    }
}