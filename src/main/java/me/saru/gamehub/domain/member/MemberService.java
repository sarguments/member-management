package me.saru.gamehub.domain.member;

import me.saru.gamehub.domain.exception.ValidCustomException;
import me.saru.gamehub.domain.member.dto.MemberIdsDto;
import me.saru.gamehub.domain.member.dto.MemberRequestDto;
import me.saru.gamehub.domain.member.dto.MemberResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberService {
    private static final String EMAIL = "email";
    private static final String USER_ID = "userId";
    private static final String EXIST_EMAIL = "이미 사용중인 이메일주소입니다";
    private static final String IN_USE_USER_ID = "이미 사용중인 유저아이디입니다";
    private static final String NOT_FOUND = "not found";

    private MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Long save(MemberRequestDto memberRequestDto) {
        verifyDuplicateUserId(memberRequestDto.getUserId());
        verifyDuplicateEmail(memberRequestDto.getEmail());
        return memberRepository.save(memberRequestDto.toEntity()).getId();
    }

    private void verifyDuplicateEmail(String email) {
        if (memberRepository.findByEmail(email).isPresent()) {
            throw new ValidCustomException(EXIST_EMAIL, EMAIL);
        }
    }

    private void verifyDuplicateUserId(String userId) {
        if (memberRepository.findByUserId(userId).isPresent()) {
            throw new ValidCustomException(IN_USE_USER_ID, USER_ID);
        }
    }

    public List<MemberIdsDto> getMemberIds() {
        return memberRepository
                .findAll()
                .stream()
                .map(s -> new MemberIdsDto(s.getId(), s.getUserId()))
                .collect(Collectors.toList());
    }

    public MemberResponseDto findById(Long id) {
        Member member = memberRepository.findById(id)
                .orElse(new Member(NOT_FOUND, NOT_FOUND, NOT_FOUND, NOT_FOUND));
        return new MemberResponseDto(member);
    }
}