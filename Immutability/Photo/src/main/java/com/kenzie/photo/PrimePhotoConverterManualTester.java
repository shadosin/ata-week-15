package com.kenzie.photo;

import com.google.common.collect.ImmutableList;
import com.kenzie.photo.activity.ConvertPrimePhotoActivity;
import com.kenzie.photo.dependency.DaggerServiceComponent;
import com.kenzie.photo.dependency.ServiceComponent;
import com.kenzie.photo.model.ConversionType;
import com.kenzie.photo.util.PrimePhotoUtil;

/**
 * A class provided for interacting with the PrimePhotoConverterService.
 */
public class PrimePhotoConverterManualTester {

    private static final ServiceComponent DAGGER = DaggerServiceComponent.create();

    /**
     * If you're having issues running the main method, check the "Before starting" steps in the README.
     * @param args Arguments to the java program.
     */
    public static void main(String[] args) {
        String pathPrefix = PrimePhotoUtil.getFilePathPrefix();

        // This test will add multiple filters to the same file
        runTest(pathPrefix + "src/resources/dalmatian.jpg", ImmutableList.of(ConversionType.INVERSION,
            ConversionType.GREYSCALE, ConversionType.SEPIA));

        // This test converts an image with a single filter type
        // Uncomment and run this to compare the output to the test above
        // runTest("Immutability/Photo/src/resources/dalmatian.jpg", ImmutableList.of(ConversionType.SEPIA));
    }

    private static void runTest(String filePath, ImmutableList<ConversionType> conversions) {
        ConvertPrimePhotoActivity activity = DAGGER.provideConvertPhotoActivity();
        activity.handleRequest(filePath, conversions);
    }
}
