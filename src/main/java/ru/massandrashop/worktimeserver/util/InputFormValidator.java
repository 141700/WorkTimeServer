package ru.massandrashop.worktimeserver.util;

import ru.massandrashop.worktimeserver.form.PointForm;

import java.util.Optional;
import java.util.regex.Pattern;

public class InputFormValidator {

    private static final Pattern MAC_ADDRESS_PATTERN = Pattern.compile("^([0-9A-Fa-f]{2}[:]){5}([0-9A-Fa-f]{2})$");

    public static Optional<String> validatePointForm(PointForm form) {
        if (form.getOfficeId() == null) {
            return Optional.of("officeerror");
        }
        if (!isTitleValid(form.getTitle())) {
            return Optional.of("titleerror");
        }
        if (!isMacAddressValid(form.getMacAddress())) {
            return Optional.of("addresserror");
        }
        return Optional.empty();
    }

    public static boolean isTitleValid(String title) {
        return title.length() > 1 && title.length() < 31;
    }

    public static boolean isMacAddressValid(String address) {
        return MAC_ADDRESS_PATTERN.matcher(address).find();
    }

}
