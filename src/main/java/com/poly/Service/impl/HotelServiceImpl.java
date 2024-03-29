package com.poly.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.poly.DAO.HotelDAO;
import com.poly.Service.HotelService;
import com.poly.dto.RoomSearchDTO;
import com.poly.entity.Hotels;

@Service
public class HotelServiceImpl implements HotelService {
	@Autowired
	HotelDAO hdao;

	@Override
	public Page<Hotels> findAll(Pageable page) {
		return hdao.findAll(page);
	}

	@Override
	public Hotels findById(Integer id) {
		return hdao.findById(id).get();
	}

	@Override
	public List<Hotels> findByHotelTypesId(String cid) {
		return null;
	}

	@Override
	public Hotels create(Hotels Hotels) {
		return hdao.save(Hotels);
	}

	@Override
	public Hotels update(Hotels Hotels) {
		return hdao.save(Hotels);
	}

	@Override
	public void delete(Integer id) {
		hdao.deleteById(id);
	}

//	@Override
//	public List<Hotels> findByHotelTypeHotelLevelBetween(String startLevel, String endLevel) {
//		return hdao.findByHotelTypeHotelLevelBetween(endLevel, endLevel);
//	}

	@Override
	public List<Hotels> findHotelByHotelLevel(Integer level) {
		return hdao.findHotelByHotelLevel(level);
	}

//	@Override
//	public List<Hotels> findHotelByHotelLevel1() {
//		return hdao.findHotelByHotelLevel1();
//	}
//
//	@Override
//	public List<Hotels> findHotelByHotelLevel2() {
//		return hdao.findHotelByHotelLevel2();
//	}
//
//	@Override
//	public List<Hotels> findHotelByHotelLevel3() {
//		return hdao.findHotelByHotelLevel3();
//	}
//
//	@Override
//	public List<Hotels> findHotelByHotelLevel4() {
//		return hdao.findHotelByHotelLevel4();
//	}
//
//	@Override
//	public List<Hotels> findHotelByHotelLevel5() {
//		return hdao.findHotelByHotelLevel5();
//	}

	@Override
	public List<Hotels> findCountId() {
		return null;
	}

	@Override
	public List<Hotels> findPage(Integer page) {
		return null;
	}

	@Override
	public List<Hotels> findByPlaceId(Integer Place) {
		return hdao.findByPlaceId(Place);
	}

	@Override
	public List<Hotels> findHotelByPlaceid(Integer id, Integer page) {
		return null;
	}

	@Override
	public List<Hotels> findHotelByLevel(Integer level, Integer page) {
		return hdao.findHotelByLevel(level, page);
	}
	
	@Override
	public List<Hotels> findHotelByHotelLevelstarttoend(Integer start, Integer end, Integer placeId) {
		return hdao.findHotelByHotelLevelstarttoend(start, end, placeId);
	}

	@Override
	public List<Hotels> findHotelByPlaceidAndStart(Integer start, Integer placeId, Integer Page) {
		return hdao.findHotelByPlaceidAndStart(start, placeId, Page);
	}


	@Override
	public List<Hotels> findHotelByService(Integer service, Integer page) {
		return hdao.findHotelByService(service, page);
	}

	@Override
	public List<Hotels> findHotelByHotelLevel0() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Hotels> findHotelByHotelLevel1() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Hotels> findHotelByHotelLevel2() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Hotels> findHotelByHotelLevel3() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Hotels> findHotelByHotelLevel4() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Hotels> findHotelByHotelLevel5() {
		// TODO Auto-generated method stub
		return hdao.findHotelByHotelLevel(null);
	}

	@Override
	public List<Hotels> findPageAdmin(Integer page, Integer number) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Hotels findByHotelName(String name) {
		// TODO Auto-generated method stub
		return hdao.findByHotelName(name);
	}



	@Override
	public List<Hotels> countHotel() {
		// TODO Auto-generated method stub
		return hdao.countHotel();
	}
	
	@Override
    public Page<Hotels> searchHotels(RoomSearchDTO searchDTO, Pageable page ) {
        return hdao.findHotelsBySearchCriteria(
                searchDTO.getServiceIds(),
                searchDTO.getRoomTypeIds(),
                searchDTO.getMinPrice(),
                searchDTO.getMaxPrice(),
                page
        );
    }

	@Override
	public Hotels findHotelByRoomId(Integer id) {
		return hdao.findHotelByRoomId(id);
	}

	@Override
	public Page<Hotels> adfindByPlaceId(Integer hid, Pageable page) {
		// TODO Auto-generated method stub
		return hdao.adfindByPlaceId(hid, page);
	}

}
