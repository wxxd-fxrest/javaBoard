package com.example.blog_crud.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "board_sts_jpa")
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Long id;

    @Column(length = 20, nullable = false)
    private String boardWriter;

    @Column(length = 30, nullable = false)
    private String boardTitle;
}
