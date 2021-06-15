package com.sidemash.sdk;

public class CallException extends Exception {
    final int statusCode;
    final String statusMessage;
    final String body;
    public CallException(int statusCode, String statusMessage, String body) {
        super(body);
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.body = body;
    }
}