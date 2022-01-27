package com.example.tedc.models;

public class VerificationResult {

    boolean hasError;
    Object data;

    public boolean isHasError() {
        return hasError;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public VerificationResult(boolean hasError, Object data) {
        this.hasError = hasError;
        this.data = data;
    }
}
