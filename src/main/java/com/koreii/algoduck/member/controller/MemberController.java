package com.koreii.algoduck.member.controller;

import com.koreii.algoduck.base.controller.BaseApiController;
import com.koreii.algoduck.base.dto.response.ApiResponse;
import com.koreii.algoduck.member.dto.request.LoginRequestDto;
import com.koreii.algoduck.member.dto.request.MemberSaveRequestDto;
import com.koreii.algoduck.member.dto.request.MemberUpdateRequestDto;
import com.koreii.algoduck.member.dto.response.MemberResponseDto;
import com.koreii.algoduck.member.dto.response.MemberPagingResponseDto;
import com.koreii.algoduck.member.dto.response.MemberSimpleResponseDto;
import com.koreii.algoduck.member.entity.Member;
import com.koreii.algoduck.member.enums.Role;
import com.koreii.algoduck.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
@Tag(name = "Member", description = "회원 관련 API")
@Slf4j
public class MemberController extends BaseApiController {

  private final MemberService memberService;

  @Operation(summary = "회원가입", description = "새로운 회원을 등록합니다.")
  @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<ApiResponse<MemberResponseDto>> join(
      @RequestPart("memberSaveRequestDto") MemberSaveRequestDto memberSaveRequestDto,
      @RequestPart(value = "file", required = false) MultipartFile file) {
    log.info("memberSaveRequestDto = {}", memberSaveRequestDto);
    log.info("file = {}", file);

    MemberResponseDto memberResponseDto = memberService.join(memberSaveRequestDto, file);
    return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(memberResponseDto));
  }

  @Operation(summary = "로그인 아이디 중복 확인", description = "로그인 아이디가 중복되었는지 확인합니다.")
  @GetMapping("/exists/login-id")
  public ResponseEntity<ApiResponse<Boolean>> isUniqueLoginId(@RequestParam String loginId) {
    boolean isUnique = memberService.isUniqueLoginId(loginId);
    return ResponseEntity.ok(ApiResponse.success(isUnique));
  }

  @Operation(summary = "닉네임 중복 확인", description = "닉네임이 중복되었는지 확인합니다.")
  @GetMapping("/exists/nickname")
  public ResponseEntity<ApiResponse<Boolean>> isUniqueNickname(@RequestParam String nickname) {
    boolean isUnique = memberService.isUniqueNickname(nickname);
    return ResponseEntity.ok(ApiResponse.success(isUnique));
  }

  @Operation(summary = "이메일 중복 확인", description = "이메일이 중복되었는지 확인합니다.")
  @GetMapping("/exists/email")
  public ResponseEntity<ApiResponse<Boolean>> isUniqueEmail(@RequestParam String email) {
    boolean isUnique = memberService.isUniqueEmail(email);
    return ResponseEntity.ok(ApiResponse.success(isUnique));
  }

  @Operation(summary = "모든 회원 조회", description = "모든 회원 정보를 페이징 처리하여 반환합니다.")
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ApiResponse<MemberPagingResponseDto>> findAllMembers(
      @RequestParam int pageNumber,
      @RequestParam int pageSize) {
    long totalCount = memberService.countAllMembers();
    List<MemberSimpleResponseDto> members = memberService.findAllMembers(pageNumber, pageSize);

    MemberPagingResponseDto responseDto = MemberPagingResponseDto.builder()
        .totalCount(totalCount)
        .members(members)
        .build();

    return ResponseEntity.ok(ApiResponse.success(responseDto));
  }

  @Operation(summary = "회원 검색 - 로그인 아이디", description = "로그인 아이디로 회원을 검색합니다.")
  @GetMapping(value = "/login_id", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ApiResponse<MemberPagingResponseDto>> findMembersWithLoginId(
      @RequestParam String loginId,
      @RequestParam int pageNumber,
      @RequestParam int pageSize) {
    long totalCount = memberService.countMembersWithLoginId(loginId);
    List<MemberSimpleResponseDto> members = memberService.findMembersWithLoginId(loginId, pageNumber, pageSize);

    MemberPagingResponseDto responseDto = MemberPagingResponseDto.builder()
        .totalCount(totalCount)
        .members(members)
        .build();

    return ResponseEntity.ok(ApiResponse.success(responseDto));
  }

  @Operation(summary = "회원 검색 - 닉네임", description = "닉네임으로 회원을 검색합니다.")
  @GetMapping(value = "/nickname", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ApiResponse<MemberPagingResponseDto>> findMembersWithNickname(
      @RequestParam String nickname,
      @RequestParam int pageNumber,
      @RequestParam int pageSize) {
    long totalCount = memberService.countMembersWithNickname(nickname);
    List<MemberSimpleResponseDto> members = memberService.findMembersWithNickname(nickname, pageNumber, pageSize);

    MemberPagingResponseDto responseDto = MemberPagingResponseDto.builder()
        .totalCount(totalCount)
        .members(members)
        .build();

    return ResponseEntity.ok(ApiResponse.success(responseDto));
  }

  @Operation(summary = "회원 검색 - 역할", description = "역할(Role)로 회원을 검색합니다.")
  @GetMapping(value = "/role", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ApiResponse<MemberPagingResponseDto>> findMembersWithRole(
      @RequestParam Role role,
      @RequestParam int pageNumber,
      @RequestParam int pageSize) {
    long totalCount = memberService.countMembersWithRole(role);
    List<MemberSimpleResponseDto> members = memberService.findMembersWithRole(role, pageNumber, pageSize);

    MemberPagingResponseDto responseDto = MemberPagingResponseDto.builder()
        .totalCount(totalCount)
        .members(members)
        .build();

    return ResponseEntity.ok(ApiResponse.success(responseDto));
  }

  @Operation(summary = "회원 상세 조회", description = "회원 ID로 회원 정보를 조회합니다.")
  @GetMapping("/id/{memberId}")
  public ResponseEntity<ApiResponse<MemberResponseDto>> findMemberByMemberId(@PathVariable Long memberId) {
    MemberResponseDto member = memberService.findMemberByMemberId(memberId);
    if (member == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.failure("해당 회원을 찾을 수 없습니다."));
    }
    return ResponseEntity.ok(ApiResponse.success(member));
  }

  @Operation(summary = "회원 정보 업데이트", description = "회원 정보를 업데이트합니다.")
  @PutMapping(value = "/{memberId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<ApiResponse<MemberResponseDto>> update(
      @PathVariable Long memberId,
      @RequestPart("memberUpdateRequestDto") MemberUpdateRequestDto memberUpdateRequestDto,
      @RequestPart(value = "file", required = false) MultipartFile file) {
    log.info("memberUpdateRequestDto = {}", memberUpdateRequestDto);
    log.info("file = {}", file);

    MemberResponseDto responseDto = memberService.update(memberId, memberUpdateRequestDto, file);
    return ResponseEntity.ok(ApiResponse.success(responseDto));
  }

  @Operation(summary = "로그인", description = "로그인 요청을 처리합니다.")
  @PostMapping("/login")
  public ResponseEntity<ApiResponse<MemberResponseDto>> login(
      @RequestBody LoginRequestDto loginRequestDto,
      HttpServletRequest request) {
    MemberResponseDto memberResponseDto = memberService.login(
        loginRequestDto.getLoginId(),
        loginRequestDto.getPassword(),
        request
    );

    return ResponseEntity.ok(ApiResponse.success(memberResponseDto));
  }

  @Operation(summary = "로그아웃", description = "로그아웃 요청을 처리합니다.")
  @PostMapping("/logout")
  public ResponseEntity<ApiResponse<Void>> logout(HttpServletRequest request) {
    memberService.logout(request);
    return ResponseEntity.ok(ApiResponse.success(null));
  }
}
