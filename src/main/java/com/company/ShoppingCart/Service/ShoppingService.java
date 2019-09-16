package com.company.ShoppingCart.Service;

import com.company.ShoppingCart.Dao.ShoppingRepository;
import com.company.ShoppingCart.Dto.Receipt;
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
            return shopRepo.save(shopping);
        }
        return null;
    }

    public Integer deleteItem(Integer id){
        shopRepo.deleteById(id);
        return null;
    }
    // This method will call out the allTaxes function to the purchase route.
    public Receipt purchase(List<Shopping> shopping){
        return this.allTaxes(shopping);
    }
    // Receipt Calculations on Taxes
    public Receipt allTaxes(List<Shopping> shopping) {
        Receipt r1 = new Receipt();
        for (int i = 0; i < shopping.size(); i++) {
            Float itemPrice = shopping.get(i).getPrice();
            Integer itemQty = shopping.get(i).getQuantity();
            r1.setSubTotal(itemPrice * itemQty);
            r1.setLocalRate(.10f);
            r1.setImportRate(.05f);
            r1.setTotalImportRate(0f);
            r1.setTotalLocalRate(0f);

            if (itemQty <= 0) {
                throw new InvalidParameterException("Item quantity must be greater than 0");
            }
            // Local Tax 10 percent
           if (!shopping.get(i).getCategory().equalsIgnoreCase("Book") && !shopping.get(i).getCategory().equalsIgnoreCase("Food") && !shopping.get(i).getCategory().equalsIgnoreCase("Medical Supplies")) {

                Float localTaxes = Math.round(r1.getLocalRate() * r1.getSubTotal() * 20.0f) / 20.0f;
                r1.setTotalLocalRate(localTaxes);
            }

            // Import Tax 5 percent
            if (!shopping.get(i).getDomestic()) {

                Float impTaxes = Math.round(r1.getImportRate() * r1.getSubTotal() * 20.0f) / 20.0f;
                r1.setTotalImportRate(impTaxes);
            }
             r1.setTaxTotal(r1.getTotalLocalRate() + r1.getTotalImportRate());
            r1.setGrandTotal(r1.getTaxTotal() + r1.getSubTotal());
        }
           return r1;

    }

}
