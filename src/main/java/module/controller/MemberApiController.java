package module.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;
import module.Member;
import module.service.MemberService;
import support.Response;
import support.ResponseCode;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/member")
public class MemberApiController {

    private final MemberService memberService;

    @GetMapping("/list")
    public ResponseEntity<Response<List<Member>>> list() {
        List<Member> members = memberService.findAll();

        return ResponseEntity
            .ok(new Response<>(ResponseCode.Ok, "조회 완료", members));
    }

    // TODO: 회원 추가하기
    @PostMapping("/add")
    public ResponseEntity<Response<Long>> add(@ModelAttribute Member member) {
    	Long memberId = memberService.add(member.getName(), member.getAge());
        return ResponseEntity
                .ok(new Response<>(ResponseCode.Ok, "추가 완료", memberId));
    }
    

    // TODO: 회원 수정하기
    @PostMapping("/edit/{id}")
    public ResponseEntity<Response<Void>> edit(@PathVariable Long id, @ModelAttribute Member member, RedirectAttributes attributes) {
    	memberService.update(id, member.getName(), member.getAge());
    	return ResponseEntity
    			.ok(new Response<>(ResponseCode.Ok, "수정 완료"));
    }
    
    // TODO: 회원 삭제하기
    @PostMapping("/delete/{id}")
    public ResponseEntity<Response<Void>> delete(@PathVariable Long id) {
    	memberService.delete(id);
    	return ResponseEntity
    			.ok(new Response<>(ResponseCode.Ok, "삭제 완료"));
    }

    // TODO: 회원 찾기
    @GetMapping("/{id}")
    public ResponseEntity<Response<Member>> get(@PathVariable Long id) {
    	Member member = memberService.findById(id);
    	return ResponseEntity
    			.ok(new Response<>(ResponseCode.Ok, "찾기 완료", member));
    }

}
