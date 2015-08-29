package com.asdamp.telegram.botapi.requests;

public enum ChatAction {
    TYPING,
    UPLOAD_PHOTO,
    RECORD_VIDEO,
    UPLOAD_VIDEO,
    RECORD_AUDIO,
    UPLOAD_AUDIO,
    UPLOAD_DOCUMENT,
    FIND_LOCATION;

    public String getAction() {
        return name().toLowerCase();
    }
}
