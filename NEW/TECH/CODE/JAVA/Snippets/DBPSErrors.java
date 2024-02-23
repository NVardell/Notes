package com.stated.royally.temp;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * TODO - Add Class Definition
 *
 * @author Nate Vardell
 * @since 2/28/2020
 */
@Getter
@AllArgsConstructor
public enum DBPSErrors {
    // 600 - Host Errors
    CLASH_HOST_ERROR(601, "Clash Host Error"),
    MY_HOST_ERROR(602, "My Host Error"),

    // 700 - Mongo DB Errors
    MONGO_LOAD_ERROR(701, "Mongo Load Data Error"),
    MONGO_SAVE_ERROR(702, "Mongo Save Data Error");

    private int errorCode;
    private String errorMessage;
}
