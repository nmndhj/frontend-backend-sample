package com.example.boot.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "board_t")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 100)
    @NotNull
    private String title;

    @Column(length = 400)
    @NotNull
    private String content;

    @Column(length = 50)
    @NotNull
    private String writer;


}
