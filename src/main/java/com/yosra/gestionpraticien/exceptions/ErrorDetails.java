package com.yosra.gestionpraticien.exceptions;

public class ErrorDetails {
    private String objectName;
    private String field;
    private String code;

    public ErrorDetails(String objectName, String field, String code) {
        this.objectName = objectName;
        this.field = field;
        this.code = code;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
