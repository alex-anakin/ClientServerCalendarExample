package org.diosoft.anakin.exception;

import java.io.Serializable;

public class NullCalendarException extends Exception implements Serializable {
    private String detailMessage;

    public NullCalendarException() {
        this.detailMessage = "Time for Event is not found";
    }

    public String getLocalizedMessage() {
        return detailMessage;
    }
}
