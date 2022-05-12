package com.lensyn.device.entity;

import lombok.Data;

/**
 * @ProjectName: device
 * @ClassName: Org
 * @Description:
 * @Author yanping
 * @Date 2022-04-29 11:13
 */
@Data
public class Org {

    private String name;
    private Integer value;

    public Org(String name, Integer value) {
        this.name = name;
        this.value = value;
    }
}
