package com.ShoppingCart.App.Entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.PrePersist;

@Entity
public class Voucher {
	@Id
	private int UserId;
	@Enumerated(EnumType.STRING)
	private Offers Offer;
	private String IssuedTime;
	
	@PrePersist
    public void prePersist() {
		LocalDateTime date= LocalDateTime.now();
		Integer day = date.getDayOfMonth();
		Integer Month = date.getMonthValue();
		Integer Year = date.getYear();
		this.IssuedTime = day.toString()+"/"+Month.toString()+"/" +Year.toString();
		
    }
	public int getUserId() {
		return UserId;
	}
	public void setUserId(int userId) {
		UserId = userId;
	}
	public Offers getOffer() {
		return Offer;
	}
	public void setOffer(Offers offer) {
		Offer = offer;
	}
	public String getIssuedTime() {
		return IssuedTime;
	}
	public void setIssuedTime(String issuedTime) {
		IssuedTime = issuedTime;
	}
	public Voucher(int userId, Offers offer, String issuedTime) {
		super();
		UserId = userId;
		Offer = offer;
		IssuedTime = issuedTime;
	}
	public Voucher() {
		super();
	}
	@Override
	public String toString() {
		return "Voucher [UserId=" + UserId + ", Offer=" + Offer + ", IssuedTime=" + IssuedTime + "]";
	}
	
	
	
}
