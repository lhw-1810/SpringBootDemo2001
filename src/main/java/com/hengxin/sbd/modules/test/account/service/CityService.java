package com.hengxin.sbd.modules.test.account.service;

import java.util.List;

import com.hengxin.sbd.modules.test.account.entity.City;

public interface CityService {

	List<City> selectCitiesByCountryId(Integer countryId);

	Integer insertCity(City city);
	
}
