package com.smart.application.utilities;

import java.util.Base64;

public class ImageEncoder {

    public static String encodeImage(byte[] image) {
        return Base64.getEncoder().encodeToString(image);
    }
}
