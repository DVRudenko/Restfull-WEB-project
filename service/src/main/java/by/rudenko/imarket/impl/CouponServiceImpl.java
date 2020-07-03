package by.rudenko.imarket.impl;

import by.rudenko.imarket.CouponDao;
import by.rudenko.imarket.CouponService;
import by.rudenko.imarket.dto.CouponDTO;
import by.rudenko.imarket.exception.NoSuchIdException;
import by.rudenko.imarket.model.Coupon;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CouponServiceImpl implements CouponService {

    @Autowired
    private final CouponDao couponDao;
    private final ModelMapper modelMapper;

    public CouponServiceImpl(CouponDao couponDao, ModelMapper modelMapper) {
        this.couponDao = couponDao;
        this.modelMapper = modelMapper;
    }


    @Override
    public boolean addNewCoupon(CouponDTO couponDTO) {
        // маппинг из ДТО в  Entity
        final Coupon coupon = modelMapper.map(couponDTO, Coupon.class);
        couponDao.save(coupon);
        return true;
    }


    @Override
    public CouponDTO findById(Long id) throws NoSuchIdException {
        final Coupon couponEntity = couponDao.findByID(id);

        return modelMapper.map(couponEntity, CouponDTO.class);
    }

    @Override
    public List<CouponDTO> getAllCouponsList(int pageNumber, int pageSize) {

        return couponDao.getAll(pageNumber, pageSize).stream()
                .map(x -> modelMapper.map(x, CouponDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteCoupon(CouponDTO couponDTO) {

        final Coupon coupon = modelMapper.map(couponDTO, Coupon.class);
        couponDao.delete(coupon);
        return true;
    }

    @Override
    public boolean update(CouponDTO couponDTO) {
        final Coupon coupon = modelMapper.map(couponDTO, Coupon.class);
        couponDao.update(coupon);
        return true;
    }

    @Override
    public Long entityCount() {
        return couponDao.entityCount();
    }
}
