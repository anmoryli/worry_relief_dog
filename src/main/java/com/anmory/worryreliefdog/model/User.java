package com.anmory.worryreliefdog.model;

import lombok.Data;

import java.util.Date;

/**
 * @author Anmory/李梦杰
 * @description TODO
 * @date 2025-03-29 下午1:06
 */

@Data
public class User {
    private int userId;
    private String username;
    private String password;
    private String email;
    private Date createdAt;
    private Date updatedAt;
}
