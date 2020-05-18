package com.stated.royally.temp;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.EnumSet;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Contact Type Enum
 *
 * @author Nate Vardell
 * @since 2/28/2020
 */
@Getter
@AllArgsConstructor
public enum ContactType {

    EMAIL(1), PHONE(2);

    private int value;

    private static Map<Integer, ContactType> values;

    static {
        values = EnumSet.allOf(ContactType.class).stream()
                .collect(Collectors.toMap(ContactType::getValue, c -> c));
    }

    public static ContactType fromValue(int value) {
        return values.get(value);
    }
}
