package com.kenzie.photo.dependency;

import com.kenzie.photo.activity.ConvertPrimePhotoActivity;
import dagger.Component;

import javax.inject.Singleton;

/**
 * Declares the dependency roots that Dagger will provide.
 */
@Singleton
@Component()
public interface ServiceComponent {
    ConvertPrimePhotoActivity provideConvertPhotoActivity();
}
