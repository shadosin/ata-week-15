package com.kenzie.photo.model;

import java.util.Objects;

/**
 * An object that represent colors. Each object represents the color as three integers that stand for primary color
 * values.
 */
public class RGB {

    private int red;
    private int green;
    private int blue;
    private int transparency;

    public RGB(int red, int green, int blue, int transparency) {
        check(red, green, blue, transparency);
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.transparency = transparency;
    }

    private void check(int r, int g, int b, int t) {
        if (r < 0 || r > 255 ||
            g < 0 || g > 255 ||
            b < 0 || b > 255) {
            throw new IllegalArgumentException(String.format("Invalid values color values. Red, green, " +
                "and blue must be between 0 and 255: {red: %d , green: %d, blue: %d}", r, g, b));
        }
        if (t < 0 || t > 255) {
            throw new IllegalArgumentException("Invalid transparency value: " + t);
        }
    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }

    public int getTransparency() {
        return transparency;
    }

    /**
     * Averages the red, blue, and green components, producing a grey color.
     */
    public void toGreyScale() {
        int avg = (red + green + blue) / 3;

        red = avg;
        green = avg;
        blue = avg;
    }

    /**
     * Converts the color to a reddish-brown color.
     */
    public void toSepia() {
        int newRed = (int) (0.393 * red + 0.769 * green + 0.189 * blue);
        int newGreen = (int) (0.349 * red + 0.686 * green + 0.168 * blue);
        int newBlue = (int) (0.272 * red + 0.534 * green + 0.131 * blue);

        red = Math.min(255, newRed);
        green = Math.min(255, newGreen);
        blue = Math.min(255, newBlue);
    }

    /**
     * "Dark mode" - Assigns red, green, and blue, their current value subtracted from their max value (255).
     * This will turn white to black.
     */
    public void invert() {
        red = 255 - red;
        green = 255 - green;
        blue = 255 - blue;
    }

    @Override
    public int hashCode() {
        return Objects.hash(red, green, blue, transparency);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        RGB rgb = (RGB) obj;

        return rgb.red == this.red && rgb.green == this.green &&
            rgb.blue == this.blue && rgb.transparency == this.transparency;
    }
}
