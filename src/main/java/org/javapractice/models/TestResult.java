package org.javapractice.models;

public class TestResult {
    private String id;
    private String status;

    public TestResult(String id, String status) {
        this.id = id;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }
}
