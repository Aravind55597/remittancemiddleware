package com.remittancemiddleware.remittancemiddleware.service;


import com.remittancemiddleware.remittancemiddleware.dataclass.SandboxResponse;

import java.io.IOException;

public interface SandboxAPIService {

    public SandboxResponse testConnection() throws IOException;

    public SandboxResponse authenticate() throws IOException;




}
