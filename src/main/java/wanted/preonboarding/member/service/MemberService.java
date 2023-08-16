package wanted.preonboarding.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wanted.preonboarding.member.domain.Member;
import wanted.preonboarding.member.dto.MemberCreateForm;
import wanted.preonboarding.member.repository.MemberRepository;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void createMember(MemberCreateForm memberCreateForm) {
        // 이메일 존재하는지 검증

        Member newMember = Member.builder()
                .email(memberCreateForm.getEmail())
                .password(memberCreateForm.getPassword())
                .build();

        memberRepository.save(newMember);
    }
}
