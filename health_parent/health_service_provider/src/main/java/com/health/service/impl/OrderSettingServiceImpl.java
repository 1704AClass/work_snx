package com.health.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.alibaba.dubbo.config.annotation.Service;
import com.health.mapper.TOrdersettingMapper;
import com.health.pojo.TOrdersetting;
import com.health.service.OrderSettingService;

@Service
public class OrderSettingServiceImpl implements OrderSettingService {

	@Resource
	TOrdersettingMapper ordersettingMapper;
	
	/**
	 * 预约设置服务
	 */
	@Override
	public void add(List<TOrdersetting> list) {
		// TODO Auto-generated method stub
		if(list!=null && list.size()>0){
			for (TOrdersetting orderSetting : list) {
				long count = ordersettingMapper.findCountByOrderDate(orderSetting.getOrderdate());
				if(count>0){
					ordersettingMapper.updateByPrimaryKey(orderSetting);
				}else{
					ordersettingMapper.insert(orderSetting);
				}
			}
		}
	}

	//根据日期查询预约设置数据
	@Override
	public List<Map> getOrderSettingByMonth(String date) {
		// TODO Auto-generated method stub
		String dateBegin = date +"-1";//2019-3-1
		String dateEnd = date +"-31";//2019-3-31
		Map map = new HashMap();
		map.put("dateBegin", dateBegin);
		map.put("dateEnd", dateEnd);
		List<TOrdersetting> list = ordersettingMapper.getOrderSettingByMonth(map);
		List<Map> data = new ArrayList<>();
		for (TOrdersetting ordersetting : list) {
			Map orderSettingMap = new HashMap();
			orderSettingMap.put("date", ordersetting.getOrderdate().getDate());//获得日期几号
			orderSettingMap.put("number", ordersetting.getNumber());//可预约人数
			orderSettingMap.put("reservations", ordersetting.getReservations());//已预约人数
			data.add(orderSettingMap);
		}
		return data;
	}

	//根据日期修改可预约人数
	@Override
	public void editNumberByDate(TOrdersetting ordersetting) {
		// TODO Auto-generated method stub
		long count = ordersettingMapper.findCountByOrderDate(ordersetting.getOrderdate());
		if(count>0){
			//当前日期已经进行了预约设置 需要进行修改操作
			ordersettingMapper.updateByPrimaryKey(ordersetting);
		}else{
			//当前日期没有进行预约设置  进行添加操作
			ordersettingMapper.insert(ordersetting);
		}
	}

	
}
