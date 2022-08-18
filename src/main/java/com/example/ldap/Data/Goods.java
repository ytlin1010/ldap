package com.example.ldap.Data;

import lombok.Data;

import java.util.Date;

@Data
public class Goods {
    private Long id;//id bigint primary key auto_increment
    private String name;//name varchar(100) not null
    private String remark;//remark text
    private Date createdTime;//createdTime datetime
}
