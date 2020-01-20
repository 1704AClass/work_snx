package com.health.service;

import java.util.List;

import com.health.entity.PageResult;
import com.health.pojo.TCheckitem;

public interface CheckItemService {

	public List<TCheckitem> checkItemList();
	
	public PageResult findPage(int page,int size,String queryString);
	
	public void add(TCheckitem checkitem);
	
	public void delete(Integer id);
	
	public void edit(TCheckitem checkitem);
	
	public TCheckitem findById(Integer id);
}
