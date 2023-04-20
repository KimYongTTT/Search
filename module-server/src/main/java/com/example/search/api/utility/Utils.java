package com.example.search.api.utility;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Utils {
    //validate a email address with regex
    public static boolean isValidEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    //create an IAM policy with read and write access to S3 bucket
    public static String createPolicy(String bucketName) {
        return "{\n" +
                "    \"Version\": \"2012-10-17\",\n" +
                "    \"Statement\": [\n" +
                "        {\n" +
                "            \"Effect\": \"Allow\",\n" +
                "            \"Action\": [\n" +
                "                \"s3:GetObject\",\n" +
                "                \"s3:PutObject\"\n" +
                "            ],\n" +
                "            \"Resource\": [\n" +
                "                \"arn:aws:s3:::" + bucketName + "/*\",\n" +
                "                \"arn:aws:s3:::" + bucketName + "\"\n" +
                "            ]\n" +
                "        }\n" +
                "    ]\n" +
                "}";
    }

    // upload file to s3 bucket using AWS JAVA SDK
    public static void uploadFile(String bucketName, String fileName, String fileContent) {
        //TODO: upload file to s3 bucket
    }
}
