package by.rudenko.imarket;

import by.rudenko.imarket.dto.CouponDTO;
import by.rudenko.imarket.exception.NoSuchIdException;

import java.util.List;


public interface CouponService {
    CouponDTO findById(Long id) throws NoSuchIdException;

    List<CouponDTO> getAllCouponsList(int pageNumber, int pageSize);

    boolean addNewCoupon(CouponDTO couponDTO);

    boolean deleteCoupon(CouponDTO couponDTO);

    boolean update(CouponDTO couponDTO);
}



