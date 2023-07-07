package ru.massandrashop.worktimeserver.form;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PointForm {

    private String title;

    private String macAddress;

    private Long officeId;

    private String type;

    public String getMacAddress() {
        return this.macAddress.toUpperCase();
    }

}
