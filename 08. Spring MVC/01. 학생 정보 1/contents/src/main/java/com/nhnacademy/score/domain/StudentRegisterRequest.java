package com.nhnacademy.score.domain;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import lombok.Value;
import org.hibernate.validator.constraints.Length;

@Value
public class StudentRegisterRequest {
    @NotBlank
    String name;

    @Email
    String email;

    @Min(0)
    @Max(100)
    int score;

    @Length(min = 0, max = 200)
    String comment;
}