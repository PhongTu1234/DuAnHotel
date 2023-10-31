package com.poly.Service;

import java.util.List;

import com.poly.entity.Hotels;

public interface HotelService {
	List<Hotels> findAll();

	Hotels findById(Integer id);

	List<Hotels> findByHotelTypesId(String cid);

	Hotels create(Hotels product);

	Hotels update(Hotels product);

	void delete(Integer id);
	
	//List<Hotels> findByHotelTypeHotelLevelBetween(String startLevel, String endLevel);
	
	List<Hotels> findHotelByHotelLevel0to1();
	
	List<Hotels> findHotelByHotelLevel1to2();
	
	List<Hotels> findHotelByHotelLevel2to3();
	
	List<Hotels> findHotelByHotelLevel3to4();
	
	List<Hotels> findHotelByHotelLevel4to5();
	
	List<Hotels> findHotelByHotelLevel5();
	
	List<Hotels> findCountId();

}