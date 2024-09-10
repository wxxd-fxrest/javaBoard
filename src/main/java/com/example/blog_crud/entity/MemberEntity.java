package com.example.blog_crud.entity;

import com.example.blog_crud.dto.MemberDTO;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@Table(name = "member_table")
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @Column(unique = true)
    private String memberEmail;

    @Column
    private String memberPW;

    @Column
    private String memberName;

    public static MemberEntity toMemberEntity(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberEmail(memberDTO.getMemberEmail());
        memberEntity.setMemberPW(memberDTO.getMemberPW());
        memberEntity.setMemberName(memberDTO.getMemberName());
        return memberEntity;
    }
}
