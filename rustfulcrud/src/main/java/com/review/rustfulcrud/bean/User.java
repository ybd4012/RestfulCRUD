package com.review.rustfulcrud.bean;


import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private String userName;
    private String passWord;
    private Integer uid;
    private String perms;
}
