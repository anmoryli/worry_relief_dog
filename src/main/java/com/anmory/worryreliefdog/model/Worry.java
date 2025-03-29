package com.anmory.worryreliefdog.model;

import lombok.Data;

import java.util.Date;

/**
 * @author Anmory/李梦杰
 * @description TODO
 * @date 2025-03-29 下午2:01
 */

@Data
public class Worry {
    private int worryId;
    private int userId;
    private String content;
    private Date timestamp;
    private boolean resolved;
    private String advice;
}
