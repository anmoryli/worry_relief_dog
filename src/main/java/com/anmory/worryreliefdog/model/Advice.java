package com.anmory.worryreliefdog.model;

import lombok.Data;

import java.util.Date;

/**
 * @author Anmory/李梦杰
 * @description TODO
 * @date 2025-03-29 下午2:17
 */

@Data
public class Advice {
    private int adviceId;
    private int worryId;
    private int userId;
    private String advice;
    private Date timestamp;
}
