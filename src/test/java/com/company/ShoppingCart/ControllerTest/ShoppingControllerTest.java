package com.company.ShoppingCart.ControllerTest;

import com.company.ShoppingCart.Controller.ShoppingController;
import com.company.ShoppingCart.Dto.Shopping;
import com.company.ShoppingCart.Service.ShoppingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ShoppingControllerTest {
    private MockMvc mockMvc;

    @Mock
    ShoppingService mockShopService;
    @InjectMocks
    ShoppingController shopController;

    Shopping s1;
    Shopping s2;

    List<Shopping> shoppingList;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(shopController).build();

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
    public void rootContext_ShouldRespondWith404() throws Exception {
        mockMvc.perform(get("/")).andExpect(status().isNotFound());
    }

    @Test
    public void ShouldReturnAllShopping() throws Exception{
        when(mockShopService.getAllItems()).thenReturn(shoppingList);

        mockMvc.perform(get("/shopping"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name").value(shoppingList.get(0).getName()));

            verify(mockShopService).getAllItems();
    }
    @Test
    public void ShouldAddShopping() throws Exception{
        Shopping newShopping = new Shopping();
        newShopping.setId(1);
        newShopping.setName("Chocolate Bar-Imported");
        newShopping.setPrice(0.89f);
        newShopping.setQuantity(1);
        newShopping.setCategory("Food");
        newShopping.setDomestic(false);
        newShopping.setImgUrl("https://static01.nyt.com/images/2016/11/09/world/09toblerone-web1/09toblerone-web1-articleLarge.jpg?quality=75&auto=webp&disable=upscale");

        when(mockShopService.addItem(newShopping)).thenReturn(newShopping);

        ObjectMapper mapper = new ObjectMapper();
        String objStr = mapper.writeValueAsString(newShopping);

        mockMvc.perform(post("/shopping")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objStr))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(newShopping.getName())).andReturn();

        verify(mockShopService).addItem(newShopping);
    }

    @Test
    public void ShouldDeleteShopping() throws Exception{
        mockMvc.perform(delete("/shopping/1"))
                .andExpect(status().isOk()).andReturn();

        verify(mockShopService).deleteItem(1);
    }

    @Test
    public void ShouldUpdateShopping() throws Exception{
        Shopping newShopping = new Shopping();
        newShopping.setId(2);
        newShopping.setName("Advil - headache pills");
        newShopping.setPrice(8.99f);
        newShopping.setQuantity(2);
        newShopping.setCategory("Medical Supplies");
        newShopping.setDomestic(true);
        newShopping.setImgUrl("https://i5.walmartimages.com/asr/aa60b2ef-2315-4241-9030-0a6901a558e5_1.e1e3c7843c959300be10168838e92dd0.jpeg?odnHeight=450&odnWidth=450&odnBg=FFFFFF");

        ObjectMapper mapper = new ObjectMapper();
        String objStr = mapper.writeValueAsString(newShopping);

        mockMvc.perform(put("/shopping/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objStr))
                .andExpect(status().isOk()).andReturn();

        verify(mockShopService).updateItem(newShopping, 2);
    }
}
