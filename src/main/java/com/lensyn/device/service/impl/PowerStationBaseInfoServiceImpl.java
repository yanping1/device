package com.lensyn.device.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lensyn.device.dao.PowerStationBaseInfoMapper;
import com.lensyn.device.entity.PowerStationBaseInfo;
import com.lensyn.device.service.PowerStationBaseInfoService;
import org.springframework.stereotype.Service;


@Service("powerStationBaseInfoService")
public class PowerStationBaseInfoServiceImpl extends ServiceImpl<PowerStationBaseInfoMapper, PowerStationBaseInfo> implements PowerStationBaseInfoService {


}