package com.poly.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class RoomSearchDTO {

	private List<Integer> serviceIds;
    private List<Integer> roomTypeIds;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
	public List<Integer> getServiceIds() {
		return serviceIds;
	}
	public void setServiceIds(List<Integer> serviceIds) {
		this.serviceIds = serviceIds;
	}
	public List<Integer> getRoomTypeIds() {
		return roomTypeIds;
	}
	public void setRoomTypeIds(List<Integer> roomTypeIds) {
		this.roomTypeIds = roomTypeIds;
	}
	public BigDecimal getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(BigDecimal minPrice) {
		this.minPrice = minPrice;
	}
	public BigDecimal getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(BigDecimal maxPrice) {
		this.maxPrice = maxPrice;
	}
}
