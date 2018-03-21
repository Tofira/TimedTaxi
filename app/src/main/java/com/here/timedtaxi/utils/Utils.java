package com.here.timedtaxi.utils;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by Mickael on 21/03/2018.
 */

public class Utils {

    private static final long MIN_ETA_TIME =  TimeUnit.MINUTES.toMillis(1);

    private static final long MAX_ETA_TIME = TimeUnit.MINUTES.toMillis(60);

    public static int getRandomETA()
    {
        Random r = new Random();
        return r.nextInt((int)(MAX_ETA_TIME - MIN_ETA_TIME + 1)) + (int)MIN_ETA_TIME;
    }

    public static String parseETA(long eta)
    {
        return String.valueOf(TimeUnit.MILLISECONDS.toMinutes(eta));
    }

}
