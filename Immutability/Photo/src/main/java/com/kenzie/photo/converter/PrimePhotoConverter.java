package com.kenzie.photo.converter;


import com.kenzie.photo.model.PrimePhoto;

/**
 * Strategy interface for converting images.
 */
public interface PrimePhotoConverter {

    String convert(PrimePhoto image, String imageName);
}
