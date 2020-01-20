package com.health.service;

import com.health.entity.PageResult;
import com.health.pojo.TSetmeal;


public interface SetmealService {
	public void add(TSetmeal setmeal,Integer[] checkgroupIds);

	public PageResult findPage(Integer currentPage, Integer pageSize,
			String queryString);
}
