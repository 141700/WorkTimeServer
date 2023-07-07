package ru.massandrashop.worktimeserver.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.massandrashop.worktimeserver.dto.PointDto;
import ru.massandrashop.worktimeserver.service.PointService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/")
public class PointRestController {

    private final PointService pointService;

    @GetMapping(value = "/getpoints")
    public List<PointDto> getAllPoints() {
        log.info("-------------> Income request for points update");
        return pointService.getAllPointsDto();
    }

}