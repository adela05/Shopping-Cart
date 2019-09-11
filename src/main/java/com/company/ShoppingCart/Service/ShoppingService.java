package com.company.ShoppingCart.Service;

import com.company.ShoppingCart.Dao.ShoppingRepository;
import com.company.ShoppingCart.Dto.Shopping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

}
