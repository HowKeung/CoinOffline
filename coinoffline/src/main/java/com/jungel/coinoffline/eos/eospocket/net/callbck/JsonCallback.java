/*
 * Copyright 2016 jeasonlzy(廖子尧)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jungel.coinoffline.eos.eospocket.net.callbck;

import com.google.gson.JsonSyntaxException;
import com.jungel.coinoffline.R;
import com.jungel.coinoffline.eos.eospocket.utils.ToastUtils;
import com.lzy.okgo.callback.AbsCallback;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.SocketException;
import java.net.SocketTimeoutException;

import okhttp3.Response;

/**
 * Created by pocketEos on 2018/4/2.
 */
public abstract class JsonCallback<T> extends AbsCallback<T> {

    private Type type;
    private Class<T> clazz;


    public JsonCallback() {

    }

    public JsonCallback(Type type) {
        this.type = type;
    }

    public JsonCallback(Class<T> clazz) {
        this.clazz = clazz;
    }


    /**
     * 该方法是子线程处理，不能做ui相关的工作
     * 主要作用是解析网络返回的 response 对象,生产onSuccess回调中需要的数据对象
     */
    @Override
    public T convertResponse(Response response) throws Throwable {
        if (type == null) {
            if (clazz == null) {
                Type genType = getClass().getGenericSuperclass();
                type = ((ParameterizedType) genType).getActualTypeArguments()[0];
            } else {
                JsonConvert<T> convert = new JsonConvert<>(clazz);
                return convert.convertResponse(response);
            }
        }

        JsonConvert<T> convert = new JsonConvert<>(type);
        return convert.convertResponse(response);
    }

    @Override
    public void onSuccess(com.lzy.okgo.model.Response<T> response) {

    }

    @Override
    public void onError(com.lzy.okgo.model.Response<T> response) {
        super.onError(response);
        int code = response.code();
        if (code == 404) {
            ToastUtils.showLongToast(R.string.pocket_url_error);
        }
        if (response.getException() instanceof SocketTimeoutException) {
            ToastUtils.showLongToast(R.string.pocket_socket_time_out);
        } else if (response.getException() instanceof SocketException) {
            ToastUtils.showLongToast(R.string.pocket_socket_exception);
        } else if (response.getException() instanceof JsonSyntaxException) {
            ToastUtils.showLongToast(R.string.pocket_error_parse);
        }
    }
}
