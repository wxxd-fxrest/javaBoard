package com.example.blog_crud.dto;

import com.example.blog_crud.entity.MemberEntity;

import lombok.*;

@Getter
@Setter 
@NoArgsConstructor
@ToString
public class MemberDTO {
    public static MemberDTO to;
    private Long id;
    private String memberEmail;
    private String memberPW;
    private String memberName;

    public static MemberDTO toMemberDTO(MemberEntity memberEntity) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(memberEntity.getId());       
        memberDTO.setMemberEmail(memberEntity.getMemberEmail());
        memberDTO.setMemberPW(memberEntity.getMemberPW()); 
        memberDTO.setMemberName(memberEntity.getMemberName()); 
        return memberDTO;
    }
}
