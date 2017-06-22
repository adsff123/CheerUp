package org.androidtown.cheerup;

/**
 * Created by USER on 2017-06-23.
 */

public class ReplyData {
    private String userName;
    private String message;

    public ReplyData() { }

    public ReplyData(String userName, String message) {
        this.userName = userName;
        this.message = message;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
