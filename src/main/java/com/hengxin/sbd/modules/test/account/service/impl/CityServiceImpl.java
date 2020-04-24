package com.hengxin.sbd.modules.test.account.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hengxin.sbd.modules.test.account.dao.CityDao;
import com.hengxin.sbd.modules.test.account.entity.City;
import com.hengxin.sbd.modules.test.account.service.CityService;

@Service
public class CityServiceImpl implements CityService {
	@Autowired
	private CityDao cityDao;
	
	@Override
	public List<City> selectCitiesByCountryId(Integer countryId) {
		return cityDao.selectCitiesByCountryId(countryId);
	}

	@Override
	public Integer insertCity(City city) {
		return cityDao.insertCity(city);
	}

}
