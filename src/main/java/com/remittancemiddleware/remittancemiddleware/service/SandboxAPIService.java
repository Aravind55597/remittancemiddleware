package com.remittancemiddleware.remittancemiddleware.service;


import com.remittancemiddleware.remittancemiddleware.dataclass.remittance.financenow.FinanceNowData;
import com.remittancemiddleware.remittancemiddleware.dataclass.sandbox.SandboxResponse;

import java.io.IOException;

public interface SandboxAPIService {

    public SandboxResponse testConnection() throws IOException;

    public SandboxResponse authenticate() throws IOException;


    public <T> SandboxResponse sendTransactionToSandbox(T payload) throws IOException;

}
