package com.remittancemiddleware.remittancemiddleware.dataclass;


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

}
