package com.kenzie.photo.integration;

import com.kenzie.photo.activity.ConvertPrimePhotoActivity;
import com.kenzie.photo.model.ConversionType;
import com.kenzie.photo.model.Pixel;
import com.kenzie.photo.model.PrimePhoto;
import com.kenzie.photo.util.PrimePhotoUtil;
import com.kenzie.photo.integration.helper.ActivityProvider;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class ConvertPrimePhotoIntegrationTest {

    private static String TEST_IMAGE_FILE_PATH = "src/resources/this_is_a_test.png";

    private String resolvedFilePath;
    private ConvertPrimePhotoActivity activity1;
    private ConvertPrimePhotoActivity activity2;

    @BeforeEach
    private void setup() {
        resolvedFilePath = PrimePhotoUtil.getFilePathPrefix() + TEST_IMAGE_FILE_PATH;
        activity1 = ActivityProvider.provideConvertPhotoActivity();
        activity2 = ActivityProvider.provideConvertPhotoActivity();
    }

    @ParameterizedTest
    @MethodSource("conversionTypeToRequestedList")
    public void handleRequest_multiConversionRequest_createsConvertedImageFile(
        ConversionType expectedConversion, List<ConversionType> requestedConversion){

        // GIVEN
        List<ConversionType> multipleConversions = requestedConversion;
        List<ConversionType> singleConversion = ImmutableList.of(expectedConversion);
        List<String> singleConversionResult = activity1.handleRequest(resolvedFilePath, singleConversion);

        // WHEN
        List<String> result = activity2.handleRequest(resolvedFilePath, multipleConversions);

        // THEN
        verifyConversionsApplied(resolvedFilePath, result);
        verifySingleEqualsMultipleResult(expectedConversion, singleConversionResult, result);
    }

    private void verifyConversionsApplied(String originalFile, List<String> results) {
        PrimePhoto originalPhoto = PrimePhotoUtil.fromFile(originalFile);
        List<Pixel> originalPixels = originalPhoto.getPixels();
        for(String convertedFile : results) {
            PrimePhoto convertedPhoto = PrimePhotoUtil.fromFile(convertedFile);
            List<Pixel> convertedPixels = convertedPhoto.getPixels();

            assertNotEquals(originalPixels, convertedPixels, "No conversion was applied to : " + convertedFile);
        }

    }

    public static Stream<Arguments> conversionTypeToRequestedList() {

        return Stream.of(
            arguments(ConversionType.INVERSION, ImmutableList.of(ConversionType.INVERSION,
                ConversionType.GREYSCALE, ConversionType.SEPIA)),
            arguments(ConversionType.GREYSCALE, ImmutableList.of(ConversionType.INVERSION,
                ConversionType.GREYSCALE, ConversionType.SEPIA)),
            arguments(ConversionType.SEPIA, ImmutableList.of(ConversionType.INVERSION,
                ConversionType.GREYSCALE, ConversionType.SEPIA)));
    }

    private void verifySingleEqualsMultipleResult(ConversionType conversionType, List<String> singleConversionResult,
                                                  List<String> result) {
        String expectedFilePath = singleConversionResult.get(0);
        PrimePhoto expectedPhoto = PrimePhotoUtil.fromFile(expectedFilePath);
        Optional<String> fileToInspectOptional = result.stream()
            .filter(fileName -> fileName.contains(conversionType.toString())).findFirst();

        assertTrue(fileToInspectOptional.isPresent(), String.format("ConvertPrimePhotoActivity results missing " +
            "requested %s conversion", conversionType.name()));

        String resultingFilePath = fileToInspectOptional.get();
        PrimePhoto convertedPhoto = PrimePhotoUtil.fromFile(resultingFilePath);

        assertEquals(expectedPhoto, convertedPhoto, String.format("ConvertPrimePhotoActivity results contain " +
            "incorrect conversion for %s. Please manually inspect \n - Expected: %s \n - Actual: %s",
            conversionType.name(),
            expectedFilePath,
            resultingFilePath));
    }

}
