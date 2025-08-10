package com.osh.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
@RequestMapping("/api/v1/ip")
@RequiredArgsConstructor
public class IpController {

    @GetMapping
    public ResponseEntity<String> getClientIp(){
        try {
            InetAddress address = InetAddress.getLocalHost();
            String ipAddress = address.getHostAddress();
            return ResponseEntity.status(HttpStatus.OK).body(ipAddress);
        }catch (UnknownHostException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("받아올 수 없습니다.");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("알 수 없음");
        }
    }

}
