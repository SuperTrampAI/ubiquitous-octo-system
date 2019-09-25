package com.supertrampai.myutils.util.sms;

import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Function: TODO ADD FUNCTION.
 * Reason:   TODO ADD REASON.
 * Date:     2019年4月2日  6:08:30 PM
 * @author   lxh800109@gmail.com
 * @version   
 * @since    JDK 1.8 
 * @see       
 */
public class SmsUtil {

    private static final String charset = "UTF-8";
    private static final String account = "N2501691";
    private static final String password = "tMil07k9H";
    private static final String server_url = "https://smssh1.253.com/msg/send/json";

    public static boolean send(String mobile, String content) {
        SmsSendRequest req = new SmsSendRequest(account, password, "【lxh】"+content, mobile, "true");
        String requestJson = JSON.toJSONString(req);
        String response = SmsUtil.sendSmsByPost(server_url, requestJson);
        SmsSendResponse res = JSON.parseObject(response, SmsSendResponse.class);
        return res.getCode().equals("0");
    }

    public static void main(String[] args){
        new SmsUtil().send("18818505556","测试");
    }

    private static String sendSmsByPost(String path, String postContent) {
        URL url = null;
        try {
            url = new URL(path);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");// 提交模式
            httpURLConnection.setConnectTimeout(10000);//连接超时 单位毫秒
            httpURLConnection.setReadTimeout(10000);//读取超时 单位毫秒
            // 发送POST请求必须设置如下两行
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestProperty("Charset", charset);
            httpURLConnection.setRequestProperty("Content-Type", "application/json");

            httpURLConnection.connect();
            OutputStream os=httpURLConnection.getOutputStream();
            os.write(postContent.getBytes(charset));
            os.flush();

            StringBuilder sb = new StringBuilder();
            int httpRspCode = httpURLConnection.getResponseCode();
            if (httpRspCode == HttpURLConnection.HTTP_OK) {
                // 开始获取数据
                BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), charset));
                String line = null;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                br.close();
                return sb.toString();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
