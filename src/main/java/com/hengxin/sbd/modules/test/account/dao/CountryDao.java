package com.hengxin.sbd.modules.test.account.dao;

import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.hengxin.sbd.modules.test.account.entity.Country;

@Mapper
public interface CountryDao {

	@Select("select * from m_country where country_id = #{countryId}")
	@Results(id = "countryInfo" ,value = {
			@Result(column = "country_id", property = "countryId"),
			@Result(column = "country_id", property = "countryId",
					javaType = List.class,
					many = @Many(select = "com.hengxin.sbd.modules.test.account.dao.CityDao.selectCitiesByCountryId")
					)
	})
	Country getCountryById(Integer countryId);
	
	@Select("select * from m_country where country_name = #{countryName}")
	@ResultMap(value = "countryInfo")
	Country getCountryByName(String countryName);
}
