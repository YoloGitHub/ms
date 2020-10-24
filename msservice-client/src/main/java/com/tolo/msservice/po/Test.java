package com.tolo.msservice.po;

import lombok.Data;

import java.sql.Date;

@Data
public class Test {

    private String id;

    private String name;

    private String password;

    private Date createdate;

    private Date lastDate;
}
