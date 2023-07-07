package ru.massandrashop.worktimeserver.repository;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import ru.massandrashop.worktimeserver.model.Point;

import java.util.concurrent.ConcurrentHashMap;

@Getter
@Setter
@Component
@NoArgsConstructor
public class PointLocalRepository {

    private ConcurrentHashMap<String, Point> pointLocalRepository = new ConcurrentHashMap<>();

    public void addPoint(Point point) {
        pointLocalRepository.put(point.getMacAddress(), point);
    }

}
