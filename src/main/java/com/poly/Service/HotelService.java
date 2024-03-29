package com.poly.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.poly.dto.RoomSearchDTO;
import com.poly.entity.Hotels;
import com.poly.entity.RoomTypes;
import com.poly.entity.Users;

public interface HotelService {
	List<Hotels> countHotel();
	
	Hotels findById(Integer id);

	List<Hotels> findByHotelTypesId(String cid);

	Hotels create(Hotels product);

	Hotels update(Hotels product);

	void delete(Integer id);

	// List<Hotels> findByHotelTypeHotelLevelBetween(String startLevel, String
	// endLevel);

	List<Hotels> findHotelByHotelLevelstarttoend(Integer start, Integer end, Integer placeId);

	List<Hotels> findHotelByHotelLevel0();

	List<Hotels> findHotelByHotelLevel1();

	List<Hotels> findHotelByHotelLevel2();

	List<Hotels> findHotelByHotelLevel3();

	List<Hotels> findHotelByHotelLevel4();

	List<Hotels> findHotelByHotelLevel5();
	
	List<Hotels> findHotelByHotelLevel(Integer level);
	
//	List<Hotels> findHotelByHotelLevel1();
//	
//	List<Hotels> findHotelByHotelLevel2();
//	
//	List<Hotels> findHotelByHotelLevel3();
//	
//	List<Hotels> findHotelByHotelLevel4();
//	
//	List<Hotels> findHotelByHotelLevel5();
	
	List<Hotels> findCountId();

	List<Hotels> findPage(Integer page);

	List<Hotels> findByPlaceId(Integer Place);

	List<Hotels> findHotelByPlaceid(Integer id, Integer page);

	List<Hotels> findHotelByLevel(Integer level, Integer page);
	
	List<Hotels> findHotelByService(Integer service, Integer page);
	
	List<Hotels> findHotelByPlaceidAndStart(Integer start, Integer placeId, Integer Page);
	
	List<Hotels> findPageAdmin(Integer page, Integer number);
	
	Hotels findByHotelName(String name);
	
	Page<Hotels> findAll(Pageable page);

	Page<Hotels> searchHotels(RoomSearchDTO searchDTO, Pageable page);
	
	Hotels findHotelByRoomId(Integer id);
	
 	Page<Hotels> adfindByPlaceId(Integer hid, Pageable page);
}