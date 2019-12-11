package com.example.lmy.customview.updaapp.okhttp;
import androidx.annotation.NonNull;
import com.example.lmy.customview.Utils.LogUtil;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.json.JSONObject;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @功能:
 * @Creat 2019/11/20 15:07
 * @User Lmy
 * @Compony zaituvideo
 */
public abstract class CallBack<T> implements Callback<T> {

    @Override
    public final void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
        if (response.isSuccessful() && response.body() != null) {
            try {
                String JsonData = new Gson().toJson(response.body());
                JSONObject object = new JSONObject(JsonData);
                String code = object.getString("code");
                String message = object.getString("message");
                switch (code) {
                    case "200":
                        onSuccess(response.body());
                        break;
                    default:
                        onError(message);
                        break;
                }
            } catch (Exception e) {
                LogUtil.e("错误========================" + "Json解析错误");
                onError("Json解析错误");
                e.printStackTrace();
            }
        }
    }

    @Override
    public final void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
        String error_message = "";
        if (t instanceof SocketTimeoutException) {
            error_message = "连接超时";
        } else if (t instanceof ConnectException) {
            error_message = "连接失败";
        } else if (t instanceof JsonSyntaxException) {
            error_message = "Json转换失败";
        } else if (t instanceof IllegalStateException) {
            error_message = "类型转换错误";
        } else {
            error_message = "未知错误";
        }
        LogUtil.e("错误========================" + error_message);
        onError(error_message);
    }

    public abstract void onSuccess(T data);

    public abstract void onError(String message);

}

