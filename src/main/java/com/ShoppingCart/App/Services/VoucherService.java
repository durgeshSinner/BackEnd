package com.ShoppingCart.App.Services;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ShoppingCart.App.Entities.Offers;
import com.ShoppingCart.App.Entities.Voucher;
import com.ShoppingCart.App.Repositories.VoucherRepository;

import java.text.ParseException;
import java.time.LocalDateTime;

@Component
public class VoucherService {
	
	@Autowired
	private VoucherRepository repository;
	
	public Voucher getVoucher(int userId) {
		return repository.findById(userId).get();
	}
	public Voucher createVoucher (int userId, int offernumber) throws Exception{
		try {
			Voucher voucher = repository.findById(userId).get();
			String Date1 = voucher.getIssuedTime();
		    String[] datearray = Date1.split("/");
		    LocalDateTime date= LocalDateTime.now();
			Integer day = date.getDayOfMonth();
			Integer Month = date.getMonthValue();
			Integer Year = date.getYear();
			String IssuedTime = day.toString()+"/"+Month.toString()+"/" +Year.toString();
			if(Integer.parseInt(datearray[2])<Year) {
				voucher.setIssuedTime(IssuedTime);
				if (0 <= offernumber && offernumber < 45) { voucher.setOffer(Offers.Buy1Get1); }
		        else if (45 <= offernumber && offernumber < 90) {voucher.setOffer(Offers.Buy2Get2); }
		        else if (90 <= offernumber && offernumber < 135) { voucher.setOffer(Offers.Flat30); }
		        else if (135 <= offernumber && offernumber < 225) { voucher.setOffer(Offers.BetterLuckNextTime);  }
		        else if (225 <= offernumber && offernumber < 270) { voucher.setOffer(Offers.Upto50);  }
		        else if (270 <= offernumber && offernumber < 315) { voucher.setOffer(Offers.FreeDelivery);  }
		        else { voucher.setOffer(Offers.Offer);  }
				return repository.save(voucher);
			}
			else{
				if(Integer.parseInt(datearray[1])<Month) {
					voucher.setIssuedTime(IssuedTime);
					if (0 <= offernumber && offernumber < 45) { voucher.setOffer(Offers.Buy1Get1); }
			        else if (45 <= offernumber && offernumber < 90) {voucher.setOffer(Offers.Buy2Get2); }
			        else if (90 <= offernumber && offernumber < 135) { voucher.setOffer(Offers.Flat30); }
			        else if (135 <= offernumber && offernumber < 225) { voucher.setOffer(Offers.BetterLuckNextTime);  }
			        else if (225 <= offernumber && offernumber < 270) { voucher.setOffer(Offers.Upto50);  }
			        else if (270 <= offernumber && offernumber < 315) { voucher.setOffer(Offers.FreeDelivery);  }
			        else{ voucher.setOffer(Offers.Offer);  }
					return repository.save(voucher);
				}
				else{
					if(Integer.parseInt(datearray[0])<day) {
						voucher.setIssuedTime(IssuedTime);
						if (0 <= offernumber && offernumber < 45) { voucher.setOffer(Offers.Buy1Get1); }
				        else if (45 <= offernumber && offernumber < 90) {voucher.setOffer(Offers.Buy2Get2); }
				        else if (90 <= offernumber && offernumber < 135) { voucher.setOffer(Offers.Flat30); }
				        else if (135 <= offernumber && offernumber < 225) { voucher.setOffer(Offers.BetterLuckNextTime);  }
				        else if (225 <= offernumber && offernumber < 270) { voucher.setOffer(Offers.Upto50);  }
				        else if (270 <= offernumber && offernumber < 315) { voucher.setOffer(Offers.FreeDelivery);  }
				        else{ voucher.setOffer(Offers.Offer);  }
						return repository.save(voucher);
					}
					else{
						throw new Exception("user already has a voucher");
					}
				}
			}
		
		}
		catch(NoSuchElementException e) {
			Voucher voucher = new Voucher();
			voucher.setUserId(userId);
			if (0 <= offernumber && offernumber < 45) { voucher.setOffer(Offers.Buy1Get1); }
	        else if (45 <= offernumber && offernumber < 90) {voucher.setOffer(Offers.Buy2Get2); }
	        else if (90 <= offernumber && offernumber < 135) { voucher.setOffer(Offers.Flat30); }
	        else if (135 <= offernumber && offernumber < 225) { voucher.setOffer(Offers.BetterLuckNextTime);  }
	        else if (225 <= offernumber && offernumber < 270) { voucher.setOffer(Offers.Upto50);  }
	        else if (270 <= offernumber && offernumber < 315) { voucher.setOffer(Offers.FreeDelivery);  }
	        else{ voucher.setOffer(Offers.Offer);  }
			return repository.save(voucher);
		}
		catch(ParseException e) {
			System.out.println("Parse Exception");
			throw new Exception("Parse Exception");
		}
		
		
	}
}
