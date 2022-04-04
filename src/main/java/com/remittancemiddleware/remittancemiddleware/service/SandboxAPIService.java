package com.remittancemiddleware.remittancemiddleware.service;


import com.remittancemiddleware.remittancemiddleware.dataclass.sandbox.SandboxResponse;

import java.io.IOException;

public interface SandboxAPIService {

    SandboxResponse testConnection() throws IOException;

    SandboxResponse authenticate() throws IOException;


    <T> SandboxResponse sendTransactionToSandbox(T payload, String apiName) throws IOException;

}
