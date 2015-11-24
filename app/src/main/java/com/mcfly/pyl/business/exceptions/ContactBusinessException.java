package com.mcfly.pyl.business.exceptions;

/**
 * *************************************************
 * Mcfly© Pyl
 * *************************************************
 * Last change : [20/11/2015]
 * Author :  [mcfly]
 */
public class ContactBusinessException extends Exception {

    public ContactBusinessException() {
        super();
    }

    public ContactBusinessException(String detailMessage) {
        super(detailMessage);
    }

    public ContactBusinessException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public ContactBusinessException(Throwable throwable) {
        super(throwable);
    }
}
