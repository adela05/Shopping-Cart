package com.company.ShoppingCart.Service;

import com.company.ShoppingCart.Dao.ShoppingRepository;
import com.company.ShoppingCart.Dto.Shopping;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ShoppingService {
    @Autowired
    ShoppingRepository shopRepo;

    public List<Shopping>getAllItems(){
        return shopRepo.findAll();
    }

    public Shopping addItem(Shopping shopping){
        shopRepo.save(shopping);
        return shopping;
    }

    public Shopping updateItem(Shopping shopping, Integer id){
        if(shopping.getId() == id){
            shopRepo.save(shopping);
        }
        return null;
    }

    public Integer deleteItem(Integer id){
        shopRepo.deleteById(id);
        return null;
    }

    // Price multiply by qty
//    public void priceByQty(Float price, Integer quantity){
//        price = price * quantity;
//    }
    // Sales Tax 10 percent multiply by price on Non-Exempt Items
//    public void salesTax(List<Shopping> exempt){
//        //float price = 0; don't need to add this
//        float total = 0;
//        double tax = 0;
//        for(int i = 0; i < exempt.size(); i++){
//            //get the price (i) for each item
//            if(!exempt.equals("Books") || !exempt.equals("Food") || !exempt.equals("Medical Supplies")){
//                tax = .10 * price / 100;
//                tax = Math.round(tax * 20.0)/20.0;
//            } else{
//                total += tax;
//            }
//        }

  //  }

}
