package com.ddip.dao;

import com.ddip.pojo.entity.DdipDictionaryData;
import java.util.List;

public interface DdipDictionaryDataMapper {
    int deleteByPrimaryKey(String code);

    int insert(DdipDictionaryData record);

    DdipDictionaryData selectByPrimaryKey(String code);

    List<DdipDictionaryData> selectAll();

    int updateByPrimaryKey(DdipDictionaryData record);
}