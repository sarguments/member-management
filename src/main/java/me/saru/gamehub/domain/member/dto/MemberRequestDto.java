package me.saru.gamehub.domain.member.dto;

import me.saru.gamehub.domain.member.Member;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class MemberRequestDto {
    private Long id;

    @Size(max = 10, message = "{Size.user.ten}")
    @NotEmpty(message = "{Exist.user.userId}")
    private String userId;

    @Size(max = 10, message = "{Size.user.ten}")
    @NotEmpty(message = "{Exist.user.name}")
    private String name;

    @Size(max = 10, message = "{Size.user.twenty}")
    @Nullable
    private String nickName;

    @Size(max = 20, message = "{Size.user.fifty}")
    @NotEmpty(message = "{Exist.user.email}")
    @Email(message = "{Valid.user.email}")
    private String email;

    public MemberRequestDto() {
    }

    public MemberRequestDto(String userId, String name, String nickName, String email) {
        this.userId = userId;
        this.name = name;
        this.nickName = nickName;
        this.email = email;
    }

    public Member toEntity() {
        return new Member(userId, name, nickName, email);
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
}
