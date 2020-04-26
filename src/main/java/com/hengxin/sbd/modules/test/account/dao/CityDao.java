package com.hengxin.sbd.modules.test.account.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.hengxin.sbd.modules.test.account.entity.City;
import com.hengxin.sbd.modules.test.common.vo.Result;

@Mapper
public interface CityDao {

	@Select("select * from m_city where country_id = #{countryId}")
	List<City> selectCitiesByCountryId(Integer countryId);
	
	@Select("select * from m_city where city_name = #{cityName} and local_city_name = #{localCityName}")   
	City selectCityByName(@Param("cityName") String cityName, @Param("localCityName") String localCityName);
	
	@Select("<script>" + 
			"select * from m_city "
			+ "<where> "
			+ "<if test='cityName != \"\" and cityName != null'>"
			+ "and city_name = #{cityName} "
			+ "</if>"
			+ "<if test='localCityName != \"\" and localCityName != null'>"
			+ "and local_city_name = #{localCityName} "
			+ "</if>"
			+ "</where>"
			+ "limit 1"
			+ "</script>")
	City selectCityByName2(@Param("cityName") String cityName, @Param("localCityName") String localCityName);
	
	@Insert("insert into m_city (city_name, local_city_name, country_id, date_created) "
 			+ "values (#{cityName}, #{localCityName}, #{countryId}, #{dateCreated})")
	@Options(useGeneratedKeys = true, keyColumn = "city_id", keyProperty="cityId")
	Result<City> insertCity(City city);
	
	@Update("update m_city set local_city_name = #{localCityName} where city_id = #{cityId}")
	Result<City> updateCityById(City city);
	
	@Delete("delete from m_city where city_id = #{cityId}")
	Result<City> deleteCityById(Integer cityId);
}
