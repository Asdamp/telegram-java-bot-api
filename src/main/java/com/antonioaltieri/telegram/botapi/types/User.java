package com.antonioaltieri.telegram.botapi.types;


import com.google.gson.annotations.SerializedName;

/**
 * This object represents a Telegram user or bot.
 *
 * Any getters labeled <i>optional</i> might return a default value (such as {@code null}).
 *
 * @see <a href="https://core.telegram.org/bots/api#user">https://core.telegram.org/bots/api#user</a>
 */
public class User {

    @SerializedName("id")
    private long id;

    @SerializedName("first_name")
    private String firstName;

    @SerializedName("last_name")
    private String lastName;

    @SerializedName("username")
    private String username;

    /**
     * Parameterless constructor for gson.
     */
    public User() {

    }

    public User(long id, String firstName, String lastName, String username) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
    }

    /**
     * @return Unique identifier for this user or bot
     */
    public long getId() {
        return id;
    }

    /**
     * @return User‘s or bot’s first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * <i>Optional.</i>
     *
     * @return User‘s or bot’s last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * <i>Optional.</i>
     *
     * @return User‘s or bot’s username
     */
    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id=").append(id);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", username='").append(username).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
