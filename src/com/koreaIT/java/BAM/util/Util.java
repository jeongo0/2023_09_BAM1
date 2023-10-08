package com.koreaIT.java.BAM.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Util {
	/** 현재 날짜 및 시간 리턴 String */
    public static String getNow() {
 
        LocalDateTime now = LocalDateTime.now();
 
        String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
 
        return formatedNow;
    }
}