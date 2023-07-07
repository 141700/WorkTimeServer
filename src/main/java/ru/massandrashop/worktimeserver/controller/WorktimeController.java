package ru.massandrashop.worktimeserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.massandrashop.worktimeserver.form.WorktimeForm;
import ru.massandrashop.worktimeserver.service.WorktimeRecordService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class WorktimeController {

    private final WorktimeRecordService worktimeRecordService;

    @GetMapping("/worktime")
    public String handleWorktime(WorktimeForm form, Model model) {
        model.addAttribute("records", Collections.emptyMap());
        return "worktime";
    }

    @PostMapping("/worktime")
    public String handleWorktimeSubmit(WorktimeForm form, Model model) {
        LocalDateTime start = (form.getBegin().length() > 0 ? LocalDate.parse(form.getBegin()) : LocalDate.now()).atStartOfDay();
        LocalDateTime end = (form.getEnd().length() > 0 ? LocalDate.parse(form.getEnd()) : LocalDate.now()).atTime(LocalTime.MAX);
        model.addAttribute("records", worktimeRecordService.getOfficeWorktimeRecords(start, end));
        return "worktime";
    }

}
