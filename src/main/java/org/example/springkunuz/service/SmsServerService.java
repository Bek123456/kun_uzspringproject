package org.example.springkunuz.service;


import okhttp3.*;
import org.example.springkunuz.entity.SmsHistoryEntity;
import org.example.springkunuz.enums.Status;
import org.example.springkunuz.repository.SmsHistoryRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class SmsServerService {
    @Value("${my.eskiz.uz.email}")
    private String email;
    @Value("${my.eskiz.uz.password}")
    private String password;
    @Value("${sms.fly.uz.url}")
    private String url;
    @Autowired
    private SmsHistoryRepository smsHistoryRepository;
    public void send(String phone, String text, String code) {
        // create sms history

        sendSmsHTTP(phone, text + code);
    }

    public String getTokenWithAuthorization() {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("email", email)
                .addFormDataPart("password", password)
                .build();
        Request request = new Request.Builder()
                .url( url +"/api/auth/login")
                .method("POST", body)
                .build();
        Response response;
        try {
            response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new IOException();
            } else {
                JSONObject object = new JSONObject(response.body().string());
                JSONObject data = object.getJSONObject("data");
                Object token = data.get("token");
                return token.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

    }
    public void sendSmsHTTP(String phone, String text) {
        String token = "Bearer " + getTokenWithAuthorization();

        OkHttpClient client = new OkHttpClient();

        if (phone.startsWith("+")) {
            phone = phone.substring(1);
        }

        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("mobile_phone", phone)
                .addFormDataPart("message", text)
                .addFormDataPart("from", "4546")
                .build();

        Request request = new Request.Builder()
                .url(url + "/api/message/sms/send")
                .method("POST", body)
                .header("Authorization", token)
                .build();
        try {
            Response response = client.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void createSmsHistory(String code, String phone) {
      SmsHistoryEntity smsHistoryEntity=new SmsHistoryEntity();
      smsHistoryEntity.setCreatedDate(LocalDateTime.now());
      smsHistoryEntity.setStatus(Status.NEW);
      smsHistoryEntity.setMessage("zzzzzzzzzzzz");
      smsHistoryEntity.setPhone(phone);
      smsHistoryRepository.save(smsHistoryEntity);
      System.out.println(smsHistoryEntity);
      System.out.println("Kreate sms history");
    }
}
