package com.revature.config;

import org.apache.log4j.Logger;

/**
 * @author Zimi Li
 */
public class Configuration {
    public static final Logger LOGGER = Logger.getLogger("logger");
    public static final String EMAIL_USERNAME = "revature.project1@gmail.com";
    public static final String EMAIL_PASSWORD = System.getenv("EMAIL_PASSWORD");
    public static final Integer PASSWORD_LENGTH = 16;
    public static final String ACCESS_KEY = System.getenv("S3ACCESS");
    public static final String SECRET_KEY = System.getenv("S3SECRET");
    public static final String REGION = "us-east-1";
    public static final String BUCKET_NAME = "test.zimi.li";
    public static final String FOLDER_NAME = "social-network/";
    public static final String S3_URL = "https://s3.amazonaws.com/test.zimi.li/";
    public static final Integer MAXIMUM_FEED_DISPLAYED = 19;
}
