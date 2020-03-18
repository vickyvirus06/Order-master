package com.paypay.oa.order.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class IDGenerator {
    private static final String UTC_TIMEZONE = "UTC";

    public static Long generateId() {
        return generateLongId();
    }

    /**
     * Generated Unique ID
     * @return Long
     */
    private static Long generateLongId() {
        ZonedDateTime instant = ZonedDateTime.of(LocalDateTime.now(), ZoneId.of(UTC_TIMEZONE));
        long millis = instant.toInstant().toEpochMilli();
        return millis;
    }
}
