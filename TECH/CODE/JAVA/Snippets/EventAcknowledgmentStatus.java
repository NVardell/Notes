package com.stated.royally.temp;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.util.Arrays;

/**
 * Event Acknowledgment Status Enum
 *
 * @author Nate Vardell
 * @since 2/28/2020
 */
@Log4j2
@Getter
@AllArgsConstructor
public enum EventAcknowledgmentStatus {

    FAIL("Fail", 511),
    SUCCESS("Success", 512),
    UNKNOWN("Unknown", 999);

    private String status;
    private int statusCode;

    @JsonCreator
    public static EventAcknowledgmentStatus getValue(String value) {
        return Arrays.stream(EventAcknowledgmentStatus.values())
                       .filter(status -> status.name().equalsIgnoreCase(value))
                       .findFirst()
                       .orElseGet(() -> {
                           log.warn("Unsupported status type=" + value + ", returning Unknown.");
                           return UNKNOWN;
                       });
    }
}
