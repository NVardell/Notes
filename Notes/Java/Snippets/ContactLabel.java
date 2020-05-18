package com.stated.royally.temp;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.EnumSet;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Contact Label Enum
 *
 * @author Nate Vardell
 * @since 2/28/2020
 */
@Getter
@AllArgsConstructor
public enum ContactLabel {

    PRIMARY(1),
    ADDITIONAL(2),
    HOME(3),
    WORK(4),
    CELL(5),
    FAX(6),
    SMS(7),
    INTERNATIONAL_PRIMARY(8),
    INTERNATIONAL_WORK(9);

    private int value;

    private static Map<Integer, ContactLabel> values;

    static {
        values = EnumSet.allOf(ContactLabel.class).stream()
                         .collect(Collectors.toMap(ContactLabel::getValue, c -> c));
    }

    public static ContactLabel fromValue(int value) {
        return values.get(value);
    }
}
