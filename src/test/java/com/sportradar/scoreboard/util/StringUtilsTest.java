package com.sportradar.scoreboard.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class StringUtilsTest {

    @Test
    void checkNull() {
        assertFalse(StringUtils.isNotEmptyAlphabetic(null));
    }

    @Test
    void checkEmpty() {
        assertFalse(StringUtils.isNotEmptyAlphabetic(""));
    }

    @Test
    void checkAlphanumeric() {
        assertFalse(StringUtils.isNotEmptyAlphabetic("thisIsTestTextWithNumbers123"));
    }

    @Test
    void checkAlphabetic() {
        assertTrue(StringUtils.isNotEmptyAlphabetic("thisIsJustText"));
    }

}
