package com.health.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.health.entity.PageResult;
import com.health.mapper.TSetmealMapper;
import com.health.pojo.TSetmeal;
import com.health.pojo.TSetmealExample;
import com.health.pojo.TSetmealExample.Criteria;
import com.health.service.SetmealService;
@Service
public class SetmealServiceImpl implements SetmealService{

	@Resource
	TSetmealMapper setmealMapper;
	
	@Override
	public void add(TSetmeal setmeal, Integer[] checkgroupIds) {
		// TODO Auto-generated method stub
		setmealMapper.insert(setmeal);
		if(checkgroupIds!=null && !"".equals(checkgroupIds)){
			setSetmealAndCheckGroup(setmeal.getId(),checkgroupIds);
		}
	}

	//绑定套餐和检查组的多对多关系
	private void setSetmealAndCheckGroup(Integer id, Integer[] checkgroupIds) {
		// TODO Auto-generated method stub
		for (Integer checkgroupId : checkgroupIds) {
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("setmeal_id", id);
			map.put("checkgroup_id", checkgroupId);
			setmealMapper.setSetmealAndCheckGroup(map);
		}
	}

	@Override
	public PageResult findPage(Integer currentPage, Integer pageSize,
			String queryString) {
		// TODO Auto-generated method stub
		TSetmealExample example = new TSetmealExample();
		Criteria criteria = example.createCriteria();
		if(queryString!=null && !"".equals(queryString)){
			criteria.andNameLike("%"+queryString+"%");
		}
		PageHelper.startPage(currentPage, pageSize);
		PageInfo<TSetmeal> info = new PageInfo<TSetmeal>(setmealMapper.selectByExample(example));
		return new PageResult(info.getTotal(), info.getList());
	}

	
}
