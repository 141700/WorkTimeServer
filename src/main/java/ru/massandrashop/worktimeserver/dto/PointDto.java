package ru.massandrashop.worktimeserver.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import ru.massandrashop.worktimeserver.model.Point;

import java.io.Serializable;

@Getter
public class PointDto implements Serializable {

    @NotNull
    private final String macAddress;

    @NotNull
    private final String title;

    @NotNull
    private final String officeTitle;

    @NotNull
    private final Point.IdTypes type;

    public PointDto(Point point) {
        this.macAddress = point.getMacAddress();
        this.title = point.getTitle();
        this.officeTitle = point.getOffice().getTitle();
        this.type = point.getType();
    }

}
