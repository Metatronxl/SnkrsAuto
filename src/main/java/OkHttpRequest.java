import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import okhttp3.*;
import okhttp3.internal.http2.Http2Connection;
import okio.*;

import java.io.*;
import java.net.Socket;
import java.net.URLDecoder;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


public class OkHttpRequest {

    public static String client_id = "qG9fJbnMcBPAMGibPRGI72Zr89l8CD4R";

    public static final String GETCOOKIES_URL = "https://s3.nikecdn.com/public/4969a26fa170db888b07d6359213d";
    public static final String GETMID_URL = "https://dpm.demdex.net/id?d_ver=2&d_orgid=F0935E09512D2C270A490D4D@AdobeOrg";
    public static String LOGIN_URL_WITHOUT_VISITOR = "https://s3.nikecdn.com/login?appVersion=556&experienceVersion=454&uxid=com.nike.commerce.snkrs.droid&locale=zh_CN&backendEnvironment=identity&browser=Google%20Inc.&os=undefined&mobile=true&native=true&visit=1&visitor=";

    public static String referer = "https://s3.nikecdn.com/unite/mobile.html?mid=" + getMid() + "&androidSDKVersion=2.8.1&uxid=com.nike.commerce.snkrs.droid&locale=zh_CN&backendEnvironment=identity&view=login&clientId=" + client_id;
    public static String sensor_data = "7a74G7m23Vrp0o5c9065641.4-1,2,-94,-100,Mozilla/5.0 (Linux; Android 6.0.1; MI NOTE LTE Build/MMB29M; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/57.0.2987.132 Mobile Safari/537.36,uaend,11123,20030107,zh-CN,Gecko,0,0,0,0,382085,4203837,393,699,393,699,393,679,393,,cpen:0,i1:0,dm:0,cwen:0,non:1,opc:0,fc:0,sc:0,wrc:1,isc:0,vib:1,bat:1,x11:0,x12:1,11702,0.487645644243,776447101915.5,loc:-1,2,-94,-101,do_en,dm_en,t_en-1,2,-94,-105,0,2,0,0,2327,1878,0;1,2,0,0,2751,883,0;-1,2,-94,-102,0,2,0,0,2327,1878,0;1,2,0,0,2751,883,0;-1,2,-94,-108,-1,2,-94,-110,-1,2,-94,-117,-1,2,-94,-111,0,269,269.99,42.37,0.04;1,304,269.95,46.72,0.10;-1,2,-94,-109,0,259,-0.17,0.17,0.15,-0.47,5.60,8.31,0.01,-0.02,-0.00;1,271,0.01,0.17,0.03,-0.28,5.60,8.20,0.01,-0.00,-0.01;-1,2,-94,-114,-1,2,-94,-103,-1,2,-94,-112,https://s3.nikecdn.com/unite/mobile.html?mid=92108229044567075990649557873342893201&androidSDKVersion=2.8.1&uxid=com.nike.commerce.snkrs.droid&locale=zh_CN&backendEnvironment=identity&view=login&clientId=qG9fJbnMcBPAMGibPRGI72Zr89l8CD4R#%7B%22event%22:%22gigyaKey%22,%22apiKey%22:%22gigyaKeyNotSupported%22,%22ts%22:1552894203490%7D-1,2,-94,-115,1,0,0,574,531,0,1105,307,0,1552894203831,49,16612,0,0,2768,0,0,318,1103,0,B8F764D208C6656784A749F00C5B19A2~-1~YAAQfNfSF268yUxpAQAAVBK1jwGMd7uFQ208Y8Yidszbji0nx0+bEz0B/d4jDuVKiLVgp00xnOo3a6CPG3AoESSISBYYCUOa1A5iZZfj00oI3Hi3Mh3du9Yu/VnnM+ks8u3pmHTMAproKhPA59D5AuOPEco1EvS3af8XctH6WoXrcsuCW2qc59rc50+V1+e5eQtK1lp2B+ygOsHoXltnCF7kbhgqHB98UtpmUJ1nYNO4ZnoJROCrwBh+LqXz71Be7ICds5+Wuz62Md56CSAdutKmS9mp8yv5oEmRZCA=~-1~-1~-1,27434,959,585492926,30212541-1,2,-94,-106,6,2-1,2,-94,-119,-1-1,2,-94,-122,0,0,0,0,1,0,0-1,2,-94,-123,-1,2,-94,-70,342866545;dis;;true;true;true;-480;true;32;32;true;false;-1-1,2,-94,-80,4763-1,2,-94,-116,37834421-1,2,-94,-118,110149-1,2,-94,-121,;20;60;0";

