package com.hengxin.sbd.modules.test.account.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hengxin.sbd.modules.test.account.dao.CityDao;
import com.hengxin.sbd.modules.test.account.entity.City;
import com.hengxin.sbd.modules.test.account.service.CityService;
import com.hengxin.sbd.modules.test.common.vo.Result;
import com.hengxin.sbd.modules.test.common.vo.Result.ResultMenu;

@Service
public class CityServiceImpl implements CityService {
	@Autowired
	private CityDao cityDao;

	@Override
	public List<City> selectCitiesByCountryId(Integer countryId) {
		return cityDao.selectCitiesByCountryId(countryId);
	}

	@Override
	public City selectCityByName(String cityName, String localCityName) {
		return cityDao.selectCityByName2(cityName, localCityName);
	}

	@Override
	public Result<City> insertCity(City city) {
		Result<City> result = new Result<City>(ResultMenu.SUCCESS.status, "INSERT SUCCESS");
		try {
			cityDao.insertCity(city);
			result.setObject(city);
		} catch (Exception e) {
			result.setStatus(ResultMenu.FALSE.status);
			result.setMessage(e.getMessage());
		}
		return result;
	}

	@Override
	@Transactional
	public Result<City> updateCityById(City city) {
		Result<City> result = new Result<City>(ResultMenu.SUCCESS.status, "UPDATE SUCCESS");
		cityDao.updateCityById(city);
		result.setObject(city);
		result.setStatus(ResultMenu.FALSE.status);
		return result;
	}

	@Override
	public Result<City> deleteCityById(Integer cityId) {
		Result<City> result = new Result<City>(ResultMenu.SUCCESS.status, "DELETE SUCCESS");
		try {
			cityDao.deleteCityById(cityId);
		} catch (Exception e) {
			result.setStatus(ResultMenu.FALSE.status);
			result.setMessage(e.getMessage());
		}
		return result;
	}

	@Override
	public PageInfo<City> getCitiesByPage(Integer currentPage, Integer pageSize, Integer countryId) {
		PageHelper.startPage(currentPage, pageSize);
		// 准备需要分页的数据
		List<City> cities = cityDao.selectCitiesByCountryId(countryId);
		cities.stream().map(item -> {
			item.setCityName(item.getCityName() + "--");
			return item;
		}).collect(Collectors.toList());
		return new PageInfo<City>(cities);
	}

}
