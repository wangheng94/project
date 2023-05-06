package com.appiron.appleservices.vpp;

import lombok.Data;

import java.util.Date;

/**
 * @author hpg
 * @date 2023/2/9 16:17
 */
@Data
public class STokens {

    private Date expDate;
    private String token;
    private String orgName;
}
