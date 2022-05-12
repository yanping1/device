package com.lensyn.device.entity;

import lombok.Data;

/**
 * 指标
 *
 * @author yanp
 * @email yanping@lensyn.com
 * @date 2021-09-01 09:49:42
 */
@Data
public class KpiIndex {
    private static final long serialVersionUID = 1L;

    /**
     * 分类id
     */
    private String key;

    /**
     * 值
     */
    private String value;


}
