package com.asdamp.telegram.botapi;

public interface AbstractChatContextFactory {

    ChatContext createChatContext(int chatId, TelegramBot bot);

}
