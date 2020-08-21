package by.rudenko.imarket.impl;

import by.rudenko.imarket.CouponDao;
import by.rudenko.imarket.CouponService;
import by.rudenko.imarket.ProfileDao;
import by.rudenko.imarket.UserDao;
import by.rudenko.imarket.dto.CouponDTO;
import by.rudenko.imarket.exception.NoSuchIdException;
import by.rudenko.imarket.model.Coupon;
import by.rudenko.imarket.model.Profile;
import by.rudenko.imarket.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CouponServiceImpl implements CouponService {

    private final CouponDao couponDao;
    private final UserDao userDao;
    private final ProfileDao profileDao;
    private final ModelMapper modelMapper;

    public CouponServiceImpl(CouponDao couponDao, UserDao userDao, ProfileDao profileDao, ModelMapper modelMapper) {
        this.couponDao = couponDao;
        this.userDao = userDao;
        this.profileDao = profileDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean addNewCoupon(CouponDTO couponDTO) {
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

        return couponDao.getAll(pageNumber, pageSize, null).stream()
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
        return couponDao.getCount();
    }

    //используем купон для пополнения баланса
    @Override
    public void useCoupon(CouponDTO couponDTO) {
        final Coupon coupon = modelMapper.map(couponDTO, Coupon.class);
        final User user = coupon.getUser();
        final Profile profile = user.getProfile();
        int newBalance = profile.getMoneyBalance() + coupon.getDiscount(); //обновляем баланс
        profile.setMoneyBalance(newBalance);
        profileDao.update(profile); //сохраняем новый профиль
        couponDao.delete(coupon);//удаляем использованный купон

    }
}
