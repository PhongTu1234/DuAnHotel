package com.poly.Service;

import java.util.List;

import com.poly.entity.Images_Room;

public interface Images_RoomService {
	List<Images_Room> findAll();

	Images_Room findById(Integer id);

	Images_Room create(Images_Room Images_Room);

	Images_Room update(Images_Room Images_Room);

	void delete(Integer id);
}
