package com.hengxin.sbd.modules.test.account.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.hengxin.sbd.modules.test.account.entity.City;
import com.hengxin.sbd.modules.test.account.service.CityService;
import com.hengxin.sbd.modules.test.common.vo.Result;

@RestController
@RequestMapping("/cityController")
public class CityController {
	@Autowired
	private CityService cityService;

	/**
	 * 127.0.0.1/cityController/cities/522
	 */
	@RequestMapping("/cities/{countryId}")
	public List<City> selectCitiesByCountryId(@PathVariable int countryId) {
		return cityService.selectCitiesByCountryId(countryId);
	}
	
	/**
	 * 127.0.0.1/cityController/city?cityName=Huangyan&localCityName=贵阳市
	 */
	@GetMapping("/city")
	public City selectCityByName(@RequestParam String cityName, @RequestParam String localCityName) {
		return  cityService.selectCityByName(cityName, localCityName);
	}

	/**
	 * 127.0.0.1/cityController/cities?currentPage=1&pageSize=6&countryId=522
	 */
	@RequestMapping("/cities")
	public PageInfo<City> getCitiesByPage(@RequestParam int currentPage, @RequestParam int pageSize,
			@RequestParam int countryId) {
		return cityService.getCitiesByPage(currentPage, pageSize, countryId);
	}

	/**
	 * 127.0.0.1/cityController/city
	 * {"cityId": "1", "cityName": "guizhou","countryId": "522","dateCreated":
	 * "2019-08-27 22:38:40","localCityName": "ADC"}
	 */
	@PostMapping(value = "/city", consumes = "application/json")
	public Result<City> insertCity(@RequestBody City city) {
		return cityService.insertCity(city);
	}
	
	@PutMapping(value = "/city", consumes = "application/x-www-form-urlencoded")
	public Result<City> updateCityById(@ModelAttribute City city) {
		return cityService.updateCityById(city);
	}
	
	@DeleteMapping(value = "/city/{cityId}")
	public Result<City> deleteCityById(@PathVariable int cityId) {
		return cityService.deleteCityById(cityId);
	}
}
