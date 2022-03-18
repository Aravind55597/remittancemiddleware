package com.remittancemiddleware.remittancemiddleware.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.remittancemiddleware.remittancemiddleware.dataclass.SandboxResponse;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SandboxAPIServiceImpl implements SandboxAPIService {

    private String sandboxPassword;

    private String sandboxUsername;

    private String sandboxBaseUrl;

    private OkHttpClient okHttpClient;

    private ObjectMapper objectMapper;

    @Autowired
    public SandboxAPIServiceImpl(OkHttpClient okHttpClient , ObjectMapper objectMapper,
                                 @Value("${sandbox.password}") String sandboxPassword,
                                 @Value( "${sandbox.username}" ) String sandboxUsername,
                                 @Value("${sandbox.base.url}") String sandboxBaseUrl){
        this.okHttpClient=okHttpClient;
        this.objectMapper=objectMapper;
        this.sandboxPassword= sandboxPassword;
        this.sandboxUsername=sandboxUsername;
        this.sandboxBaseUrl=sandboxBaseUrl;
    }

    @Override
    public SandboxResponse testConnection() throws IOException {
        RequestBody formBody = new FormBody.Builder()
                .build();

        Request request = new Request.Builder()
                .url(sandboxBaseUrl + "/smu_sandbox")
                .post(formBody)
                .build();

        Call call = okHttpClient.newCall(request);
        ResponseBody responseBody = call.execute().body();

        SandboxResponse sandboxResponse = objectMapper.readValue(responseBody.string(), SandboxResponse.class);

        return sandboxResponse;
    }

    @Override
    public SandboxResponse authenticate() throws IOException {
        RequestBody requestBody = new FormBody.Builder()
                .add("username", sandboxPassword)
                .add("password", sandboxUsername)
                .build();

        Request request = new Request.Builder()
                .url(sandboxBaseUrl + "/smu_authenticate")
                .post(requestBody)
                .build();

        Call call = okHttpClient.newCall(request);
        ResponseBody responseBody = call.execute().body();

        SandboxResponse sandboxResponse = objectMapper.readValue(responseBody.string(), SandboxResponse.class);

        return sandboxResponse;
    }

//    @Override
//    public <T> SandboxResponse sendTransactionsToSandbox(T remittance) throws IOException {
//        SandboxResponse sandboxAccessTokenResponse = this.authenticate();
//        String accessToken = sandboxAccessTokenResponse.getAccessToken();
//
//        RequestBody formBody = new FormBody.Builder()
//                .add("access_token",accessToken)
//
//                .build();
//
//        Request request = new Request.Builder()
//                .url(sandboxBaseUrl + "/smu_send_transaction")
//                .post(formBody)
//                .build();
//
//        Call call = okHttpClient.newCall(request);
//        ResponseBody responseBody = call.execute().body();
//
//        SandboxResponse sandboxResponse = objectMapper.readValue(responseBody.string(), SandboxResponse.class);
//
//        return sandboxResponse;
//    }





}
