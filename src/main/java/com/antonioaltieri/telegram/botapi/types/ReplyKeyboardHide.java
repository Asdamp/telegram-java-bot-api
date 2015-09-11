package com.antonioaltieri.telegram.botapi.types;

import com.antonioaltieri.telegram.botapi.types.ReplyKeyboardMarkup;
import com.antonioaltieri.telegram.botapi.types.ReplyMarkup;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 * Upon receiving a message with this object, Telegram clients will hide the current custom keyboard and display the default letter-keyboard.
 * By default, custom keyboards are displayed until a new keyboard is sent by a bot.
 * An exception is made for one-time keyboards that are hidden immediately after the user presses a button (see {@link ReplyKeyboardMarkup})
 *
 * @see <a href="https://core.telegram.org/bots/api#replykeyboardhide">https://core.telegram.org/bots/api#replykeyboardhide</a>
 */
public class ReplyKeyboardHide implements ReplyMarkup {

    private final static com.antonioaltieri.telegram.botapi.types.ReplyKeyboardHide NON_SELECTIVE = new com.antonioaltieri.telegram.botapi.types.ReplyKeyboardHide(false);
    private final static com.antonioaltieri.telegram.botapi.types.ReplyKeyboardHide SELECTIVE = new com.antonioaltieri.telegram.botapi.types.ReplyKeyboardHide(true);

    @SerializedName("hide_keyboard")
    private boolean hideKeyboard = true;
    @SerializedName("selective")
    private boolean selective = false;

    private ReplyKeyboardHide(boolean selective) {
        this.selective = selective;
    }

    /**
     * Returns a selective instance of this class ({@code selective} is set to {@code true}).
     *
     * @return a selective instance of this class
     */
    public static com.antonioaltieri.telegram.botapi.types.ReplyKeyboardHide getSelectiveInstance() {
        return SELECTIVE;
    }

    /**
     * Returns a non-selective instance of this class ({@code selective} is set to {@code false}).
     *
     * @return a non-selective instance of this class
     */
    public static com.antonioaltieri.telegram.botapi.types.ReplyKeyboardHide getNonSelectiveInstance() {
        return NON_SELECTIVE;
    }

    /**
     * Serializes this object to a JSON String.
     *
     * @return A JSON String representation of this object.
     */
    @Override
    public String serialize() {
        return new Gson().toJson(this);
    }
}
