package com.kenzie.photo.util;

import com.kenzie.photo.exception.PhotoConversionClientException;
import com.kenzie.photo.exception.PhotoConversionServiceException;
import com.kenzie.photo.model.ConversionType;
import com.kenzie.photo.model.Pixel;
import com.kenzie.photo.model.PrimePhoto;
import com.kenzie.photo.model.RGB;
import org.apache.commons.io.FilenameUtils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

/**
 * Class containing utility methods to aid in the conversion between PrimePhotos and persistent resources.
 */
public final class PrimePhotoUtil {

    private PrimePhotoUtil(){}

    /**
     * Reads an image file into memory and returns it in a PrimePhoto representation.
     * @param filePath the location of the file to be opened
     * @return a PrimePhoto representation of an image file
     * @throws PhotoConversionClientException if the provided file cannot be opened
     */
    public static PrimePhoto fromFile(String filePath) throws PhotoConversionClientException {
        try {
            File input = new File(filePath);
            BufferedImage image = ImageIO.read(input);

            int width = image.getWidth();
            int height = image.getHeight();

            List<Pixel> pixelList = new ArrayList();
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    pixelList.add(new Pixel(x, y, createRGB(image.getRGB(x, y))));
                }
            }

            return new PrimePhoto(pixelList, height, width, image.getType());
        } catch (IOException e) {
            throw new PhotoConversionClientException(
                String.format("Unable to access provided image file: %s", filePath));
        }
    }

    /**
     * Persists a PrimePhoto in a file. The imageName and conversionType will be used in the final file name. The file
     * name provided will be suffixed with a timestamp and the conversion type.
     * Ex:
     * provided fileName: 'family_in_spain.jpg', conversion applied: 'SEPIA'
     * resulting fileName: 'family_in_spain_1584313755131_sepia.jpg'
     *
     * All files will be stored in the 'output' folder located in 'src'.
     * @param image the PrimePhoto to persist
     * @param imageName the name, including the extension, of the file to be preserved
     * @param conversionType which type of conversion was applied - will be used in the output file name
     * @return the file path of the persisted photo. Format: 'src/output/fileName_timeStamp_conversionType.extension'
     * @throws PhotoConversionServiceException if saving the provided PrimePhoto fails
     */
    public static String savePrimePhoto(PrimePhoto image, String imageName, ConversionType conversionType)
        throws PhotoConversionServiceException {

        String extension = FilenameUtils.getExtension(imageName);
        String fileName = FilenameUtils.removeExtension(imageName);

        String pathPrefix = getFilePathPrefix();

        File f = new File(String.format(pathPrefix + "src/output/%s_%s_%s.%s", fileName, Instant.now().toEpochMilli(),
            conversionType, extension));

        try {
            BufferedImage outImage = new BufferedImage(image.getWidth(), image.getHeight(),
                image.getType());

            for (Pixel p : image.getPixels()) {
                outImage.setRGB(p.getX(), p.getY(), getRGBIntValue(p.getRGB()));
            }

            ImageIO.write(outImage, extension, f);

            return f.getPath();
        } catch (IOException e) {
            throw new PhotoConversionServiceException(
                String.format("Unable to save converted image: %s", f.getPath()));
        }
    }

    private static int getRGBIntValue(RGB rgb) {
        return (rgb.getTransparency() << 24) | (rgb.getRed() << 16) | (rgb.getGreen() << 8) | rgb.getBlue();
    }

    private static RGB createRGB(int bufferedImageRGBValue) {
        return new RGB((bufferedImageRGBValue >> 16) & 0xff, (bufferedImageRGBValue >> 8) & 0xff,
            bufferedImageRGBValue & 0xff, (bufferedImageRGBValue >> 24) & 0xff);
    }

    public static String getFilePathPrefix() {
        if (new File("Immutability").isDirectory()){
            // This is being run from inside Intellij
            return "Immutability/Photo/";
        } else if (new File("src").isDirectory()) {
            // This is being run from Gradle
            return "";
        } else {
            // This is being run from somewhere else, it will most likely fail...
            return "";
        }
    }

}
