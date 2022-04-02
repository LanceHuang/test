package com.lance.test.hibernatevalidator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

/**
 * @author Lance
 * @since 2022/3/31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestBean {

    @NotBlank
    private String username;
    private String password;
    @Min(1)
    private int age;
    @Positive
    private int classId;
}
