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

    public void purchase(List<Shopping> shopping){
        this.allTaxes(shopping);
    }
    // Try to use Shopping as an Array (it turned my getPrice() red)
    public Shopping allTaxes(Shopping[] shopping){
        Float itemPrice = shopping.getPrice();
        Integer itemQty = shopping.getQuantity();
        Float subTotal = itemPrice * itemQty;
        Double localRate;
        Double importRate;
        Double totalLocalRate;
        Double totalImportRate;
        Double taxTotal = totalLocalRate + totalImportRate;

        if(itemQty <= 0){
            throw new InvalidParameterException("Item quantity must be greater than 0");
        }
        // Local Tax 10 percent
        List<Shopping> exempt = new Shopping[];
        for(int i = 0; i < exempt.size(); i++) {
            if(!exempt.equals("Books") || !exempt.equals("Food") || !exempt.equals("Medical Supplies")){
                localRate = .10 * subTotal;
                localRate = Math.round(localRate * 20.0) /20.0;
                totalLocalRate += localRate;
            }
        }
        // Import Tax 5 percent
        List<Shopping> nonExempt = new Shopping[];
        for(int count = 0; count < nonExempt.size(); count++){
            if(!nonExempt.get(count).getDomestic()){
                importRate = .5 * subTotal;
                importRate = Math.round(importRate * 20.0) /20.0;
                totalImportRate += importRate;
            }
        }
        // Adding both tax rates with subTotal
        public Float grandTotal(){
            double sum = taxTotal + subTotal;
        }
    }


}
