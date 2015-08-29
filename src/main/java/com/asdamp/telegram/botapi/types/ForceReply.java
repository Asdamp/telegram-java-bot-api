package com.asdamp.telegram.botapi.types;

import com.asdamp.telegram.botapi.requests.OptionalArgs;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 * Upon receiving a message with this object, Telegram clients will display a reply interface to the user (act as if the user has selected the bot's message and tapped 'Reply').
 * This can be extremely useful if you want to create user-friendly step-by-step interfaces without having to sacrifice privacy mode.
 *
 * See {@link OptionalArgs} to see how to pass this class as an argument.
 *
 * @see <a href="https://core.telegram.org/bots/api#forcereply>https://core.telegram.org/bots/api#forcereply</a>
 */
public class ForceReply implements ReplyMarkup {

    private static final ForceReply SELECTIVE = new ForceReply(true);
    private static final ForceReply NON_SELECTIVE = new ForceReply(false);

    @SerializedName("force_reply")
    private boolean forceReply = true;
    @SerializedName("selective")
    private boolean selective = false;

    private ForceReply(boolean selective) {
        this.selective = selective;
    }

    /**
     * Returns a selective instance of this class ({@code selective} is set to {@code true}).
     *
     * @return a selective instance of this class
     */
    public static ForceReply getSelective() {
        return SELECTIVE;
    }

    /**
     * Returns a non-selective instance of this class ({@code selective} is set to {@code false}).
     *
     * @return a non-selective instance of this class
     */
    public static ForceReply getNonSelective() {
        return NON_SELECTIVE;
    }

    /**
     * Serializes this object to a JSON String.
     *
     * @return A JSON String representation of this object.
     */
    @Override
    public String serialize() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ForceReply{");
        sb.append("forceReply=").append(forceReply);
        sb.append(", selective=").append(selective);
        sb.append('}');
        return sb.toString();
    }
}
