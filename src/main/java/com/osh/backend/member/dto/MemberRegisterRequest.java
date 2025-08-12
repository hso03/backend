package com.osh.backend.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberRegisterRequest {

    @Email(message = "유효한 이메일 주소를 입력해주세요.")
    @NotBlank(message = "이메일 입력은 필수 입니다.")
    private final String memberEmail;

    @NotBlank(message = "비밀번호 입력은 필수 입니다.")
    private final String memberPassword;

    @NotBlank(message = "연락처 입력은 필수 입니다.")
    @Pattern(regexp = "^010-\\d{4}-\\d{4}$",
            message = "형식은 010-1234-5678 이어야 합니다.")
    private final String memberMobile;

    @NotBlank(message = "이름 입력은 필수 입니다.")
    private final String memberName;

}
