package com.kenzie.photo.activity;

import com.kenzie.photo.converter.*;
import com.kenzie.photo.model.*;
import com.kenzie.photo.exception.*;

import com.google.common.collect.ImmutableList;
import com.kenzie.photo.util.PrimePhotoUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConvertPrimePhotoActivityTest {

    private static final List<ConversionType> SINGLE_CONVERSION_LIST = ImmutableList.of(ConversionType.SEPIA);
    private static final List<ConversionType> MULTIPLE_CONVERSIONS_LIST =  ImmutableList.of(
        ConversionType.SEPIA, ConversionType.GREYSCALE);
    private static String TEST_IMAGE_FILE_PATH = "src/resources/this_is_a_test.png";

    private ConvertPrimePhotoActivity activity;

    private ConverterStrategyMapper mapper;
    private String resolvedFilePath;

    @BeforeEach
    public void setup() {
        resolvedFilePath = PrimePhotoUtil.getFilePathPrefix() + TEST_IMAGE_FILE_PATH;
        mapper = new ConverterStrategyMapper();
        activity = new ConvertPrimePhotoActivity(mapper);
    }


    @Test
    public void handleRequest_invalidImagePath_throwsException() {
        // WHEN && THEN
        assertThrows(PhotoConversionClientException.class,
            () -> activity.handleRequest("badImagePath.jpg", SINGLE_CONVERSION_LIST),
            "Expected handleRequest to throw a client exception when provided an invalid file path");
    }

    @Test
    public void handleRequest_emptyConversionTypesList_throwsException() {
        // WHEN && THEN
        assertThrows(PhotoConversionClientException.class,
            () -> activity.handleRequest(resolvedFilePath, Collections.emptyList()),
            "Expected handleRequest to throw a client exception when provided empty conversion list");
    }

    @Test
    public void handleRequest_nullConversionTypesList_throwsException() {
        // WHEN && THEN
        assertThrows(PhotoConversionClientException.class,
            () -> activity.handleRequest(resolvedFilePath, null),
            "Expected handleRequest to throw a client exception when provided null conversion type");
    }

    @Test
    public void handleRequest_singleConversion_returnsSingleConvertedFile() {
        // WHEN
        List<String> results = activity.handleRequest(resolvedFilePath, SINGLE_CONVERSION_LIST);

        // THEN
        assertEquals(1, results.size(), "Expected a single converted file.");
        assertTrue(results.get(0).contains(SINGLE_CONVERSION_LIST.get(0).toString()), "Expected the result file to " +
            "be for the requested conversion: " + SINGLE_CONVERSION_LIST.get(0).toString());
    }

    @Test
    public void handleRequest_multipleConversions_returnsMultipleConvertedFiles() {
        // WHEN
        List<String> results = activity.handleRequest(resolvedFilePath, MULTIPLE_CONVERSIONS_LIST);

        // THEN
        assertEquals(MULTIPLE_CONVERSIONS_LIST.size(), results.size(), "Expected a single converted file.");
        for(ConversionType conversion : MULTIPLE_CONVERSIONS_LIST) {
            Optional<String> fileToInspectOptional = results.stream()
                .filter(fileName -> fileName.contains(conversion.toString())).findFirst();

            assertTrue(fileToInspectOptional.isPresent(), String.format("ConvertPrimePhotoActivity results missing " +
                "requested %s conversion", conversion.name()));
        }
    }
}
