package com.company.ShoppingCart.Controller;

import com.company.ShoppingCart.Dto.Receipt;
import com.company.ShoppingCart.Dto.Shopping;
import com.company.ShoppingCart.Service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ShoppingController {
    @Autowired
    private ShoppingService service;

    // GET
    @RequestMapping(value = "/shopping", method = RequestMethod.GET)
    public List<Shopping> getAllItems(){
        return service.getAllItems();
    }
    // POST
    @RequestMapping(value = "/shopping", method = RequestMethod.POST)
    public Shopping addItem(@RequestBody @Valid Shopping shopping){
        service.addItem(shopping);
        return shopping;
    }

    // PUT
    @RequestMapping(value = "/shopping/{id}", method = RequestMethod.PUT)
    public void updateItem(@RequestBody @Valid Shopping shopping, @PathVariable Integer id){
        service.updateItem(shopping, id);
    }

    // DELETE
    @RequestMapping(value = "/shopping/{id}", method = RequestMethod.DELETE)
    public Integer deleteItem(@PathVariable Integer id){
        return service.deleteItem(id);
    }

    // PURCHASE
    @RequestMapping(value = "/purchase", method = RequestMethod.POST)
    public Receipt purchase(@RequestBody @Valid List<Shopping> shopping){
        return service.purchase(shopping);
    }

}
