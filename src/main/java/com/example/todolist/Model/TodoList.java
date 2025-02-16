package com.example.todolist.Model;

import jakarta.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Table
public class TodoList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id ;
    @Column(name = "title")
    String title ;
    @Column(name = "description")
    String description ;
    @Column(name = "date")
    String date ;
    @Column(name = "Todo")
    List<String> Todo;
}
