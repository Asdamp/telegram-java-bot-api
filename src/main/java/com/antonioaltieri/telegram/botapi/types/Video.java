package com.antonioaltieri.telegram.botapi.types;

import com.antonioaltieri.telegram.botapi.types.PhotoSize;
import com.google.gson.annotations.SerializedName;

/**
 * This object represents a video file.
 *
 * Any getters labeled <i>optional</i> might return a default value (such as {@code null}).
 *
 * @see <a href="https://core.telegram.org/bots/api#video">https://core.telegram.org/bots/api#video</a>
 */
public class Video {

    @SerializedName("file_id")
    private String fileId;

    @SerializedName("width")
    private int width;

    @SerializedName("height")
    private int height;

    @SerializedName("duration")
    private int duration;

    @SerializedName("thumb")
    private com.antonioaltieri.telegram.botapi.types.PhotoSize thumb;

    @SerializedName("mime_type")
    private String mimeType;

    @SerializedName("file_size")
    private long fileSize;

    /**
     * @return Unique identifier for this file
     */
    public String getFileId() {
        return fileId;
    }

    /**
     * @return Video width as defined by sender
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return Video height as defined by sender
     */
    public int getHeight() {
        return height;
    }

    /**
     * @return Duration of the video in seconds as defined by sender
     */
    public int getDuration() {
        return duration;
    }

    /**
     * <i>Optional.</i>
     *
     * @return Video thumbnail
     */
    public PhotoSize getThumb() {
        return thumb;
    }

    /**
     * <i>Optional.</i>
     *
     * @return Mime type of a file as defined by sender
     */
    public String getMimeType() {
        return mimeType;
    }

    /**
     * <i>Optional.</i>
     *
     * @return File size
     */
    public long getFileSize() {
        return fileSize;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Video{");
        sb.append("fileId='").append(fileId).append('\'');
        sb.append(", width=").append(width);
        sb.append(", height=").append(height);
        sb.append(", duration=").append(duration);
        sb.append(", thumb=").append(thumb);
        sb.append(", mimeType='").append(mimeType).append('\'');
        sb.append(", fileSize=").append(fileSize);
        sb.append('}');
        return sb.toString();
    }
}
