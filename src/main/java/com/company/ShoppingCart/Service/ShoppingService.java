package com.company.ShoppingCart.Service;

import com.company.ShoppingCart.Dao.ShoppingRepository;
import com.company.ShoppingCart.Dto.Shopping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.InvalidParameterException;
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

    public Float purchase(List<Shopping> shopping){
        return this.allTaxes(shopping);
    }
    // Try to use Shopping as an Array (it turned my getPrice() red)
    public Float allTaxes(List<Shopping> shopping) {
        float grandTotal = 0.0f;
        for (int i = 0; i < shopping.size(); i++) {
            Float itemPrice = shopping.get(i).getPrice();
            Integer itemQty = shopping.get(i).getQuantity();
            Float subTotal = itemPrice * itemQty;
            Float itemTotal = 0.0f;
            Float localRate;
            Float importRate;
            Float totalLocalRate = 0.0f;
            Float totalImportRate = 0.0f;

            if (itemQty <= 0) {
                throw new InvalidParameterException("Item quantity must be greater than 0");
            }
            // Local Tax 10 percent
           if (!shopping.get(i).getCategory().equals("Books") || !shopping.get(i).getCategory().equals("Food") || !shopping.get(i).getCategory().equals("Medical Supplies")) {
                localRate = .10f * subTotal;
                localRate = Math.round(localRate * 20.0f) / 20.0f;
                totalLocalRate += localRate;
            } //else if {}

            // Import Tax 5 percent
            if (!shopping.get(i).getDomestic()) {
                importRate = .5f * subTotal;
                importRate = Math.round(importRate * 20.0f) / 20.0f;
                totalImportRate += importRate;
            }
             float taxTotal = totalLocalRate + totalImportRate;
            itemTotal = taxTotal + subTotal;
            grandTotal += itemTotal;

        }
       return grandTotal;

    }

}
