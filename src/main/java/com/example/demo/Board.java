package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "board")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id =0;

    @Column(length=10, nullable=false)
    private String writer;
    @Column(length=10, nullable=false)
    private String title;
    @Column(columnDefinition="TEXT", nullable=false)
    private String content;
}