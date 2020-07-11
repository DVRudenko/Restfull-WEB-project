package by.rudenko.imarket;

import by.rudenko.imarket.dto.CouponDTO;
import by.rudenko.imarket.exception.NoSuchIdException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coupons")
public class CouponController {

    private final CouponService couponService;

    public CouponController(final CouponService couponService) {
        this.couponService = couponService;
    }

    //тип Get /coupons/ - получить весь список с пагинацией
    @GetMapping
    public List<CouponDTO> getAllCoupons(
            @RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return couponService.getAllCouponsList(pageNumber, pageSize);
    }

    //тип Get /guests/count - получить количество строк в таблице (пользователей)
    @GetMapping(value = "/count")
    public Long getUsersCount() {
        return couponService.entityCount();
    }

    //тип Get /guests/id - получить сущность по Id
    @GetMapping(value = "/{id}")
    public CouponDTO getAllCoupons(@PathVariable(value = "id") Long id) throws NoSuchIdException {
        return couponService.findById(id);
    }

    //тип Post /coupons/JSON добавить новую запись
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> addNewCoupon(@RequestBody CouponDTO couponDTO) {
        couponService.addNewCoupon(couponDTO);
        return ResponseEntity.ok("coupon saved");
    }

    //тип Post /coupons/JSON добавить новую запись
    @PostMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> useCoupon(@RequestBody CouponDTO couponDTO) {
        couponService.useCoupon(couponDTO);
        return ResponseEntity.ok("coupon used successfully");
    }


    //тип Delete /rooms/id удалить запись по Id
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCoupon(@PathVariable(value = "id") Long id) throws NoSuchIdException {

        couponService.deleteCoupon(couponService.findById(id));
    }

    //тип Put /coupons/JSON обновить запись
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateCoupon(@RequestBody CouponDTO couponDTO) {
        couponService.update(couponDTO);
    }

}
