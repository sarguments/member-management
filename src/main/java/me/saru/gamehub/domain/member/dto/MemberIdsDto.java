package me.saru.gamehub.domain.member.dto;

public class MemberIdsDto {
    private Long id;
    private String userId;

    public MemberIdsDto() {
    }

    public MemberIdsDto(Long id, String userId) {
        this.id = id;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }
}
