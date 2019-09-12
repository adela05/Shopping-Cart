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

    public void purchase(List<Shopping> shopping){
        this.salesTax(shopping);
    }

     // Price multiply by qty
    public Float priceByQty(Float price, Integer quantity){
        float total = price * quantity;
        return total;
    }
    // Sales Tax 10 percent multiply by price on Non-Exempt Items
    public void salesTax(List<Shopping> exempt){
        float total = 0;
        double tax = 0;

        for(int i = 0; i < exempt.size(); i++){
            total=this.priceByQty(exempt.get(i).getPrice(), exempt.get(i).getQuantity());
            if(!exempt.equals("Books") || !exempt.equals("Food") || !exempt.equals("Medical Supplies")){
                tax = .10 * total;
                tax = Math.round(tax * 20.0)/20.0;
            } else{
                total += tax;
            }
        }

  }
}
