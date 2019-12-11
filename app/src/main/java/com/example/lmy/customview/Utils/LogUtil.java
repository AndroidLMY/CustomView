package com.example.lmy.customview.Utils;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * @功能: 全局控制log日志打印配置
 * @Creat 2019/07/15 16:18
 * @User Lmy
 * @By Android Studio
 */
public class LogUtil {

    public static boolean isPrintLog = true;
    private static int LOG_MAXLENGTH = 2000;
    public static String TGA = "com.eric.ontheway";

    public static void v(String msg) {
        v(TGA, msg);
    }

    public static void v(String tagName, String msg) {
        if (isPrintLog) {
            int strLength = msg.length();
            int start = 0;
            int end = LOG_MAXLENGTH;
            for (int i = 0; i < 100; i++) {
                if (strLength > end) {
                    Log.v(TGA, msg.substring(start, end));
                    start = end;
                    end = end + LOG_MAXLENGTH;
                } else {
                    Log.v(TGA, msg.substring(start, strLength));
                    break;
                }
            }
        }
    }

    public static void d(String msg) {
        d("TGA", msg);
    }

    public static void d(String tagName, String msg) {
        if (isPrintLog) {
            int strLength = msg.length();
            int start = 0;
            int end = LOG_MAXLENGTH;
            for (int i = 0; i < 100; i++) {
                if (strLength > end) {
                    Log.d(TGA, msg.substring(start, end));
                    start = end;
                    end = end + LOG_MAXLENGTH;
                } else {
                    Log.d(TGA, msg.substring(start, strLength));
                    break;
                }
            }
        }
    }

    public static void i(String msg) {
        i(TGA, msg);
    }

    public static void i(String tagName, String msg) {
        if (isPrintLog) {
            int strLength = msg.length();
            int start = 0;
            int end = LOG_MAXLENGTH;
            for (int i = 0; i < 100; i++) {
                if (strLength > end) {
                    Log.i(TGA, msg.substring(start, end));
                    start = end;
                    end = end + LOG_MAXLENGTH;
                } else {
                    Log.i(TGA, msg.substring(start, strLength));
                    break;
                }
            }
        }
    }

    public static void w(String msg) {
        w(TGA, msg);
    }

    public static void w(String tagName, String msg) {
        if (isPrintLog) {
            int strLength = msg.length();
            int start = 0;
            int end = LOG_MAXLENGTH;
            for (int i = 0; i < 100; i++) {
                if (strLength > end) {
                    Log.w(TGA, msg.substring(start, end));
                    start = end;
                    end = end + LOG_MAXLENGTH;
                } else {
                    Log.w(TGA, msg.substring(start, strLength));
                    break;
                }
            }
        }
    }

    public static void e(String msg) {
        e(TGA, msg);
    }

    public static void e(String tagName, String msg) {
        if (isPrintLog) {
            int strLength = msg.length();
            int start = 0;
            int end = LOG_MAXLENGTH;
            for (int i = 0; i < 100; i++) {
                if (strLength > end) {
                    Log.e(TGA, msg.substring(start, end));
                    start = end;
                    end = end + LOG_MAXLENGTH;
                } else {
                    Log.e(TGA, msg.substring(start, strLength));
                    break;
                }
            }
        }
    }

    public static final String LINE_SEPARATOR = System.getProperty("line.separator");

    public static void printLine(String tag, boolean isTop) {
        if (isTop) {
            LogUtil.d(tag, "╔═══════════════════════════════════════════════════════════════");
        } else {
            LogUtil.d(tag, "╚═══════════════════════════════════════════════════════════════");
        }
    }


    public static void printParam(Request request) {
        HttpUrl httpUrl = request.url();
        Gson gson = new Gson();
        Set<String> paramKeys = httpUrl.queryParameterNames();
        Map<String, String> getparams = new HashMap<>();
        Map<String, String> postparams = new HashMap<>();
        //map转字符串
        for (String key : paramKeys) {
            String value = httpUrl.queryParameter(key);
            // 这里就是GET接口传的参数key和value
            getparams.put(key, httpUrl.queryParameter(key));
        }

        if (getparams.size() != 0) {
            LogUtil.printJson(gson.toJson(getparams), "请求参数: " + request.url().uri().toString());

        }
        if (request.body() instanceof FormBody) {
            FormBody formBody = (FormBody) request.body();
            for (int i = 0; i < formBody.size(); i++) {
                String key = formBody.name(i);
                String value = formBody.value(i);
                postparams.put(key, value);
                // 这里是POST接口传的表单参数key和value
            }
        }
        if (postparams.size() != 0) {
            LogUtil.printJson(gson.toJson(postparams), "请求参数: " + request.url().uri().toString());
        }
    }

    public static void printJson(String msg, String headString) {
        if (isPrintLog) {
            String message;

            try {
                if (msg.startsWith("{")) {
                    JSONObject jsonObject = new JSONObject(msg);
                    message = jsonObject.toString(4);//最重要的方法，就一行，返回格式化的json字符串，其中的数字4是缩进字符数
                } else if (msg.startsWith("[")) {
                    JSONArray jsonArray = new JSONArray(msg);
                    message = jsonArray.toString(4);
                } else {
                    message = msg;
                }
            } catch (JSONException e) {
                message = msg;
            }

            printLine(TGA, true);
            message = headString + LINE_SEPARATOR + message;
            String[] lines = message.split(LINE_SEPARATOR);
            for (String line : lines) {
                Log.d(TGA, "║ " + line);
            }
            printLine(TGA, false);
        }

    }
}