package com.asdamp.telegram.botapi.types;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Antonio on 30/08/2015.
 */
public class Voice {
    @SerializedName("file_id")
    private String fileId;

    @SerializedName("duration")
    private int duration;

    @SerializedName("mime_type")
    private String mimeType;

    @SerializedName("file_size")
    private int fileSize;

    /**
     * @return Unique identifier for this file
     */
    public String getFileId() {
        return fileId;
    }

    /**
     * @return Duration of the audio in seconds as defined by sender
     */
    public int getDuration() {
        return duration;
    }

    /**
     * <i>Optional.</i>
     *
     * @return MIME type of the file as defined by sender
     */
    public String getMimeType() {
        return mimeType;
    }

    /**
     * <i>Optional.</i>
     *
     * @return The size of this audio file.
     */
    public int getFileSize() {
        return fileSize;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Audio{");
        sb.append("fileId='").append(fileId).append('\'');
        sb.append(", duration=").append(duration);
        sb.append(", mimeType='").append(mimeType).append('\'');
        sb.append(", fileSize=").append(fileSize);
        sb.append('}');
        return sb.toString();
    }
}
