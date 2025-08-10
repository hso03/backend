package com.osh.backend.member.domain;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "members")
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    private String memberEmail;

    private String memberPassword;

    private String name;

}