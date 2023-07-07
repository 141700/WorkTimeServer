package ru.massandrashop.worktimeserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.massandrashop.worktimeserver.dto.PointDto;
import ru.massandrashop.worktimeserver.exception.PointNotFoundException;
import ru.massandrashop.worktimeserver.form.PointForm;
import ru.massandrashop.worktimeserver.model.Office;
import ru.massandrashop.worktimeserver.model.Point;
import ru.massandrashop.worktimeserver.repository.PointJpaRepository;
import ru.massandrashop.worktimeserver.repository.PointLocalRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PointService {

    private final PointLocalRepository pointLocalRepository;

    private final PointJpaRepository pointJpaRepository;

    private final OfficeService officeService;


    public List<Point> findAllPoints() {
        return new ArrayList<>(pointLocalRepository.getPointLocalRepository().values());
    }

    public List<PointDto> getAllPointsDto() {
        return pointLocalRepository.getPointLocalRepository().values().stream()
                .map(PointDto::new)
                .collect(Collectors.toList());
    }

    public Office getOffice(String macAddress) {
        return getPoint(macAddress).getOffice();
    }

    public Point getPoint(String macAddress) {
        ConcurrentHashMap<String, Point> localRepository = pointLocalRepository.getPointLocalRepository();
        return localRepository.computeIfAbsent(macAddress, k -> findByMacAddress(macAddress));
    }

    @Transactional
    public Point findByMacAddress(String macAddress) {
        return pointJpaRepository.findByMacAddress(macAddress)
                .orElseThrow(() -> new PointNotFoundException(macAddress));
    }

    @Transactional
    public void addNewPoint(PointForm form) {
        Office office = officeService.findById(form.getOfficeId());
        Point point = new Point(form.getTitle(), form.getMacAddress(), office, Point.IdTypes.valueOf(form.getType()));
        pointLocalRepository.addPoint(pointJpaRepository.save(point));
    }

}