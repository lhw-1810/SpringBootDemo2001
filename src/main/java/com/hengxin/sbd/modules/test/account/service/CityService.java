package com.hengxin.sbd.modules.test.account.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.hengxin.sbd.modules.test.account.entity.City;
import com.hengxin.sbd.modules.test.common.vo.Result;

public interface CityService {

	List<City> selectCitiesByCountryId(Integer Integer);
	
	City selectCityByName(String cityName, String localCityName);

	Result<City> insertCity(City city);

	PageInfo<City> getCitiesByPage(Integer currentPage, Integer pageSize, Integer countryId);

	Result<City> updateCityById(City city);

	Result<City> deleteCityById(Integer cityId);
}
