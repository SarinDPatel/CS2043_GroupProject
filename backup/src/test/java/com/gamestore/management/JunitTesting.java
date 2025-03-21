package com.gamestore.management;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class JunitTesting {
    @Test
    void testAddition() {
        System.out.println("Testing");
        assertEquals(4, 2 + 2);  // Correct method name
    }
}
