package com.company.ShoppingCart.ServiceTest;

import com.company.ShoppingCart.Dao.ShoppingRepository;
import com.company.ShoppingCart.Dto.Shopping;
import com.company.ShoppingCart.Service.ShoppingService;
import jdk.internal.module.ModuleBootstrap;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ShoppingServiceTest {
    @Mock
    @Autowired
    ShoppingRepository shopRepoMock;

    @InjectMocks
    ShoppingService service;
    Shopping s1;
    Shopping s2;

    List<Shopping> shoppingList;
    @Before
    public void setUp(){
        s1 = new Shopping();
        s1.setId(1);
        s1.setName("Chocolate Bar-Imported");
        s1.setPrice(0.89f);
        s1.setQuantity(1);
        s1.setCategory("Food");
        s1.setDomestic(false);
        s1.setImgUrl("https://static01.nyt.com/images/2016/11/09/world/09toblerone-web1/09toblerone-web1-articleLarge.jpg?quality=75&auto=webp&disable=upscale");

        s2 = new Shopping();
        s2.setId(2);
        s2.setName("Advil - headache pills");
        s2.setPrice(8.99f);
        s2.setQuantity(2);
        s2.setCategory("Medical Supplies");
        s2.setDomestic(true);
        s2.setImgUrl("https://i5.walmartimages.com/asr/aa60b2ef-2315-4241-9030-0a6901a558e5_1.e1e3c7843c959300be10168838e92dd0.jpeg?odnHeight=450&odnWidth=450&odnBg=FFFFFF");

        shoppingList = Arrays.asList(s1,s2);
    }

        @Test
        public void shouldGetAllShopping(){
            when(shopRepoMock.findAll()).thenReturn(shoppingList);
            assertEquals(shoppingList, service.getAllItems());
        }

        @Test
        public void shouldAddShopping(){
        when(shopRepoMock.save(s1)).thenReturn(s1);
        assertEquals(s1, service.addItem(s1));
        }
        @Test
        public void shouldUpdateShopping(){
        when(shopRepoMock.save(s1)).thenReturn(s1);
        assertEquals(s1, service.updateItem(s1, 1));
        }
}
