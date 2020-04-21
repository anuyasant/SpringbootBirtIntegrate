package com.ags.learn.integrate.springbootbirt.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Account {

    private long key;
    private String accountName;
    private String accountNo;
    private String ccy;
    private int paymentType;  // 1.payment 2.payroll 3.fix deposit
    private int userKy;
}
