package com.example.plazoleta.ms_plazoleta.domain.utils.helpers;

public class PinGenerator {
    public static String generate() {
        int pin = (int) (Math.random() * 900000) + 100000;
        return String.valueOf(pin);
    }
}