package com.remittancemiddleware.remittancemiddleware.dataclass.sandbox;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SandBoxRequest<T> {
    public SandBoxRequest(String accessToken, String apiName, T payload) {
        this.accessToken = accessToken;
        this.apiName = apiName;
        this.payload = payload;
    }

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("api_name")
    private String apiName;

    private T payload;
}
