package wanted.preonboarding.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wanted.preonboarding.member.domain.Member;
import wanted.preonboarding.member.dto.MemberCreateForm;
import wanted.preonboarding.member.service.MemberService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity createMember(@RequestBody @Valid MemberCreateForm memberCreateForm) {
        memberService.createMember(memberCreateForm);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
