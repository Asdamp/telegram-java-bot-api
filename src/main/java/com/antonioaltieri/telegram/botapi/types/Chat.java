package com.antonioaltieri.telegram.botapi.types;

import com.google.gson.annotations.SerializedName;

/**
 * Represents a chat.
 */
public class Chat {

    @SerializedName("id")
    private long id;

    @SerializedName("first_name")
    private String firstName;

    @SerializedName("type")
    private String type;

    @SerializedName("last_name")
    private String lastName;

    @SerializedName("username")
    private String username;

    @SerializedName("title")
    private String title;

    /**
     * @return Whether this is a chat with a {@link User}
     */
    public boolean isUser() {
        return type.equals(ChatTypes.PRIVATE);
    }

    /**
     * @return Whether this is a Group Chat
     */
    public boolean isGroupChat() {
        return type.equals(ChatTypes.GROUP);
    }
    /**
     * @return Whether this is a SuperGroup Chat
     */
    public boolean isSuperGroupChat() {
        return type.equals(ChatTypes.SUPERGROUP);
    }
    /**
     * @return Whether this is a Channel Chat
     */
    public boolean isChannelChat() {
        return type.equals(ChatTypes.CHANNEL);
    }
    /**
     * @return Unique identifier for this chat
     */
    public long getId() {
        return id;
    }

    /**
     * Returns this chat as a {@link User}.
     * Before invoking, check whether this chat is actually a chat with a user
     *
     * @return This chat as a {@link User} object
     */
    public User asUser() {
        return new User(id, firstName, username, lastName);
    }



    @Override
    public String toString() {
        if (isUser())
            return "Chat {" + asUser().toString() + "}";
        //TODO completare il toString
        return null;
    }


}
