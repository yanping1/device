package com.lensyn.device.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author yanp
 * @email yanping@lensyn.com
 * @date 2022-05-12 10:23:05
 */
@Data
@TableName("power_station_base_info")
public class PowerStationBaseInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 
	 */
	private String orgName;
	/**
	 * 大坝高度
	 */
	private String damHeight;
	/**
	 * 设计发电量
	 */
	private String designPowerGeneration;
	/**
	 * 坝型
	 */
	private String damType;
	/**
	 * 机型
	 */
	private String machineType;
	/**
	 * 总库容
	 */
	private String totalStorageCapacity;
	/**
	 * 调节库容
	 */
	private String adjustStorageCapacity;
	/**
	 * 总装机容量
	 */
	private String totalInstalledCapacity;
	/**
	 * 投产时间
	 */
	private String putIntoProductionDate;
	/**
	 * 建设状态
	 */
	private String buildStatus;
	/**
	 * 图片
	 */
	private String picture;
	/**
	 * 梯级数
	 */
	private Integer stepNum;
	/**
	 * 
	 */
	private String introduction;

}
