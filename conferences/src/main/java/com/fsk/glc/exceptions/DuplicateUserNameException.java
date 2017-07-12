package com.fsk.glc.exceptions;

public class DuplicateUserNameException extends Exception{
	private static final long serialVersionUID = -4658463190108406055L;
    private String msg;

    public DuplicateUserNameException() {
        super();
    }

    public DuplicateUserNameException(String msg) {
        this.msg = System.currentTimeMillis()
                + ": " + msg;
    }
    public String getMsg() {
        return msg;
    }

}
