package com.osh.backend.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberUpdateRequest {

    private final String memberPassword;

    private final String memberMobile;

}
