package me.saru.gamehub.domain.web;

import me.saru.gamehub.domain.member.MemberService;
import me.saru.gamehub.domain.member.dto.MemberRequestDto;
import me.saru.gamehub.domain.member.dto.MemberResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/members")
public class MemberController {
    private MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("")
    public @ResponseBody
    Long saveMember(@RequestBody @Valid MemberRequestDto memberRequestDto) {
        return memberService.save(memberRequestDto);
    }

    @GetMapping("")
    public String findAll(Model model) {
        model.addAttribute("members", memberService.getMemberIds());
        return "members";
    }

    @GetMapping("/{id}")
    public String findMemberByUserId(@PathVariable Long id, Model model) {
        MemberResponseDto memberResponseDto = memberService.findById(id);
        model.addAttribute("member", memberResponseDto);
        return "member";
    }
}
