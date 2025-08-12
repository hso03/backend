package com.osh.backend.member.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 멤버 정보 Entity
 * 회원가입시 필요사항(이메일, 비밀번호, 이름, 연락처)
 */
@Getter
@Entity
@Table(name = "members")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_no")
    private Long memberNo;

    @Column(name = "member_email", unique = true, nullable = false)
    private String memberEmail;

    @Column(name = "member_password")
    private String memberPassword;

    @Column(name = "member_name")
    private String memberName;

    @Column(name = "member_mobile", unique = true, nullable = false)
    private String memberMobile;

    @Column(name = "member_createAt")
    private LocalDateTime memberCreatedAt;

    @Column(name = "member_withdrawalAt")
    private LocalDateTime memberWithdrawalAt;

    @Builder
    public Member(String memberEmail, String memberPassword, String memberName, String memberMobile) {
        this.memberEmail = memberEmail;
        this.memberPassword = memberPassword;
        this.memberName = memberName;
        this.memberMobile = memberMobile;
    }

    @PrePersist
    public void prePersist(){
        this.memberCreatedAt = LocalDateTime.now();
    }

}