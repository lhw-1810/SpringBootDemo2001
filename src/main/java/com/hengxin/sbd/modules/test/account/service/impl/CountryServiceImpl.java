package com.hengxin.sbd.modules.test.account.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hengxin.sbd.modules.test.account.dao.CountryDao;
import com.hengxin.sbd.modules.test.account.entity.Country;
import com.hengxin.sbd.modules.test.account.service.CountryService;

@Service
public class CountryServiceImpl implements CountryService {
	@Autowired
	private CountryDao countryDao;

	@Override
	public Country getCountryById(Integer countryId) {
		return countryDao.getCountryById(countryId);
	}

	@Override
	public Country getCountryByName(String countryName) {
		return countryDao.getCountryByName(countryName);
	}

}
