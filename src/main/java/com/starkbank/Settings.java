package com.starkbank;

import com.starkcore.user.User;

public abstract class Settings{
    public static User user = null;
    public static String userAgentOverride = null;
    public static String language = "en-US";
    public static String version = "2.15.0";
    public static int timeout = 15;
}
