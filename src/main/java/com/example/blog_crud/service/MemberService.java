package com.example.blog_crud.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.blog_crud.dto.MemberDTO;
import com.example.blog_crud.entity.MemberEntity;
import com.example.blog_crud.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public void postSignUpPage(MemberDTO memberDTO) {
        // 1. dto -> entity로 변환 
        // 2. repository의 save 메서드 호출 
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
        memberRepository.save(memberEntity);
        // repository의 save 메서드 호출(조건: entity 객체를 넘겨줘야 함)
    }

    public MemberDTO postLogin(MemberDTO memberDTO) {
        // 1. 회원이 입력한 이메일로 DB에서 조회 
        // 2. DB에서 조회한 비밀번호와 사용자 입력 비밀번호가 일치하는지 판단 
        Optional<MemberEntity> byMemberEmail = memberRepository.findByMemberEmail(memberDTO.getMemberEmail());  
        if(byMemberEmail.isPresent()) {
            // 해당 이메일을 가진 회원 정보가 있음(회원가입 된 이메일)
            MemberEntity memberEntity = byMemberEmail.get();
            if(memberEntity.getMemberPW().equals(memberDTO.getMemberPW())) {
                // 비밀번호 일치 여부 확인 -> 일치 
                // entity 객체를 dto 로 변환 후 return
                MemberDTO dto = MemberDTO.toMemberDTO(memberEntity);
                return dto;
            } else {
                // 비밀번호 일치 여부 확인 -> 불일치 
                return null; 
            }
        } else {
            // 해당 이메일을 가진 회원 정보가 없음(회원가입되지 않은 이메일)
            return null;
        }
    }

    public List<MemberDTO> findAll() {
        List<MemberEntity> memberEntityList = memberRepository.findAll();
        List<MemberDTO> memberDTOList = new ArrayList<>();
        for (MemberEntity memberEntity: memberEntityList) {
            memberDTOList.add(MemberDTO.toMemberDTO(memberEntity));
        //    MemberDTO memberDTO = MemberDTO.toMemberDTO(memberEntity);
        //    memberDTOList.add(memberDTO);
        }
        return memberDTOList;
    }

    public MemberDTO findById(Long id) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(id);
        if (optionalMemberEntity.isPresent()) {
        //    MemberEntity memberEntity = optionalMemberEntity.get();
        //    MemberDTO memberDTO = MemberDTO.toMemberDTO(memberEntity);
        //    return memberDTO;
            return MemberDTO.toMemberDTO(optionalMemberEntity.get());
        } else {
            return null;
        }
    }
}
