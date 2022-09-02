package com.example.demo.model;

import lombok.Builder;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

// 테스트용 모델
@Builder
@RequiredArgsConstructor
public class DemoModel {

    @NonNull
    private String id;
}

