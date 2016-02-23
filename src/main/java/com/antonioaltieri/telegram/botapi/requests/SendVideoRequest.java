package com.antonioaltieri.telegram.botapi.requests;

import com.antonioaltieri.telegram.botapi.types.Message;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class SendVideoRequest implements ApiRequest<Message> {

    private Map<String, String> args = new HashMap<>();
    private RequestStrategy requestStrategy;

    public SendVideoRequest(long chatId, File video) {
        this(chatId, video, null);
    }

    public SendVideoRequest(long chatId, File video, com.antonioaltieri.telegram.botapi.requests.OptionalArgs optionalArgs) {
        args.put("chat_id", String.valueOf(chatId));

        if (optionalArgs != null)
            args.putAll(optionalArgs.options());

        requestStrategy = new MultipartStrategy(video, "video");
    }

    public SendVideoRequest(long chatId, String video) {
        this(chatId, video, null);
    }

    public SendVideoRequest(long chatId, String video, OptionalArgs optionalArgs) {
        args.put("chat_id", String.valueOf(chatId));
        args.put("video", video);

        if (optionalArgs != null)
            args.putAll(optionalArgs.options());

        requestStrategy = new PostStrategy();
    }

    @Override
    public String getMethodName() {
        return "sendVideo";
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
        return "SendVideoRequest{" +
                "args=" + args +
                ", requestStrategy=" + requestStrategy +
                '}';
    }
}
