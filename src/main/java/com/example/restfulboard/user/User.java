package com.example.restfulboard.user;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
public class User {
    private Integer id;
    // 두글자 이상, 에러 메시지
    @Size(min=2, message ="Name은 2글자 이상 입력해 주세요.")
    private String name;
    @Past
    private Date joinDate;
}
