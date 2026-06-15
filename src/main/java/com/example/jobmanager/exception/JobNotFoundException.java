package com.example.jobmanager.exception;

public class JobNotFoundException extends RuntimeException {

    public JobNotFoundException() {
        super("求人が見つからないか、アクセス権限がありません。");
    }
}