package com.health.service;

import java.util.List;

import com.health.entity.PageResult;
import com.health.pojo.TCheckgroup;
import com.health.pojo.TCheckitem;


public interface CheckgroupService {
	
	public List<TCheckitem> findAll();
	
	void add(TCheckgroup checkgroup,Integer[] checkitemIds);

	PageResult pageQuery(Integer currentPage, Integer pageSize,String queryString);
	
	TCheckgroup findById(Integer id);
	
	List<Integer> findCheckItemIdsByCheckGroup(Integer id);
	
	public void edit(TCheckgroup checkgroup,Integer[] checkitemIds);
}
