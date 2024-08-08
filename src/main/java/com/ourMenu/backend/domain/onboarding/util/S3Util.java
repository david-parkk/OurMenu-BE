package com.ourMenu.backend.domain.onboarding.util;

import java.util.List;

public class S3Util {

    private S3Util() {
    }

    ;
    public static String S3Path = "https://ourmenu.s3.ap-northeast-2.amazonaws.com/온보딩/";

    public static String makeRegexp(List<String> stringList) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : stringList) {
            stringBuilder.append(str).append("|");
        }
        return stringBuilder.toString();
    }

}
