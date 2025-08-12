package com.osh.backend.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
public class MemberResponse {

    private final Long memberNo;

    private final String memberEmail;

    private final String memberName;

    private final String memberMobile;

    private final LocalDateTime memberCreatedAt;

    private final LocalDateTime memberWithdrawalAt;

}
