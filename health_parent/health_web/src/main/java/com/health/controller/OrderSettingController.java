package com.health.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.dubbo.config.annotation.Reference;
import com.health.entity.Result;
import com.health.poi.POIUtils;
import com.health.pojo.TOrdersetting;
import com.health.service.OrderSettingService;

@RestController
@RequestMapping("/ordersetting")
public class OrderSettingController {

	@Reference
	OrderSettingService orderSettingService;
	
	@RequestMapping("/upload")
	public Result upload(@RequestParam("excelFile")MultipartFile excelFile) throws Exception{
		try {
			List<String[]> list = POIUtils.readExcel(excelFile);
			if(list!=null && list.size()>0){
				List<TOrdersetting> orList = new ArrayList<>();
				for (String[] strings : list) {
					TOrdersetting ordersetting = new TOrdersetting(new Date(strings[0]),Integer.parseInt(strings[1]));
					orList.add(ordersetting);
				}
				orderSettingService.add(orList);
			}
			return new Result(true, "success");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			e.printStackTrace();
			return new Result(false, "error");
		}
	}
	
	
	//根据如期查询预约设置数据
	@RequestMapping("/getOrderSettingByMonth")
	public Result getOrderSettingByMonth(String date){
		try {
			List<Map> list = orderSettingService.getOrderSettingByMonth(date);
			//获取预约设置数据成功
			return new Result(true, "success");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			e.printStackTrace();
			return new Result(false, "error");
		}
	}
}
