package com.antonioaltieri.telegram.botapi.requests;

import com.antonioaltieri.telegram.botapi.types.Message;
import com.antonioaltieri.telegram.botapi.types.ParseModes;

import java.util.HashMap;
import java.util.Map;

public final class SendMessageRequest implements ApiRequest<Message> {

    private Map<String, String> args = new HashMap<>();

    public SendMessageRequest(long chatId, String text) {
        this(chatId, text, null);
    }

    public SendMessageRequest(String chatId, String text) {
        this(chatId, text, null);
    }

    public SendMessageRequest(long chatId, String text, OptionalArgs optionalArgs) {
        this(String.valueOf(chatId),text,optionalArgs);
    }

    public SendMessageRequest(String chatId, String text, OptionalArgs optionalArgs) {
        args.put("chat_id", chatId);
        if(text.length()> 4096)
            text=text.substring(0,4090);
        if(text.contains("<") && !text.contains(">"))
            text=text.replace("<", " " );
        args.put("text", text);

        if (optionalArgs != null) {
            args.putAll(optionalArgs.options());

        }

    }

    @Override
    public String getMethodName() {
        return "sendMessage";
    }

    @Override
    public ResultTypes getResultType() {
        return ResultTypes.MESSAGE;
    }

    @Override
    public Map<String, String> getArgs() {
        return args;
    }

    @Override
    public RequestStrategy getRequestStrategy() {
        return new PostStrategy();
    }

    @Override
    public String toString() {
        return "SendMessageRequest{" +
                "args=" + args +
                '}';
    }
}
