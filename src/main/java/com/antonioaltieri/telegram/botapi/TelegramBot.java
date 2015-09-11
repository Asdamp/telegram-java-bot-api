package com.antonioaltieri.telegram.botapi;

import com.antonioaltieri.telegram.botapi.HandlerNotifier;
import com.antonioaltieri.telegram.botapi.requests.*;
import com.antonioaltieri.telegram.botapi.types.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * This class represents a TelegramBot.
 *
 * To use it, use any of the available subclasses or subclass it yourself.
 */
abstract public class TelegramBot {

    private static final Logger logger = Logger.getLogger(com.antonioaltieri.telegram.botapi.TelegramBot.class.getName());

    private TelegramApi api;
    private boolean running = false;
    private int lastUpdateId = 0;
    private ApiRequestExecutor requestExecutor;
    private com.antonioaltieri.telegram.botapi.HandlerNotifier handlerNotifier;
    private boolean sendAsync = true;




    /**
     * Constructs a TelegramBot using the provided {@code botToken}.
     *
     * @param botToken The token provided by @BotFather
     */
    public TelegramBot(String botToken) {
        api = new TelegramApi(botToken);
        handlerNotifier = new HandlerNotifier(this);
    }

    /**
     * Starts the bot.
     */
    public final void start() {
        logger.info("Starting");
        requestExecutor = ApiRequestExecutor.getInstance();
        running=true;
        onStart();
    }

    protected void onStart() {

    }

    /**
     * Stops the bot.
     */
    public final void stop() {
        running=false;
        onStop();
    }

    protected void onStop() {

    }

    /**
     * Forwards a message with ID {@code messageId} from {@code fromChatId} to {@code chatId}.
     *
     * @see <a href="https://core.telegram.org/bots/api#forwardmessage">https://core.telegram.org/bots/api#forwardmessage</a>
     *
     * @param chatId Unique identifier for the message recipient — User or GroupChat id
     * @param fromChatId Unique identifier for the chat where the original message was sent — User or GroupChat id
     * @param messageId Unique message identifier
     * @return The sent {@link Message}
     */
    public final Message forwardMessage(int chatId, int fromChatId, int messageId) {
        return requestExecutor.execute(api, new ForwardMessageRequest(chatId, fromChatId, messageId));
    }

    /**
     * A simple method for testing your bot's auth token. Requires no parameters. Returns basic information about the bot in form of a User object.
     *
     * @see <a href="https://core.telegram.org/bots/api#getme">https://core.telegram.org/bots/api#getme</a>
     *
     * @return this bot's information, in the form of a {@code User}
     */
    public final User getMe() {
        return requestExecutor.execute(api, new GetMeRequest());
    }

    /**
     * @see com.antonioaltieri.telegram.botapi.TelegramBot#getUserProfilePhotos(int, OptionalArgs)
     */
    public final UserProfilePhotos getUserProfilePhotos(int userId) {
        return requestExecutor.execute(api, new GetUserProfilePhotosRequest(userId));
    }

    /**
     * Returns a {@link UserProfilePhotos} for a user.
     *
     * For any optional arguments, refer to the Telegram documentation.
     *
     * @see <a href="https://core.telegram.org/bots/api#getuserprofilephotos">https://core.telegram.org/bots/api#getuserprofilephotos</a>
     * @see OptionalArgs
     *
     * @param userId Unique identifier of the target user
     * @param optionalArgs Any optional arguments for this method
     * @return The requested {@link UserProfilePhotos}
     */
    //TODO testare il caso in cui vengano inseirti optionalargs non pertinenti
    public final UserProfilePhotos getUserProfilePhotos(int userId, OptionalArgs optionalArgs) {
        return requestExecutor.execute(api, new GetUserProfilePhotosRequest(userId, optionalArgs));
    }

    /**
     * @see com.antonioaltieri.telegram.botapi.TelegramBot#sendAudio(int, File, OptionalArgs)
     */
    public final Message sendAudio(int chatId, File audioFile) {
        return requestExecutor.execute(api, new SendAudioRequest(chatId, audioFile));
    }

