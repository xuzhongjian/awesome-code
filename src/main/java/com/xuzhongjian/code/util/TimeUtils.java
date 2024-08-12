package com.xuzhongjian.code.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {
    public static String datetime(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date(timestamp));
    }
}
