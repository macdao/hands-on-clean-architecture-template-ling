package com.example.demo.adapter.web;

import com.example.demo.adapter.web.order.adapter.GetOrderAdapter;
import com.example.demo.adapter.web.order.adapter.PlaceOrderAdapter;
import com.example.demo.application.service.PayOrderService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
abstract class ContractTestBase {

    @Autowired
    WebApplicationContext context;

    @MockitoBean
    PayOrderService payOrderService;

    @MockitoBean
    PlaceOrderAdapter placeOrderAdapter;

    @MockitoBean
    GetOrderAdapter getOrderAdapter;

    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.webAppContextSetup(context);
    }
}
