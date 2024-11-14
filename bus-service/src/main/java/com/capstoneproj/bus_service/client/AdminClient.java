package com.capstoneproj.bus_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "admin-service", url = "http://localhost:8081/api/admin")
public interface AdminClient {

    @PostMapping("/api/admin/notify")
    void notifyAdminToAddBus(@RequestParam String routeId);
}
