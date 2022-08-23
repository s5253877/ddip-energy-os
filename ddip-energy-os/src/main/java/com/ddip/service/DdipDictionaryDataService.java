package com.ddip.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddip.dao.DdipDictionaryDataMapper;
import com.ddip.pojo.entity.DdipDictionaryData;

@Service
public class DdipDictionaryDataService {

	@Autowired
	DdipDictionaryDataMapper ddipDictionaryDataMapper;
	
	public List<DdipDictionaryData> selectAll(){
		return ddipDictionaryDataMapper.selectAll();
	}
}
