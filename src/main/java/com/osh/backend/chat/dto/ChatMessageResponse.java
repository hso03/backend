package com.osh.backend.chat.dto;

/**
 * record는 파라미터를 private final로 선언
 * getter, hashCode(), toString(), equals() 메서드 자동 제공
 */
public record ChatMessageResponse(String username, String content) {
}
