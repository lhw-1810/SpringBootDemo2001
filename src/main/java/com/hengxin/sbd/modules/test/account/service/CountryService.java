package com.hengxin.sbd.modules.test.account.service;

import com.hengxin.sbd.modules.test.account.entity.Country;

public interface CountryService {

	Country getCountryById(Integer countryId);

	Country getCountryByName(String countryName);

}