    /**
     * Use this method to send audio files, if you want Telegram clients to display the file as a music playable with the internal player.
     * Bots can currently send audio files of up to 50 MB in size, this limit may be changed in the future.
     *
     * For any optional arguments, refer to the Telegram documentation.
     *
     * @see <a href="https://core.telegram.org/bots/api#sendaudio">https://core.telegram.org/bots/api#sendaudio</a>
     *
     * @param chatId Unique identifier for the message recipient - {@link User} or {@link GroupChat} id
     * @param audioFile Audio {@link File} to send
     * @param optionalArgs Any optional arguments for this method
     * @return The sent {@link Message}
     */
    public final Message sendAudio(int chatId, File audioFile, OptionalArgs optionalArgs) {
        return requestExecutor.execute(api, new SendAudioRequest(chatId, audioFile, optionalArgs));
    }

    /**
     * @see com.antonioaltieri.telegram.botapi.TelegramBot#sendAudio(int, String, OptionalArgs)
     */
    public final Message sendAudio(int chatId, String audioFileId) {
        return requestExecutor.execute(api, new SendAudioRequest(chatId, audioFileId));
    }

    /**
     * This version takes a file id instead of a {@link File}, as a {@code String} argument.
     *
     * @see com.antonioaltieri.telegram.botapi.TelegramBot#sendAudio(int, File, OptionalArgs)
     */
    public final Message sendAudio(int chatId, String audioFileId, OptionalArgs optionalArgs) {
        return requestExecutor.execute(api, new SendAudioRequest(chatId, audioFileId, optionalArgs));
    }

    /**
     * Use this method when you need to tell the user that something is happening on the bot's side.
     * The status is set for 5 seconds or less (when a message arrives from your bot, Telegram clients clear its typing status).
     *
     * @see <a href="https://core.telegram.org/bots/api#sendchataction">https://core.telegram.org/bots/api#sendchataction</a>
     *
     * @param chatId Unique identifier for the message recipient - {@link User} or {@link GroupChat} id.
     * @param chatAction The target {@link ChatAction}
     * @return True if the request was successful
     */
    public final Boolean sendChatAction(int chatId, ChatAction chatAction) {
        return requestExecutor.execute(api, new SendChatActionRequest(chatId, chatAction));
    }

    /**
     * @see com.antonioaltieri.telegram.botapi.TelegramBot#sendAudio(int, File, OptionalArgs)
     */
    public final Message sendDocument(int chatId, File documentFile) {
        return requestExecutor.execute(api, new SendDocumentRequest(chatId, documentFile));
    }

    /**
     * Use this method to send general files. On success, the sent Message is returned.
     * Bots can currently send files of any type of up to 50 MB in size, this limit may be changed in the future.
     *
     * For any optional arguments, refer to the Telegram documentation.
     *
     * @see <a href="https://core.telegram.org/bots/api#senddocument">https://core.telegram.org/bots/api#senddocument</a>
     *
     * @param chatId Unique identifier for the message recipient - {@link User} or {@link GroupChat} id
     * @param documentFile File to send
     * @param optionalArgs Any optional arguments
     * @return The sent {@link Message}
     */
    public final Message sendDocument(int chatId, File documentFile, OptionalArgs optionalArgs) {
        return requestExecutor.execute(api, new SendDocumentRequest(chatId, documentFile, optionalArgs));
    }

    /**
     * @see com.antonioaltieri.telegram.botapi.TelegramBot#sendDocument(int, String, OptionalArgs)
     */
    public final Message sendDocument(int chatId, String documentFileId) {
        return requestExecutor.execute(api, new SendDocumentRequest(chatId, documentFileId));
    }

    /**
     * This version takes a file id instead of a {@link File}.
     *
     * @see com.antonioaltieri.telegram.botapi.TelegramBot#sendDocument(int, File, OptionalArgs)
     */
    public final Message sendDocument(int chatId, String documentFileId, OptionalArgs optionalArgs) {
        return requestExecutor.execute(api, new SendDocumentRequest(chatId, documentFileId, optionalArgs));
    }

    /**
     * @see com.antonioaltieri.telegram.botapi.TelegramBot#sendLocation(int, float, float, OptionalArgs)
     */
    public final Message sendLocation(int chatId, float latitude, float longitude) {
        return requestExecutor.execute(api, new SendLocationRequest(chatId, latitude, longitude));
    }

