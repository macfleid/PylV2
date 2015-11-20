package com.mcfly.pyl.business.exceptions;

/**
 * *************************************************
 * Kayentis© Pyl
 * *************************************************
 * Last change : [20/11/2015]
 * Author :  [mcfly]
 */
public class PlaylistSavingException extends Exception {

    public PlaylistSavingException() {
        super();
    }

    public PlaylistSavingException(String detailMessage) {
        super(detailMessage);
    }

    public PlaylistSavingException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public PlaylistSavingException(Throwable throwable) {
        super(throwable);
    }
}
