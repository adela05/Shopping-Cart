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
        this.localTax(shopping);
        this.importTax(shopping);
    }

     // Price multiply by qty
    public Float priceByQty(Float price, Integer quantity){
        float total = price * quantity;
        return total;
    }
    // Local Tax 10 percent multiply by price on Non-Exempt Items
    public void localTax(List<Shopping> exempt){
        float totalLocalTax = 0;
        double tax = 0;

        for(int i = 0; i < exempt.size(); i++){
            totalLocalTax=this.priceByQty(exempt.get(i).getPrice(), exempt.get(i).getQuantity());
            if(!exempt.equals("Books") || !exempt.equals("Food") || !exempt.equals("Medical Supplies")){
                tax = .10 * totalLocalTax;
                tax = Math.round(tax * 20.0)/20.0;
           }
            else{
                totalLocalTax += tax;
            }
        }
    }
    // Import Tax 5 percent multiply by price on Exempt and all Items.
    public void importTax(List<Shopping> nonExempt){
        float totalImportTax = 0;
        double taxDuty = 0;
        for(int count = 0; count < nonExempt.size(); count++){
            totalImportTax=this.priceByQty(nonExempt.get(count).getPrice(), nonExempt.get(count).getQuantity());
            if(nonExempt.get(count).getDomestic()==false){
                taxDuty = .5 * totalImportTax;
                taxDuty = Math.round(taxDuty * 20.0)/20.0;
            }
            else {
                totalImportTax += taxDuty;
            }
        }
    }
    // Adding both local and import tax
    public void salesTaxes(Float totalLocalTax, Float totalImportTax){
        float totalTaxes = totalLocalTax + totalImportTax;
    }
    // Add totalTaxes with grandTotal
}
