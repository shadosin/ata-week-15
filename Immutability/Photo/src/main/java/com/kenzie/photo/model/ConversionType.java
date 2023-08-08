package com.kenzie.photo.model;

/**
 * Image conversion types.
 */
public enum ConversionType {

    SEPIA("sepia"),
    GREYSCALE("greyscale"),
    INVERSION("inversion");

    private final String name;

    ConversionType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
