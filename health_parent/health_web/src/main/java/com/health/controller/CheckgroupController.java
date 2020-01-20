package com.health.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.health.entity.PageResult;
import com.health.entity.QueryPageBean;
import com.health.entity.Result;
import com.health.pojo.TCheckgroup;
import com.health.pojo.TCheckitem;
import com.health.service.CheckItemService;
import com.health.service.CheckgroupService;

@RestController
@RequestMapping("/checkgroup")
public class CheckgroupController {

	@Reference
	CheckgroupService checkgroupService;
	
	//查询所有
	@RequestMapping("findAll")
	public Result findAll(){
		return new Result(true, "", checkgroupService.findAll());
	}
	
	//新增
	@RequestMapping("/add")
	public Result add(@RequestBody TCheckgroup checkgroup,Integer[] checkitemIds){
		try {
			checkgroupService.add(checkgroup, checkitemIds);
			return new Result(true, "ok");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			e.printStackTrace();
			return new Result(false, "error");
		}
	}
	
	//分页查询
	@RequestMapping("/findPage")
	public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
		PageResult pageResult = checkgroupService.pageQuery(queryPageBean.getCurrentPage(),queryPageBean.getPageSize(),queryPageBean.getQueryString());
		return pageResult;
	}
	//根据id查询
	@RequestMapping("/findById")
	public Result findById(Integer id){
		TCheckgroup checkgroup = checkgroupService.findById(id);
		if(checkgroup!=null){
			return new Result(true, "", checkgroup);
		}else{
			return new Result(false, "获取数据失败");
		}
	}
	//根据检查组合id查询对应的所有检查项id
	@RequestMapping("/findCheckItemIdsByCheckGroupId")
	public Result findCheckItemIdsByCheckGroupId(Integer id){
		try {
			List<Integer> list = checkgroupService.findCheckItemIdsByCheckGroup(id);
			return new Result(true, "", list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			e.printStackTrace();
			return new Result(false, "error");
		}
	}
	
	//编辑
	@RequestMapping("/edit")
	public Result edit(@RequestBody TCheckgroup checkgroup,Integer[] checkitemIds){
		try {
			checkgroupService.edit(checkgroup, checkitemIds);
			return new Result(true, "success");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			e.printStackTrace();
			return new Result(false, "error");
		}
	}
}
