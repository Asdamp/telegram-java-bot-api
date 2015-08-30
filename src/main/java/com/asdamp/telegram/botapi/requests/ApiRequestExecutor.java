package com.asdamp.telegram.botapi.requests;

import com.google.gson.Gson;

import java.util.logging.Logger;

public class ApiRequestExecutor {

    private static final Logger log = Logger.getLogger(ApiRequestExecutor.class.getName());
    private static final Gson gson = new Gson();
    private static ApiRequestExecutor requestExecutor;
    // Non instantiable/subclassable
    private ApiRequestExecutor() {
    }
    public ApiRequestExecutor getInstance(){
        if(requestExecutor==null){
            requestExecutor=new ApiRequestExecutor();
        }
        return requestExecutor;
    }

    protected <T> ApiResult<T> deserialize(String json, ApiRequest.ResultTypes resultType) {
    	System.out.println(json);
        return gson.fromJson(json, resultType.getType());
    }


    public <T> T execute(TelegramApi api, ApiRequest<T> request) {
        log.info(request.toString());

        String response = request.getRequestStrategy().makeRequest(request, api);

        ApiResult<T> result = deserialize(response, request.getResultType());

        if (!result.isOk())
            throw new ApiException(request.getMethodName(), result); //TODO controllare se l'eccezione viene ben gestita

        return result.getResult();
    }



}
