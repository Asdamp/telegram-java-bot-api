package com.antonioaltieri.telegram.botapi.requests;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.logging.Logger;

public class ApiRequestExecutor {

    private static final Logger log = Logger.getLogger(com.antonioaltieri.telegram.botapi.requests.ApiRequestExecutor.class.getName());
    private static final Gson gson = new Gson();
    private static com.antonioaltieri.telegram.botapi.requests.ApiRequestExecutor requestExecutor;
    // Non instantiable/subclassable
    private ApiRequestExecutor() {
    }
    public static com.antonioaltieri.telegram.botapi.requests.ApiRequestExecutor getInstance(){
        if(requestExecutor==null){
            requestExecutor=new com.antonioaltieri.telegram.botapi.requests.ApiRequestExecutor();
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
        try {
            ApiResult<T> result = deserialize(response, request.getResultType());
            if (!result.isOk())
                throw new ApiException(request.getMethodName(), result); //TODO controllare se l'eccezione viene ben gestita
            return result.getResult();
        }
        catch (JsonSyntaxException e){
            System.out.println("The response is not a "+request.getResultType()+". Is your Token valid?");
            System.out.println("Response: "+response);
            return null;
        }

    }



}
