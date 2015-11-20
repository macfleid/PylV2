package com.mcfly.pyl.business.exceptions;

/**
 * *************************************************
 * Mcfly© Pyl
 * *************************************************
 * Last change : [20/11/2015]
 * Author :  [mcfly]
 */
public class SharePlaylistException extends Exception {

    public SharePlaylistException() {
        super();
    }

    public SharePlaylistException(String detailMessage) {
        super(detailMessage);
    }

    public SharePlaylistException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public SharePlaylistException(Throwable throwable) {
        super(throwable);
    }
}
