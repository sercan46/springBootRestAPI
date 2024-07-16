package com.sercan.ozbek.springbootrestapi.advice;

import java.io.Serial;

public class UserNotFound extends RuntimeException {
    @Serial
    private static final long serialVersionUID=1L;
    public UserNotFound(String message){
        super(message);
    }
}