    /**
     * @desc:POST 登陆请求 Header+params
     */
    public static void sendHeadersAndParams(ArrayList<String> cookies, String login_url) {


        //构造表单
        RequestBody formBody = new FormBody.Builder().add("username", "+8613717798263")
                .add("password", "2244363365lzhcE").add("client_id", client_id)
                .add("ux_id", "com.nike.commerce.snkrs.droid").add("grant_type", "password").build();


        OkHttpClient client = SSLSocketFactoryImp.getClient();

        Request request = new Request.Builder()
                .url(login_url)
                .addHeader("content-length", "169")
                .addHeader("origin", "https://s3.nikecdn.com")
                .addHeader("user-agent", "Mozilla/5.0 (Linux; Android 6.0.1; MI NOTE LTE Build/MMB29M; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/57.0.2987.132 Mobile Safari/537.36")
                .addHeader("content-type", "application/json")
                .addHeader("accept", "*/*")
                .addHeader("referer", referer)
                .addHeader("accept-encoding", "gzip, deflate")
                .addHeader("accept-language", "zh-CN,en-US;q=0.8")
                .addHeader("cookie", cookies.get(0))
                .addHeader("cookie", cookies.get(1))
                .addHeader("x-requested-with", "com.nike.snkrs")
                .post(formBody)
                .build();

        client.newCall(request)
                .enqueue(new Callback() {
                    public void onFailure(Call call, IOException e) {

                    }

                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()) {
                            Headers responseHeaders = response.headers();
                            for (int i = 0; i < responseHeaders.size(); i++) {
                                System.out.println(responseHeaders.name(i) + ": "
                                        + responseHeaders.value(i));
                            }
                            String json = response.body().string();
                            System.out.println(URLDecoder.decode(json, "utf-8"));
                        } else {
                            System.out.println(response.networkResponse());
                            String rslt = response.body().string();
                            System.out.println(rslt); // ECHO
                        }
                        response.body().close();
                    }
                });
    }


    /**
     * @dec 首次访问获取bm_sz和_abck（1）两个cookie
     */
    public static ArrayList<String> getCookiesStep1() {
        System.out.println("getCookiesStep1: ");
        ArrayList<String> cookies = new ArrayList<String>();
        OkHttpClient client = SSLSocketFactoryImp.getClient();
        Request request = new Request.Builder()
                .url(GETCOOKIES_URL)
                .addHeader("user-agent", "Mozilla/5.0 (Linux; Android 6.0.1; MI NOTE LTE Build/MMB29M; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/57.0.2987.132 Mobile Safari/537.36")
                .addHeader("accept", "*/*")
                .addHeader("referer", referer)
                .addHeader("accept-encoding", "gzip, deflate")
                .addHeader("accept-language", "zh-CN,en-US;q=0.8")
                .addHeader("x-requested-with", "com.nike.snkrs")
                .build();

//        System.out.println("referer:   " + request.header("referer"));
        try {
            Response response = client.newCall(request).execute();

            if (!response.isSuccessful())
                throw new IOException("Unexpected code " + response);
            Headers responseHeaders = response.headers();
            for (int i = 0; i < responseHeaders.size(); i++) {

                if (responseHeaders.name(i).equals("Set-Cookie")) {
                    cookies.add(responseHeaders.value(i).split(";")[0]);
                }
            }
            response.body().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cookies;
    }


    /**
     * @dec 第二次请求获取_abck（2）
     */
    public static String getCookiesStep2(ArrayList<String> cookies) {

        System.out.println("getCookiesStep2: ");
        RequestBody formBody = new FormBody.Builder().add("sensor_data", sensor_data).build();
        OkHttpClient client = SSLSocketFactoryImp.getClient();
        Request request = new Request.Builder()
                .url(GETCOOKIES_URL)
                .addHeader("content-length", String.valueOf(sensor_data.length() + 18))        //观察这个length的值，都是sensor_data.length() + 18
                .addHeader("origin", "https://s3.nikecdn.com")
                .addHeader("user-agent", "Mozilla/5.0 (Linux; Android 6.0.1; MI NOTE LTE Build/MMB29M; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/57.0.2987.132 Mobile Safari/537.36")
                .addHeader("content-type", "text/plain;charset=UTF-8")
                .addHeader("accept", "*/*")
                .addHeader("referer", referer)
                .addHeader("accept-encoding", "gzip, deflate")
                .addHeader("accept-language", "zh-CN,en-US;q=0.8")
                .addHeader("cookie", cookies.get(0))
                .addHeader("cookie", cookies.get(1))
                .addHeader("x-requested-with", "com.nike.snkrs")
                .post(formBody)
                .build();

        try {
            Response response = client.newCall(request).execute();

            if (!response.isSuccessful())
                throw new IOException("Unexpected code " + response);
            System.out.println(response.networkResponse());
            Headers responseHeaders = response.headers();
            for (int i = 0; i < responseHeaders.size(); i++) {
                System.out.println(responseHeaders.name(i) + ":" + responseHeaders.value(i));
                if (responseHeaders.name(i).equals("set-cookie")) {
                    System.out.println("-------------------------------------");
                    return responseHeaders.value(i).split(";")[0];
                }
            }
            response.body().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("-------------------------------------");
        return null;
    }


    /**
     * @dec 请求mid字段
     */
    public static String getMid() {
        System.out.println("get Mid:");
        String mid = null;
        OkHttpClient client = SSLSocketFactoryImp.getClient();
        Request request = new Request.Builder()
                .url(GETMID_URL)
                .addHeader("Accept-Language", "zh-CN")
                .addHeader("User-Agent", "Mozilla/5.0 (Linux; U; Android 6.0.1; zh-CN; MI NOTE LTE Build/MMB29M)")
                .addHeader("Host", "dpm.demdex.net")
                .addHeader("Connection", "Keep-Alive")
                .addHeader("Accept-Encoding", "gzip")
                .build();
        try {
            Response response = client.newCall(request).execute();
            System.out.println(response.networkResponse());
            if (!response.isSuccessful())
                throw new IOException("Unexpected code " + response);
            JSONObject responseBody = JSONObject.parseObject(response.body().string());
            Headers headers = response.headers();
            for (int i = 0; i < headers.size(); i++) {
                System.out.println(headers.name(i) + ":" + headers.value(i));
            }
            mid = responseBody.getString("d_mid");
            response.body().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Mid: " + mid);
        System.out.println("-------------------------------------");
        return mid;
    }

//    private void startHttp2(int pingIntervalMillis) throws IOException {
//        Socket socket = new Socket();
//        Route route = new Route();
//        Source source = Okio.buffer(Okio.source(socket));
//        Sink sink = Okio.buffer(Okio.sink(socket));
//
//        socket.setSoTimeout(0); // HTTP/2 connection timeouts are set per-stream.
//        //创建Http2Connection对象
//        Http2Connection http2Connection = new Http2Connection.Builder(true)
//                .socket(socket, route.address().url().host(), source, sink)
//                .listener(this)
//                .pingIntervalMillis(pingIntervalMillis)
//                .build();
//        //开启HTTP 2.0
//        http2Connection.start();
//    }

    public static void main(String[] args) {

        //visitor字段即为一个UUID
        String uuid = UUID.randomUUID().toString().toLowerCase();

        String login_url = LOGIN_URL_WITHOUT_VISITOR + uuid;

        ArrayList<String> cookies = getCookiesStep1();

        System.out.println(cookies.get(0));
        System.out.println(cookies.get(1));
        System.out.println("-------------------------------------");

        cookies.set(1, getCookiesStep2(cookies));

//        System.out.println(cookies.get(1));

//        OkHttpRequest.sendHeadersAndParams(cookies, login_url);
    }
}