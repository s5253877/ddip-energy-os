package com.ddip.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ddip.common.response.ResultModel;
import com.ddip.pojo.entity.DdipDictionaryData;
import com.ddip.service.DdipDictionaryDataService;

@Controller
public class TestController {

	@Autowired
	DdipDictionaryDataService ddipDictionaryDataService;
	
	@GetMapping("/web/login")
	@ResponseBody
	public ResultModel<List<DdipDictionaryData>> selectAll() {

		
		
		List<DdipDictionaryData> list = ddipDictionaryDataService.selectAll();
		return ResultModel.success(list);
	}
}
