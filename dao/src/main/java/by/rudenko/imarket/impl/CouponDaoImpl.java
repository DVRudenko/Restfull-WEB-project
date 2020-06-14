package by.rudenko.imarket.impl;

import by.rudenko.imarket.CouponDao;
import by.rudenko.imarket.model.Coupon;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository
public class CouponDaoImpl extends AbstractDao <Coupon, Long> implements CouponDao {

    private static final Logger LOGGER = LogManager.getLogger("imarketCoupon");

    public CouponDaoImpl() {
        super(Coupon.class);
    }


}

