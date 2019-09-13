package com.company.ShoppingCart.DaoTest;

import com.company.ShoppingCart.Dao.ShoppingRepository;
import com.company.ShoppingCart.Dto.Shopping;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShoppingDaoTest {
    @Autowired
    ShoppingRepository shopRepo;

    Shopping s1;
    Shopping s2;
    @Before
    public void setUp(){
        shopRepo.deleteAll();

        s1 = new Shopping();
        s1.setName("Country Story");
        s1.setPrice(12.99f);
        s1.setQuantity(1);
        s1.setCategory("Book");
        s1.setDomestic(true);
        s1.setImgUrl("https://tribecapac.org/wp-content/uploads/2015/10/charlottes-web.jpg");

        s2 = new Shopping();
        s2.setName("Bottle of Perfume");
        s2.setPrice(27.89f);
        s2.setQuantity(1);
        s2.setCategory("Lux");
        s2.setDomestic(false);
        s2.setImgUrl("https://ak0.scstatic.net/1/cdn2-cont12.sweetcouch.com/153571069418709745-giorgioarmani-armani-perfume-man.jpg");

    }
    @Test
    @Transactional
    public void shouldGetShopping(){
        shopRepo.save(s1);
        shopRepo.save(s2);

        List<Shopping> shoppingList = new ArrayList<>();
        shoppingList.add(s1);
        shoppingList.add(s2);

        List<Shopping> fromRepo = shopRepo.findAll();
        assertEquals(shoppingList, fromRepo);
    }
    @Test
    @Transactional
    public void shouldGetShoppingById(){
        Shopping addedShopping = shopRepo.save(s1);
        int id = addedShopping.getId();

        Shopping fromRepo = shopRepo.getOne(id);
        assertEquals(addedShopping, fromRepo);
    }

    @Test
    @Transactional
    public void shouldAddShopping(){
        shopRepo.save(s1);
        shopRepo.save(s2);

        assertNotNull(s1.getId());
        assertNotNull(s2.getId());
    }

    @Test
    @Transactional
    public void shouldUpdateShopping(){
        Shopping addedShopping = shopRepo.save(s1);
        addedShopping.setName("Chicken");

        shopRepo.save(addedShopping);

        List<Shopping> fromRepo = shopRepo.findAll();

        assertEquals(fromRepo.size(), 1);
        assertEquals(fromRepo.get(0).getName(), "Chicken");
    }

    @Test
    @Transactional
    public void shouldDeleteShopping(){
        Shopping addedShopping = shopRepo.save(s1);

        shopRepo.deleteById(addedShopping.getId());
        List<Shopping> fromRepo = shopRepo.findAll();

        assertEquals(fromRepo.size(), 0);
    }
}
