package com.ShoppingCart.App.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.ShoppingCart.App.Entities.Voucher;

@Component
public interface VoucherRepository extends JpaRepository<Voucher, Integer> {

}
