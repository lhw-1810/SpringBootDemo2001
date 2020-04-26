package com.hengxin.sbd.modules.test.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hengxin.sbd.modules.test.account.entity.Country;
import com.hengxin.sbd.modules.test.account.service.CountryService;

@RestController
@RequestMapping("/countryContrller")
public class CountryContrller {
	@Autowired
	private CountryService countryService;

	/**
	 * https://127.0.0.1/countryContrller/country/522
	 */
	@RequestMapping("/country/{countryId}")
	public Country getCountryById(@PathVariable int countryId) {
		return countryService.getCountryById(countryId);
	}
	
	/**
	 * https://127.0.0.1/countryContrller/country?countryName=China
	 */
	@RequestMapping("/country")
	public Country getCountryByName(@RequestParam String countryName) {
		return countryService.getCountryByName(countryName);
	}
	
}
