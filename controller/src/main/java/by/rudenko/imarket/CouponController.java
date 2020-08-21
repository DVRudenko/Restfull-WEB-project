package by.rudenko.imarket;

import by.rudenko.imarket.dto.CouponDTO;
import by.rudenko.imarket.exception.NoSuchIdException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for coupons requests (get, post, delete, etc.)
 *
 * @author Dmitry Rudenko
 * @version 1.0
 */
@RestController
@RequestMapping("/coupons")
public class CouponController {

    private final CouponService couponService;

    public CouponController(final CouponService couponService) {
        this.couponService = couponService;
    }

    @GetMapping
    public List<CouponDTO> getAllCoupons(
            @RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return couponService.getAllCouponsList(pageNumber, pageSize);
    }

    @GetMapping(value = "/count")
    public Long getUsersCount() {
        return couponService.entityCount();
    }

    @GetMapping(value = "/{id}")
    public CouponDTO getAllCoupons(@PathVariable(value = "id") Long id) throws NoSuchIdException {
        return couponService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> addNewCoupon(@RequestBody CouponDTO couponDTO) {
        couponService.addNewCoupon(couponDTO);
        return ResponseEntity.ok("coupon saved");
    }

    @PostMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> useCoupon(@RequestBody CouponDTO couponDTO) {
        couponService.useCoupon(couponDTO);
        return ResponseEntity.ok("coupon used successfully");
    }


    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCoupon(@PathVariable(value = "id") Long id) throws NoSuchIdException {

        couponService.deleteCoupon(couponService.findById(id));
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateCoupon(@RequestBody CouponDTO couponDTO) {
        couponService.update(couponDTO);
    }

}
