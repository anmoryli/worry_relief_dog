package com.anmory.worryreliefdog.model;

import lombok.Data;

import java.util.Date;

/**
 * @author Anmory/李梦杰
 * @description TODO
 * @date 2025-03-30 上午9:56
 */

@Data
public class History {
    private int id;
    private int userId;
    private String fullName;
    private String content;
    private String advice;
    private Date timestamp;
}
