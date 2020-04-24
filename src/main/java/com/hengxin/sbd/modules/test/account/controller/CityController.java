package com.hengxin.sbd.modules.test.account.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hengxin.sbd.modules.test.account.entity.City;
import com.hengxin.sbd.modules.test.account.service.CityService;

@RestController
@RequestMapping("/cityController")
public class CityController {
	@Autowired
	private CityService cityService;

	/**
	 * https://127.0.0.1/cityController/cities/522
	 */
	@RequestMapping("/cities/{countryId}")
	public List<City> selectCitiesByCountryId(@PathVariable Integer countryId) {
		return cityService.selectCitiesByCountryId(countryId);
	}
	
	@PostMapping(value = "/city" , consumes = "application/json")
	public Integer insertCity(@RequestBody City city){
		return  cityService.insertCity(city);
	}
}
