package com.kenzie.photo.integration.helper;

import com.kenzie.photo.activity.ConvertPrimePhotoActivity;
import com.kenzie.photo.dependency.DaggerServiceComponent;
import com.kenzie.photo.dependency.ServiceComponent;

public final class ActivityProvider {
    private static final ServiceComponent DAGGER = DaggerServiceComponent.create();

    private ActivityProvider() {
    }

    public static ConvertPrimePhotoActivity provideConvertPhotoActivity() {
        return DAGGER.provideConvertPhotoActivity();
    }
}