    /**
     * Use this method to send point on the map.
     *
     * For any optional arguments, refer to the Telegram documentation.
     *
     * @see <a href="https://core.telegram.org/bots/api#sendlocation">https://core.telegram.org/bots/api#sendlocation</a>
     *
     * @param chatId Unique identifier for the message recipient - {@link User} or {@link GroupChat} id
     * @param latitude Latitude of location
     * @param longitude Longitude of location
     * @param optionalArgs Any optional arguments
     * @return the sent {@link Message}
     */
    public final Message sendLocation(int chatId, float latitude, float longitude, OptionalArgs optionalArgs) {
        return requestExecutor.execute(api, new SendLocationRequest(chatId, latitude, longitude, optionalArgs));
    }

    /**
     * @see com.antonioaltieri.telegram.botapi.TelegramBot#sendMessage(int, String, OptionalArgs)
     */
    public final Message sendMessage(int chatId, String text) {
        return requestExecutor.execute(api, new SendMessageRequest(chatId, text));
    }

    /**
     * Use this method to send text messages.
     *
     * For any optional arguments, refer to the Telegram documentation.
     *
     * @see <a href="https://core.telegram.org/bots/api#sendmessage">https://core.telegram.org/bots/api#sendmessage</a>
     *
     * @param chatId Unique identifier for the message recipient - {@link User} or {@link GroupChat} id
     * @param text Text of the message to be sent
     * @param optionalArgs Any optional arguments
     * @return the sent {@link Message}
     */
    public final Message sendMessage(int chatId, String text, OptionalArgs optionalArgs) {
        return requestExecutor.execute(api, new SendMessageRequest(chatId, text, optionalArgs));
    }

    /**
     * @see com.antonioaltieri.telegram.botapi.TelegramBot#sendPhoto(int, File, OptionalArgs)
     */
    public final Message sendPhoto(int chatId, File photoFile) {
        return requestExecutor.execute(api, new SendPhotoRequest(chatId, photoFile));
    }

    /**
     * Use this method to send photos.
     *
     * @see <a href="https://core.telegram.org/bots/api#sendphoto">https://core.telegram.org/bots/api#sendphoto</a>
     *
     * @param chatId Unique identifier for the message recipient - {@link User} or {@link GroupChat} id
     * @param photoFile Photo to send
     * @param optionalArgs Any optional arguments
     * @return The sent {@link Message}
     */
    public final Message sendPhoto(int chatId, File photoFile, OptionalArgs optionalArgs) {
        return requestExecutor.execute(api, new SendPhotoRequest(chatId, photoFile, optionalArgs));
    }

    /**
     * @see com.antonioaltieri.telegram.botapi.TelegramBot#sendPhoto(int, String, OptionalArgs)
     */
    public final Message sendPhoto(int chatId, String photoFileId) {
        return requestExecutor.execute(api, new SendPhotoRequest(chatId, photoFileId));
    }

    /**
     * This version takes a file id instead of a {@link File}
     *
     * @see com.antonioaltieri.telegram.botapi.TelegramBot#sendPhoto(int, File, OptionalArgs)
     */
    public final Message sendPhoto(int chatId, String photoFileId, OptionalArgs optionalArgs) {
        return requestExecutor.execute(api, new SendPhotoRequest(chatId, photoFileId, optionalArgs));
    }

    /**
     * @see com.antonioaltieri.telegram.botapi.TelegramBot#sendSticker(int, File, OptionalArgs)
     */
    public final Message sendSticker(int chatId, File stickerFile) {
        return requestExecutor.execute(api, new SendStickerRequest(chatId, stickerFile));
    }

    /**
     * Use this method to send .webp stickers.
     *
     * For any optional arguments, refer to the Telegram documentation.
     *
     * @see <a href="https://core.telegram.org/bots/api#sendsticker">https://core.telegram.org/bots/api#sendsticker</a>
     *
     * @param chatId Unique identifier for the message recipient - {@link User} or {@link GroupChat} id
     * @param stickerFile Sticker to send.
     * @param optionalArgs Any optional arguments
     * @return The sent {@link Message}
     */
    public final Message sendSticker(int chatId, File stickerFile, OptionalArgs optionalArgs) {
        return requestExecutor.execute(api, new SendStickerRequest(chatId, stickerFile, optionalArgs));
    }

