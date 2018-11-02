package me.saru.gamehub.domain.member.dto;

import me.saru.gamehub.domain.member.Member;

import java.time.LocalDate;

public class MemberResponseDto {
    private Long id;
    private String userId;
    private String name;
    private String nickName;
    private String email;
    private LocalDate createdDate;

    public MemberResponseDto() {
    }

    public MemberResponseDto(Long id, String userId, String name, String nickName, String email) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.nickName = nickName;
        this.email = email;
    }

    public MemberResponseDto(Member member) {
        id = member.getId();
        userId = member.getUserId();
        name = member.getName();
        nickName = member.getNickName();
        email = member.getEmail();
        createdDate = member.getCreateDate();
    }

    public Long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getNickName() {
        return nickName;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }
}
