package com.lensyn.device.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lensyn.device.entity.PowerStationBaseInfo;
import com.lensyn.device.service.PowerStationBaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author yanp
 * @email yanping@lensyn.com
 * @date 2022-05-12 10:23:05
 */
@RestController
@RequestMapping("power-station-base-info")
public class PowerStationBaseInfoController {
    @Autowired
    private PowerStationBaseInfoService powerStationBaseInfoService;


    /**
     * 信息
     */
    @GetMapping("/info")
    public PowerStationBaseInfo info(@RequestParam("orgName") String orgName) {
        PowerStationBaseInfo powerStationBaseInfo = powerStationBaseInfoService.getOne(Wrappers.<PowerStationBaseInfo>lambdaQuery()
                .eq(PowerStationBaseInfo::getOrgName, orgName));

        return powerStationBaseInfo;
    }


}
