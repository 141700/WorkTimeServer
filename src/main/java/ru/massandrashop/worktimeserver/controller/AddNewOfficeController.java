package ru.massandrashop.worktimeserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.massandrashop.worktimeserver.form.OfficeForm;
import ru.massandrashop.worktimeserver.service.OfficeService;
import ru.massandrashop.worktimeserver.util.InputFormValidator;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class AddNewOfficeController {

    private final OfficeService officeService;

    @GetMapping("/addnewoffice")
    public String handleAddNewOffice(OfficeForm form, Model model) {
        model.addAttribute("offices", officeService.findAllOffices());
        return "office";
    }

    @PostMapping("/addnewoffice")
    public String handleAddNewOfficeSubmit(OfficeForm form, Model model) {
        String title = form.getTitle();
        if (InputFormValidator.isTitleValid(title)) {
            officeService.addNewOffice(title);
        } else {
            model.addAttribute("titleerror", true);
        }
        return handleAddNewOffice(form, model);
    }

}
