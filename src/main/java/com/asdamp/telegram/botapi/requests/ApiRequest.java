package com.asdamp.telegram.botapi.requests;

import com.asdamp.telegram.botapi.types.Message;
import com.asdamp.telegram.botapi.types.Update;
import com.asdamp.telegram.botapi.types.User;
import com.asdamp.telegram.botapi.types.UserProfilePhotos;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

interface ApiRequest<T> {

    String getMethodName();

    ResultTypes getResultType();

    Map<String, String> getArgs();

    RequestStrategy getRequestStrategy();

    enum ResultTypes {
        USER(new TypeToken<ApiResult<User>>() {}.getType()),
        MESSAGE(new TypeToken<ApiResult<Message>>() {}.getType()),
        BOOLEAN(new TypeToken<ApiResult<Boolean>>() {}.getType()),
        USER_PROFILE_PHOTOS(new TypeToken<ApiResult<UserProfilePhotos>>() {}.getType()),
        LIST_OF_UPDATES(new TypeToken<ApiResult<List<Update>>>() {}.getType());

        private Type type;

        ResultTypes(Type type) {
            this.type = type;
        }

        public Type getType() {
            return type;
        }
    }

    interface RequestStrategy {

        String makeRequest(ApiRequest<?> request, TelegramApi api);

    }

    final class PostStrategy implements RequestStrategy {

        @Override
        public String makeRequest(ApiRequest<?> request, TelegramApi api) {
            return api.makePostRequest(request.getMethodName(), request.getArgs());
        }

        @Override
        public String toString() {
            return "POST";
        }
    }

    final class GetStrategy implements RequestStrategy {

        @Override
        public String makeRequest(ApiRequest<?> request, TelegramApi api) {
            return api.makeGetRequest(request.getMethodName());
        }

        @Override
        public String toString() {
            return "GET";
        }
    }

    final class MultipartStrategy implements RequestStrategy {

        private File file;
        private String fieldName;

        public MultipartStrategy(File file, String fieldName) {
            this.file = file;
            this.fieldName = fieldName;
        }

        @Override
        public String makeRequest(ApiRequest<?> request, TelegramApi api) {
            return api.makeMultipartRequest(request.getMethodName(), request.getArgs(), fieldName, file);
        }

        @Override
        public String toString() {
            return "MULTIPART";
        }
    }

}
