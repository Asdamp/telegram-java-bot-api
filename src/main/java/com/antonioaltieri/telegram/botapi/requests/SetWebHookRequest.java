package com.antonioaltieri.telegram.botapi.requests;

import java.util.HashMap;
import java.util.Map;

public class SetWebHookRequest implements com.antonioaltieri.telegram.botapi.requests.ApiRequest<Boolean> {
    private Map<String, String> args = new HashMap<>();

	public SetWebHookRequest(String webHookURL) {
        args.put("url", webHookURL);

    }
	@Override
	public String getMethodName() {
		return "setWebhook";
	}

	@Override
	public ResultTypes getResultType() {
		return ResultTypes.BOOLEAN;
	}

	@Override
	public Map<String, String> getArgs() {
		return args;
	}

	@Override
	public RequestStrategy getRequestStrategy() {
        return new PostStrategy();

	}

}
