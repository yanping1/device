package com.lensyn.device.common.response;

import java.io.Serializable;

/**
 * 公共枚举接口
 *
 * @author Gumj
 */
public interface IResultCodeEnum<K extends Serializable, V extends Serializable> {
    /**
     * 枚举编码
     *
     * @return K是枚举code对象类型
     */
    K getCode();

    /**
     * 枚举描述
     *
     * @return V是枚举desc对象类型
     */
    V getDesc();
}
