package com.antonioaltieri.telegram.botapi.requests;

import com.antonioaltieri.telegram.botapi.types.Message;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class SendVoiceRequest implements ApiRequest<Message> {

    private Map<String, String> args = new HashMap<>();
    private RequestStrategy requestStrategy;

    public SendVoiceRequest(int chatId, File voiceFile) {
        this(chatId, voiceFile, null);
    }

    public SendVoiceRequest(int chatId, File voiceFile, com.antonioaltieri.telegram.botapi.requests.OptionalArgs optionalArgs) {
        args.put("chat_id", String.valueOf(chatId));

        if (optionalArgs != null)
            args.putAll(optionalArgs.options());

        requestStrategy = new MultipartStrategy(voiceFile, "voice");
    }

    public SendVoiceRequest(int chatId, String voiceString) {
        this(chatId, voiceString, null);
    }

    public SendVoiceRequest(int chatId, String voiceString, OptionalArgs optionalArgs) {
        args.put("chat_id", String.valueOf(chatId));
        args.put("voice", voiceString);

        if (optionalArgs != null)
            args.putAll(optionalArgs.options());

        requestStrategy = new PostStrategy();
    }

    @Override
    public String getMethodName() {
        return "sendVoice";
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
        return requestStrategy;
    }

    @Override
    public String toString() {
        return "SendVoiceRequest{" +
                "args=" + args +
                ", requestStrategy=" + requestStrategy +
                '}';
    }
}
