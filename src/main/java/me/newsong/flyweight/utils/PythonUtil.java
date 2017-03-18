package me.newsong.flyweight.utils;

import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.googlecode.jsonrpc4j.JsonRpcHttpClient;

import me.newsong.flyweight.exceptions.PythonServerErrorException;

public class PythonUtil {
    private static PythonUtil instance;
    private ResourceBundle rb = ResourceBundle.getBundle("py");

    public static PythonUtil getInstance() {
        if (instance == null) {
            synchronized (PythonUtil.class) {
                if (instance == null) {
                    instance = new PythonUtil();
                }
            }
        }
        return instance;
    }

    private JsonRpcHttpClient client;

    private PythonUtil() {
        try {
            client = new JsonRpcHttpClient(new URL(rb.getString("url") + ":" + rb.getString("port")));
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new PythonServerErrorException();
        }
    }

    public void connect() {
        System.out.println("重连");
        try {
            client = new JsonRpcHttpClient(new URL(rb.getString("url") + ":" + rb.getString("port")));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void call(String methodName, List<?> params) {
        try {
            client.invoke(methodName, params.toArray());
        } catch (ConnectException e) {
            connect();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public <T> T call(String methodName, List<?> params, Class<T> returnType) {
        T res = null;
        try {
            res = client.invoke(methodName, params.toArray(), returnType);
        } catch (ConnectException e) {
            connect();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return res;
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> callForRawList(String methodName, List<?> params, Class<T> returnType) {
        try {
            return client.invoke(methodName, params.toArray(), List.class);
        } catch (ConnectException e) {
            connect();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> callForModelList(String methodName, List<?> params, Class<T> returnType) {
        List<Map<String, Object>> rawDatas = null;
        try {
            rawDatas = client.invoke(methodName, params.toArray(), List.class);
        } catch (ConnectException e) {
            connect();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println(rawDatas);
        List<T> results = new ArrayList<>();
        for (Map<String, Object> rawData : rawDatas) {
            results.add(ConvertUtil.toBean(rawData, returnType));
        }
        return results;
    }

}
