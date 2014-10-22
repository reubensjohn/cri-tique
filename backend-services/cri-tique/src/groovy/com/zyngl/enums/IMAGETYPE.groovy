package com.zyngl.enums

enum IMAGETYPE {

    PORTRAIT("P"),
    LANDSCAPE("L"),
    SQUARE("S")

    private final String value

    IMAGETYPE(String value) {
        this.value = value;
    }

}
