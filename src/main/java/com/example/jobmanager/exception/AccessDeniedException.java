
package com.example.jobmanager.exception;

public class AccessDeniedException extends RuntimeException {

    public AccessDeniedException() {
        super("この求人にアクセスする権限がありません。");
    }
}