package io.vscale.perpenanto.controllers.admin.statistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.vscale.perpenanto.forms.admin.ProductToReservationForm;
import io.vscale.perpenanto.services.interfaces.admin.ProductToReservationAdminService;
import io.vscale.perpenanto.services.interfaces.user.ProductToReservationService;

/**
 * 03.02.2018
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Controller
@RequestMapping("/admin/statistics")
public class ProductToReservationAdminController {

    private final ProductToReservationService productToReservationService;
    private final ProductToReservationAdminService productToReservationAdminService;

    @Autowired
    public ProductToReservationAdminController(ProductToReservationService productToReservationService,
                                               ProductToReservationAdminService productToReservationAdminService) {
        this.productToReservationService = productToReservationService;
        this.productToReservationAdminService = productToReservationAdminService;
    }

    @GetMapping("/products_to_reservations")
    public ModelAndView getProductsToReservations(
                                             @CookieValue(value = "status", defaultValue = "reset") String cookieValue){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/product_to_reservation_admin");
        modelAndView.addObject("reservation_infos",
                this.productToReservationService.getProductsToReservationsByCookie(cookieValue));

        return modelAndView;

    }

    @PostMapping(value = "/products_to_reservations", params = "add_action")
    public ModelAndView saveNewProductToReservation(
            @CookieValue(value = "status", defaultValue = "reset") String cookieValue,
            @ModelAttribute("productToReservationForm")ProductToReservationForm productToReservationForm){

       this.productToReservationAdminService.saveProductToReservation(productToReservationForm);

       ModelAndView modelAndView = new ModelAndView();
       modelAndView.setViewName("admin/product_to_reservation_admin");
       modelAndView.addObject("reservation_infos",
               this.productToReservationService.getProductsToReservationsByCookie(cookieValue));

       return modelAndView;

    }

    @PostMapping(value = "/products_to_reservations", params = "update_action")
    public ModelAndView updateProductToReservation(
            @CookieValue(value = "status", defaultValue = "reset") String cookieValue,
            @ModelAttribute("productToReservationForm")ProductToReservationForm productToReservationForm){

        this.productToReservationAdminService.updateProductToReservation(productToReservationForm);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/product_to_reservation_admin");
        modelAndView.addObject("reservation_infos",
                this.productToReservationService.getProductsToReservationsByCookie(cookieValue));

        return modelAndView;

    }

    @PostMapping(value = "/products_to_reservations", params = "delete_action")
    public ModelAndView deleteProductToReservation(
            @CookieValue(value = "status", defaultValue = "reset") String cookieValue,
            @ModelAttribute("productToReservationForm")ProductToReservationForm productToReservationForm){

        this.productToReservationAdminService.deleteProductToReservation(productToReservationForm);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/product_to_reservation_admin");
        modelAndView.addObject("reservation_infos",
                this.productToReservationService.getProductsToReservationsByCookie(cookieValue));

        return modelAndView;

    }

}
