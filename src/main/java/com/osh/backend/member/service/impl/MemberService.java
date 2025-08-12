package com.osh.backend.member.service.impl;

import com.osh.backend.member.dto.MemberRegisterRequest;
import com.osh.backend.member.dto.MemberResponse;
import com.osh.backend.member.dto.MemberUpdateRequest;
import com.osh.backend.member.service.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService implements MemberServiceImpl {

    @Override
    public MemberResponse registerMember(@Validated MemberRegisterRequest memberRegisterRequest) {
        return null;
    }

    @Override
    public MemberResponse getMember(String memberEmail) {
        return null;
    }

    @Override
    public MemberResponse getMember(Long memberNo) {
        return null;
    }

    @Override
    public MemberResponse updateMember(MemberUpdateRequest memberUpdateRequest) {
        return null;
    }

    @Override
    public MemberResponse withdrawalMember(String memberEmail) {
        return null;
    }

    @Override
    public MemberResponse withdrawalMember(Long memberNo) {
        return null;
    }

}