    /**
     * @see com.antonioaltieri.telegram.botapi.TelegramBot#sendSticker(int, String, OptionalArgs)
     */
    public final Message sendSticker(int chatId, String stickerFileId) {
        return requestExecutor.execute(api, new SendStickerRequest(chatId, stickerFileId));
    }

    /**
     * This version takes a file id instead of a {@link File}.
     *
     * @see com.antonioaltieri.telegram.botapi.TelegramBot#sendSticker(int, File, OptionalArgs)
     */
    public final Message sendSticker(int chatId, String stickerFileId, OptionalArgs optionalArgs) {
        return requestExecutor.execute(api, new SendStickerRequest(chatId, stickerFileId, optionalArgs));
    }

    /**
     * @see com.antonioaltieri.telegram.botapi.TelegramBot#sendVideo(int, File, OptionalArgs)
     */
    public final Message sendVideo(int chatId, File videoFile) {
        return requestExecutor.execute(api, new SendVideoRequest(chatId, videoFile));
    }

    /**
     * Use this method to send video files,
     * Telegram clients support mp4 videos (other formats may be sent as Document ({@link com.antonioaltieri.telegram.botapi.TelegramBot#sendDocument(int, File, OptionalArgs)})).
     * Bots can currently send video files of up to 50 MB in size, this limit may be changed in the future.
     *
     * For any optional arguments, refer to the Telegram documentation.
     *
     * @see <a href="https://core.telegram.org/bots/api#sendvideo">https://core.telegram.org/bots/api#sendvideo</a>
     *
     * @param chatId Unique identifier for the message recipient - {@link User} or {@link GroupChat} id
     * @param videoFile Video to send
     * @param optionalArgs Any optional arguments.
     * @return The sent {@link Message}
     */
    //TODO Controllare cosa succede se si invia un file di tipo diverso da quello richiesto. es. foto con sendvideo, oppure un video non mp4
    public final Message sendVideo(int chatId, File videoFile, OptionalArgs optionalArgs) {
        return requestExecutor.execute(api, new SendVideoRequest(chatId, videoFile, optionalArgs));
    }

    /**
     * @see com.antonioaltieri.telegram.botapi.TelegramBot#sendVideo(int, File, OptionalArgs)
     */
    public final Message sendVideo(int chatId, String videoFileId) {
        return requestExecutor.execute(api, new SendVideoRequest(chatId, videoFileId));
    }

    /**
     * This version uses a file id rather than a {@link File}
     *
     * @see com.antonioaltieri.telegram.botapi.TelegramBot#sendVideo(int, File, OptionalArgs)
     */
    public final Message sendVideo(int chatId, String videoFileId, OptionalArgs optionalArgs) {
        return requestExecutor.execute(api, new SendVideoRequest(chatId, videoFileId, optionalArgs));
    }

    /**
     * Convenience method for {@code sendMessage(message.getChat().getId(), text, new OptionalArgs().replyToMessageId(message.getMessageId()))}
     */
    public final Message replyTo(Message message, String text) {
        OptionalArgs optionalArgs = new OptionalArgs().replyToMessageId(message.getMessageId());
        return sendMessage(message.getChat().getId(), text, optionalArgs);
    }

    /**
     * This method is called when a new message has arrived.
     * It can safely be overridden by subclasses.
     *
     * @param message The newly arrived {@link Message}
     */
    protected void onMessage(Message message) {
        // Can be overridden by subclasses.
    }

    /**
     * This method is called by this class to process all new {@link Message}s .
     * @param message The newly arrived {@link Message}
     */
    protected void notifyNewMessage(Message message) {
        onMessage(message);
        handlerNotifier.notifyHandlers(message);
    }

    /**
     * This method is called by another class to process all new {@link Update}s asynchronously.
     *
     * @param update The newly arrived {@link Update}s
     */
    public void notifyNewUpdate(Update update) {
    	Message message = processUpdate(update);
        if(message!=null) notifyNewMessage(message);
    }
    private Message processUpdate(Update update) {
        Message msg=null;
        //if (update.getUpdateId() > lastUpdateId){
            lastUpdateId = update.getUpdateId();
            msg=update.getMessage();
        //}

        return msg;
    }


}
