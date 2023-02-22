package com.showroom.springboot.resource;

import com.showroom.springboot.model.*;
import com.showroom.springboot.services.DiscountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/discount")
@Api(value = "Discount Resource", description = "shows discount info")
public class DiscountController {
    @Autowired
    DiscountService discountService;

    @ApiOperation(value = "Returns discounts list")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 100, message = "100 is the message"),
                    @ApiResponse(code = 200, message = "Successful request")
            }
    )
    @GetMapping("/discounts")
    public List<Discount> getDiscounts() {
        return discountService.getDiscountsList();
    }
}