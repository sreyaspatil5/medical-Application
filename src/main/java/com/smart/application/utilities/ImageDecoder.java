package com.smart.application.utilities;

import java.util.Base64;

public class ImageDecoder {

    public static byte[] decodeImage(String base64) {
        return Base64.getDecoder().decode(base64);
    }
}
