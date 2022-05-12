package com.lensyn.device.controller;

import com.lensyn.device.entity.KpiIndex;
import com.lensyn.device.entity.Org;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ProjectName: device
 * @ClassName: TestController
 * @Description:
 * @Author yanping
 * @Date 2022-04-29 8:50
 */
@RestController
@RequestMapping("test")
public class TestController {


    /**
     * 单位填报导入
     *
     * @param
     */
    @GetMapping(value = "/getAll")
    public List<KpiIndex> getAll(@RequestParam(value = "type", required = false) String type) {

        List<KpiIndex> list = new ArrayList<>();
        KpiIndex index = new KpiIndex();
        index.setKey("一类");
        index.setValue("100");
        list.add(index);

        KpiIndex index1 = new KpiIndex();
        index1.setKey("二类");
        index1.setValue("80");
        list.add(index1);

        KpiIndex index2 = new KpiIndex();
        index2.setKey("三类");
        index2.setValue("60");
        list.add(index2);

        return list;
    }

    /**
     * 单位填报导入
     *
     * @param
     */
    @PostMapping(value = "/getOrg")
    public List<Org> getOrg(@RequestBody Org org) {

        List<Org> orgs = new ArrayList<>();
        orgs.add(new Org("瀑电总厂", 10));
        orgs.add(new Org("大岗山公司", 10));
        orgs.add(new Org("猴子岩", 101));
        orgs.add(new Org("龚电总厂", 110));
        orgs.add(new Org("沙坪公司", 123));
        orgs.add(new Org("革什扎公司", 123));
        orgs.add(new Org("双江口分公司", 73));
        orgs.add(new Org("金川公司", 123));
        orgs.add(new Org("枕沙分公司", 63));
        orgs.add(new Org("检修公司", 123));
        orgs.add(new Org("生产指挥中心", 123));
        orgs.add(new Org("新能源公司", 13));
        orgs.add(new Org("库坝中心", 12));
        orgs.add(new Org("大数据公司", 123));
        if (!org.getName().equals("全部")) {
            orgs = orgs.stream().filter(org1 -> org1.getName().equals(org.getName())).collect(Collectors.toList());
        }
        return orgs;

    }


    @GetMapping(value = "/getP")
    public List<Map<String, Object>> getP() {
        List<Map<String, Object>> result = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("key", i + "");
            map.put("value", (double) Math.random() * 100);
            result.add(map);
        }

        return result;

    }


}
