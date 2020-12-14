package com.pikachu.book.tools.url;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;

import androidx.annotation.IntDef;
import androidx.annotation.RequiresApi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

import static java.nio.charset.StandardCharsets.UTF_8;

public class LoadUrl {


    private String url;
    private int type;
    private OnCall onCall;
    private int connectTime = 5000;
    private int readTime = 5000;
    private String postStr;
    private String charset = "UTF-8";


    @IntDef(value = {Type.GET, Type.POST})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Type {
        int GET = 1;
        int POST = 2;
    }

    public interface OnCall{
        void error(Exception e);
        void finish(String str);
    }

    private Activity activity;


    //get utf-8
    public LoadUrl(Activity activity,String url,OnCall onCall){
        this.activity = activity;
        this.url = url;
        type = Type.GET;
        this.onCall = onCall;
        start();
    }

    //get utf-8
    public LoadUrl(Activity activity,String url,String charset,OnCall onCall){
        this.activity = activity;
        this.url = url;
        this.charset = charset==null||charset.equals("")?"UTF-8":charset;
        type = Type.GET;
        this.onCall = onCall;
        start();
    }



    //post charset
    public LoadUrl(Activity activity,String url,String postStr,String charset,OnCall onCall){
        this.activity = activity;
        this.url = url;
        this.postStr = postStr;
        this.charset = charset;
        this.onCall = onCall;
        type = Type.POST;
        start();
    }



    @SuppressLint("NewApi")
    private void start(){
        new Thread(() -> {
            try {
                HttpURLConnection http = (HttpURLConnection) new URL(url).openConnection();
                http.setRequestMethod(type == Type.GET ?"GET":"POST");
                http.setConnectTimeout(connectTime);
                http.setReadTimeout(readTime);

                http.setRequestProperty("accept", " */*");
                http.setRequestProperty("accept-encoding", "gzip, deflate, br");
                http.setRequestProperty("Charset", "UTF-8");
                http.setRequestProperty("accept-language", "zh-CN,zh;q=0.9");
                http.setRequestProperty("cookie", " sm_uuid=1de5bdce386b1f28fe8b348f4bc4cb91%7C%7C%7C1605455203; " +
                        "sm_diu=1de5bdce386b1f28fe8b348f4bc4cb91%7C%7C13ecf3ec4ca25e70e7%7C1605455203;" +
                        " sm_diu=02f022f5f558ec3716d31f83071306ba%7C%7C1Fe0e0e040a8d6d298%7C1605880269;" +
                        " sm_uuid=1de5bdce386b1f28fe8b348f4bc4cb91");
                http.setRequestProperty("referer", " https://xiaoshuo.sm.cn/sc/1/channel/index?format=html&uc_param_str=dnntnwvepffrgibijbprsvdsdichei&from=smhome");
                http.setRequestProperty("user-agent", " Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.25 Safari/537.36 Core/1.70.3858.400 QQBrowser/10.7.4317.400");

                if (Type.POST == type){
                    http.setDoOutput(true);
                    OutputStream outputStream = http.getOutputStream();
                    outputStream.write(postStr.getBytes());
                    outputStream.flush();
                    outputStream.close();
                }

                int responseCode = http.getResponseCode();
                if (responseCode==200){

                    Pattern pattern = Pattern.compile("charset=\\S*");
                    Matcher matcher = pattern.matcher(http.getContentType());
                    if (matcher.find()) {
                        charset = matcher.group().replace("charset=", "");
                    }

                    InputStream inputStream = http.getInputStream();
                    BufferedReader bufferedReader;
                    if (http.getContentEncoding().equals("gzip"))
                        bufferedReader = new BufferedReader(new InputStreamReader(new GZIPInputStream(inputStream), charset));
                    else
                        bufferedReader = new BufferedReader(new InputStreamReader(inputStream, charset));


                    StringBuffer stringBuffer = new StringBuffer();
                    String str;
                    while ((str = bufferedReader.readLine()) != null){
                        stringBuffer.append(str);
                    }
                    activity.runOnUiThread(() -> onCall.finish(stringBuffer.toString()));
                }else {
                    activity.runOnUiThread(() -> onCall.error(new Exception("error->"+responseCode)));
                }


            } catch (IOException e) {
                e.printStackTrace();
                activity.runOnUiThread(() -> onCall.error(e));
            }


        }).start();



    }







}
