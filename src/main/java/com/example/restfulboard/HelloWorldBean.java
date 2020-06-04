package com.example.restfulboard;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data // Setter, Getter, RequiredArgsConstuctor, ToString, EqualsAndHashCode
@AllArgsConstructor // 모든 필드 값을 파라미터로받는 생성자
public class HelloWorldBean {
    private String message;
}
