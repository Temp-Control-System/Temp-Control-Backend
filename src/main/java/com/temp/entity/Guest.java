package com.temp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Guest {

    /**
     * 身份证号
     */
    private String idCode;

    private String phoneNumber;

    /**
     * 手机校验码
     */
    private String verificationCode;
}
