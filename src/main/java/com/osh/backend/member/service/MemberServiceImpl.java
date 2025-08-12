package com.osh.backend.member.service;

import com.osh.backend.member.dto.MemberRegisterRequest;
import com.osh.backend.member.dto.MemberResponse;
import com.osh.backend.member.dto.MemberUpdateRequest;

public interface MemberServiceImpl {

    // C : 멤버 생성
    MemberResponse registerMember(MemberRegisterRequest memberRegisterRequest);

    // R : 멤버 정보(회원 이메일)
    MemberResponse getMember(String memberEmail);

    // R : 멤버 정보(회원 번호)
    MemberResponse getMember(Long memberNo);

    // U : 멤버 업데이트
    MemberResponse updateMember(MemberUpdateRequest memberUpdateRequest);

    // D : 멤버 탈퇴(회원 이메일)
    MemberResponse withdrawalMember(String memberEmail);

    // D : 멤버 탈퇴(회원 번호)
    MemberResponse withdrawalMember(Long memberNo);
}
