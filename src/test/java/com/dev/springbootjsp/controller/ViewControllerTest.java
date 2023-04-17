package com.dev.springbootjsp.controller;

import com.dev.springbootjsp.builder.ProductBuilder;
import com.dev.springbootjsp.domain.ProductRequest;
import com.dev.springbootjsp.domain.ProductResponse;
import com.dev.springbootjsp.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class ViewControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    private ViewController controller;

    @Mock
    private ProductService productService;

    private ProductResponse productResponse;

    private ProductRequest productRequest;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        this.productResponse = ProductBuilder.buildProductResponse();
        this.productRequest = ProductBuilder.buildProductRequest();
    }

    @Test
    public void shouldListProducts() throws Exception {
        when(productService.listProducts()).thenReturn(List.of(productResponse));

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("products"))
                .andExpect(model().attributeExists("products"))
                .andExpect(model().attribute("products", List.of(productResponse)));
    }

    @Test
    public void shouldShowProductRegistrationScreen() throws Exception {
        mockMvc.perform(get("/add-product"))
                .andExpect(status().isOk())
                .andExpect(view().name("save-product"))
                .andExpect(model().attributeExists("create"))
                .andExpect(model().attribute("create", true));
    }

    @Test
    public void shouldShowProductEditScreen() throws Exception {
        when(productService.getProduct(Long.valueOf(1))).thenReturn(productResponse);
        mockMvc.perform(get("/edit-product/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("save-product"))
                .andExpect(model().attributeExists("create"))
                .andExpect(model().attributeExists("product"))
                .andExpect(model().attribute("create", false))
                .andExpect(model().attribute("product", productResponse));
    }

    @Test
    public void shouldDeleteProduct() throws Exception {
        mockMvc.perform(get("/delete/1"))
                .andExpect(status().isFound())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        verify(productService, times(1)).deleteProduct(Long.valueOf(1));
    }

    @Test
    public void shouldEditProduct() throws Exception {
        when(productService.updateProduct(Long.valueOf(1), productRequest)).thenReturn(productResponse);
        mockMvc.perform(post("/edit/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(productRequest)))
                .andExpect(status().isFound())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    public void shouldAddProduct() throws Exception {
        when(productService.createProduct(productRequest)).thenReturn(productResponse);
        mockMvc.perform(post("/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(productRequest)))
                .andExpect(status().isFound())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }
}
