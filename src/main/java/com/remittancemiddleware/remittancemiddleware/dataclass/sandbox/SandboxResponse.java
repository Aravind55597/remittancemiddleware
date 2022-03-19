package com.remittancemiddleware.remittancemiddleware.dataclass.sandbox;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SandboxResponse {

    private int code ;

    private String message;

    private String error;

    @JsonProperty("access_token")
    private String accessToken;

    @Override
    public String toString() {
        return "SandboxResponse{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", error='" + error + '\'' +
                ", accessToken='" + accessToken + '\'' +
                '}';
    }
}
