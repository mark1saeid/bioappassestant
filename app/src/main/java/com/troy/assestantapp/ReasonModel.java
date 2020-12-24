package com.troy.assestantapp;

public class ReasonModel {
   String name;
   String reason;
String marks;

    public ReasonModel() {
    }

    public ReasonModel(String name, String reason, String marks) {
        this.name = name;
        this.reason = reason;
        this.marks = marks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }
}
