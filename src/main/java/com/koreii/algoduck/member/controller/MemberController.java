package com.koreii.algoduck.member.controller;

import com.koreii.algoduck.base.controller.BaseApiController;
import com.koreii.algoduck.member.dto.request.MemberSaveRequestDto;
import com.koreii.algoduck.member.dto.request.MemberUpdateRequestDto;
import com.koreii.algoduck.member.dto.response.MemberResponseDto;
import com.koreii.algoduck.member.dto.response.MemberSimpleResponseDto;
import com.koreii.algoduck.member.enums.Role;
import com.koreii.algoduck.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
@Tag(name = "Members", description = "회원 관련 API")
@Slf4j
public class MemberController extends BaseApiController {

  private final MemberService memberService;

  @Operation(
      summary = "회원가입",
      description = "새로운 회원을 등록합니다."
      )
  @PostMapping(value = "/join", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<MemberResponseDto> join(@RequestPart(value = "requestDto") MemberSaveRequestDto requestDto, @RequestPart(value = "file", required = false) MultipartFile file) {
    try {
      log.info("requestDto = {}", requestDto);
      log.info("file = {}", file);

      MemberResponseDto memberResponseDto = memberService.join(requestDto, file);
      return ResponseEntity.status(HttpStatus.CREATED).body(memberResponseDto);
    } catch (Exception e) {
      log.error("join error", e);
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
  }

  @Operation(summary = "로그인 아이디 중복 확인", description = "로그인 아이디가 중복되었는지 확인합니다.")
  @GetMapping("/check-login-id")
  public ResponseEntity<Boolean> isUniqueLoginId(@RequestParam String loginId) {
    boolean isUnique = memberService.isUniqueLoginId(loginId);
    return ResponseEntity.ok(isUnique);
  }

  @Operation(summary = "닉네임 중복 확인", description = "닉네임이 중복되었는지 확인합니다.")
  @GetMapping("/check-nickname")
  public ResponseEntity<Boolean> isUniqueNickname(@RequestParam String nickname) {
    boolean isUnique = memberService.isUniqueNickname(nickname);
    return ResponseEntity.ok(isUnique);
  }

  @Operation(summary = "이메일 중복 확인", description = "이메일이 중복되었는지 확인합니다.")
  @GetMapping("/check-email")
  public ResponseEntity<Boolean> isUniqueEmail(@RequestParam String email) {
    boolean isUnique = memberService.isUniqueEmail(email);
    return ResponseEntity.ok(isUnique);
  }

  @Operation(summary = "모든 회원 조회", description = "모든 회원 정보를 페이징 처리하여 반환합니다.")
  @GetMapping
  public ResponseEntity<List<MemberSimpleResponseDto>> findAllMembers(@RequestParam int pageNumber,
                                                                      @RequestParam int pageSize) {
    List<MemberSimpleResponseDto> members = memberService.findAllMembers(pageNumber, pageSize);
    HttpHeaders headers = new HttpHeaders();
    headers.add("X-Total-Count", String.valueOf(members.size()));
    return ResponseEntity.ok().headers(headers).body(members);
  }

  @Operation(summary = "회원 검색 - 로그인 아이디", description = "로그인 아이디로 회원을 검색합니다.")
  @GetMapping("/search-by-login-id")
  public ResponseEntity<List<MemberSimpleResponseDto>> findMembersWithLoginId(@RequestParam String loginId,
                                                                              @RequestParam int pageNumber,
                                                                              @RequestParam int pageSize) {
    List<MemberSimpleResponseDto> members = memberService.findMembersWithLoginId(loginId, pageNumber, pageSize);
    return ResponseEntity.ok(members);
  }

  @Operation(summary = "회원 검색 - 닉네임", description = "닉네임으로 회원을 검색합니다.")
  @GetMapping("/search-by-nickname")
  public ResponseEntity<List<MemberSimpleResponseDto>> findMembersWithNickname(@RequestParam String nickname,
                                                                               @RequestParam int pageNumber,
                                                                               @RequestParam int pageSize) {
    List<MemberSimpleResponseDto> members = memberService.findMembersWithNickname(nickname, pageNumber, pageSize);
    return ResponseEntity.ok(members);
  }

  @Operation(summary = "회원 검색 - 역할", description = "역할(Role)로 회원을 검색합니다.")
  @GetMapping("/search-by-role")
  public ResponseEntity<List<MemberSimpleResponseDto>> findMembersWithRole(@RequestParam Role role,
                                                                           @RequestParam int pageNumber,
                                                                           @RequestParam int pageSize) {
    List<MemberSimpleResponseDto> members = memberService.findMembersWithRole(role, pageNumber, pageSize);
    return ResponseEntity.ok(members);
  }

  @Operation(summary = "회원 상세 조회", description = "회원 ID로 회원 정보를 조회합니다.")
  @GetMapping("/{memberId}")
  public ResponseEntity<MemberResponseDto> findMemberByLoginId(@PathVariable Long memberId) {
    MemberResponseDto member = memberService.findMemberByLoginId(memberId);
    if (member == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    return ResponseEntity.ok(member);
  }

  @Operation(summary = "회원 정보 업데이트", description = "회원 정보를 업데이트합니다.")
  @PutMapping("/{memberId}")
  public ResponseEntity<MemberResponseDto> update(@PathVariable Long memberId,
                                                  @RequestPart MemberUpdateRequestDto memberUpdateRequestDto,
                                                  @RequestPart(required = false) MultipartFile file) {
    try {
      MemberResponseDto updatedMember = memberService.update(memberUpdateRequestDto, file);
      return ResponseEntity.ok(updatedMember);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
  }
}