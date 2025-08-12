package com.osh.backend.controller;

import com.osh.backend.member.dto.MemberRegisterRequest;
import com.osh.backend.member.dto.MemberResponse;
import com.osh.backend.member.service.impl.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/register")
    public ResponseEntity<MemberResponse> registerMember(@Validated @RequestBody MemberRegisterRequest memberRegisterRequest){
        MemberResponse memberResponse = memberService.registerMember(memberRegisterRequest);
        return ResponseEntity.ok(memberResponse);
    }

}
