package com.ags.learn.integrate.springbootbirt.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class User {

    private Long userKy;
    private String fullName;
    private String email;
    private String mobile;
    private Date birthDay;
    private String userId;
    private String language;
    private String companyName;
    private List<Account> accounts;
    
}
