package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class AppTest {

    @Test
    public void testEvenNumber() {
        assertEquals("Even", App.checkEvenOdd(4));
    }

    @Test
    public void testOddNumber() {
        assertEquals("Odd", App.checkEvenOdd(5));
    }
}