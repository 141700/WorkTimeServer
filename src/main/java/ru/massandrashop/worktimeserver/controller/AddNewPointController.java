package ru.massandrashop.worktimeserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.massandrashop.worktimeserver.form.PointForm;
import ru.massandrashop.worktimeserver.model.Point;
import ru.massandrashop.worktimeserver.service.OfficeService;
import ru.massandrashop.worktimeserver.service.PointService;
import ru.massandrashop.worktimeserver.util.InputFormValidator;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class AddNewPointController {

    private final OfficeService officeService;

    private final PointService pointService;

    private static final List<Point.IdTypes> ID_TYPES_LIST = Arrays.asList(Point.IdTypes.values());

    @GetMapping("/addnewpoint")
    public String handleAddNewPoint(PointForm form, Model model) {
        model.addAttribute("offices", officeService.findAllOffices());
        model.addAttribute("points", pointService.findAllPoints());
        model.addAttribute("types", ID_TYPES_LIST);
        return "point";
    }

    @PostMapping("/addnewpoint")
    public String handleAddNewPointSubmit(PointForm form, Model model) {
        Optional<String> formValidation = InputFormValidator.validatePointForm(form);
        if (formValidation.isEmpty()) {
            pointService.addNewPoint(form);
        } else {
            model.addAttribute(formValidation.get(), true);
        }
        return handleAddNewPoint(form, model);
    }

}
