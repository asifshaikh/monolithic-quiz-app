package com.asif.quizapp.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class Response {
    private String id;
    private String response;
}
