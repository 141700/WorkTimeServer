package ru.massandrashop.worktimeserver.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.massandrashop.worktimeserver.service.NewRecordService;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/new", produces = MediaType.APPLICATION_JSON_VALUE)
public class NewRecordRestController {

    private final NewRecordService newRecordService;

    @PostMapping
    public void addNewRecord(@RequestBody Map<String, String> input) {
        log.info("-------------> New record incoming from " + input.get("name"));
        newRecordService.addNewRecord(input.get("name"), input.get("deviceId"), input.get("macAddress").toUpperCase());
    }
}