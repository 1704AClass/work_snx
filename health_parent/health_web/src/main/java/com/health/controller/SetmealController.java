package com.health.controller;


import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.dubbo.config.annotation.Reference;
import com.health.entity.PageResult;
import com.health.entity.QueryPageBean;
import com.health.entity.Result;
import com.health.oss.AliyunOSSClientUtils;
import com.health.pojo.TSetmeal;
import com.health.service.SetmealService;
import com.itheima.constant.RedisConstant;

@RestController
@RequestMapping("/setmeal")
public class SetmealController {

	@Reference
	SetmealService setmealService;
	
	

	//文件上传
	@RequestMapping("/upload")
	public String upload(MultipartFile imgFile) throws Exception{
		AliyunOSSClientUtils ali = new AliyunOSSClientUtils();
		String img2Oss = ali.uploadImg2Oss(imgFile);
		String imgUrl = ali.getImgUrl(img2Oss);
		return imgUrl;
	}
	
	@RequestMapping("/add")
	public Result add(@RequestBody TSetmeal setmeal,Integer[] checkgroupIds){
		try {
			setmealService.add(setmeal, checkgroupIds);
			return new Result(true, "success");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			e.printStackTrace();
			return new Result(true, "error");
		}
	}
	
	//分页查询
		@RequestMapping("/findPage")
		public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
			PageResult pageResult = setmealService.findPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize(), queryPageBean.getQueryString());
			return pageResult;
		}
	
}
