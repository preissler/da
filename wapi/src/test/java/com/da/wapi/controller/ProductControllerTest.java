package com.da.wapi.controller;


import com.da.common.model.json.MetaDataJSON;
import com.da.common.model.json.PricingInformationJSON;
import com.da.common.model.json.ProductDescriptionJSON;
import com.da.common.model.json.ProductJSON;
import com.da.wapi.exception.EmptyFieldError;
import com.da.wapi.exception.WrongIdFormatError;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.DisabledIf;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ProductControllerTest {
    @LocalServerPort
    public int port;
    @Autowired
    public TestRestTemplate restTemplate;
    String productUpdateURL = "/api/v1/update";




   @MockBean
   RabbitAdmin rabbitAdmin;
   @MockBean
   RabbitTemplate rabbitTemplate;


    MockMvc mockMvc;

    @Autowired
    WebApplicationContext context;


    @Autowired
    FilterChainProxy springSecurityFilterChain;



    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).addFilters(springSecurityFilterChain).build();
    }



    private ProductJSON fakeRequest(){
        MetaDataJSON metaDataJSON = new MetaDataJSON("title","site_name","description",
                "keywords", "canonical");
        PricingInformationJSON pricingInformationJSON = new PricingInformationJSON( new BigDecimal("10.00"),
                new BigDecimal("9.00"), new BigDecimal("10.00"));
        ProductDescriptionJSON productDescriptionJSON = new ProductDescriptionJSON("title","subtitle",
                "text");

        return new ProductJSON(null,"name","model","product_type",metaDataJSON,
                pricingInformationJSON, productDescriptionJSON);
    }

    private ProductJSON fakeRequestNegative(){
        MetaDataJSON metaDataJSON = new MetaDataJSON("title","site_name","description",
                "keywords", "canonical");
        PricingInformationJSON pricingInformationJSON = new PricingInformationJSON( new BigDecimal("10.00"),
                new BigDecimal("-9.00"), new BigDecimal("10.00"));
        ProductDescriptionJSON productDescriptionJSON = new ProductDescriptionJSON("title","subtitle",
                "text");

        return new ProductJSON(null,"name","model","product_type",metaDataJSON,
                pricingInformationJSON, productDescriptionJSON);
    }


    @Test
    @WithMockUser
    public void shouldAllowAnyAuthenticatedUser() throws Exception {
        MvcResult result = mockMvc.perform(post(productUpdateURL)).andReturn();
        System.out.println(result.getResponse().getStatus());
    }

    @Test
    @WithMockUser
    public void shouldAllowAnyAuthenticatedUser1() throws Exception {
         mockMvc.perform(post(productUpdateURL).contentType(MediaType.APPLICATION_JSON)
                .content(fakeRequest().toString()).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

    }

    @Test
    public void testForbidden() throws Exception{
        ProductJSON product = fakeRequest();
        ResponseEntity<String> entity = restTemplate.postForEntity(productUpdateURL, product, String.class);
        System.out.println(entity.getBody());
        assertEquals(HttpStatus.FORBIDDEN, entity.getStatusCode());
    }

    @Test
    @WithMockUser
    public void getOk() throws Exception{
        ProductJSON product = fakeRequest();
        ResponseEntity<String> entity = restTemplate.postForEntity(productUpdateURL, product, String.class);
        System.out.println(entity.getBody());
        assertEquals(HttpStatus.OK, entity.getStatusCode());
    }

    @Test
    @WithMockUser
    public void getValidationError ()throws Exception{
        ProductJSON product = fakeRequest();
        product.setId("WRONG");
        ResponseEntity<String> entity = restTemplate.postForEntity(productUpdateURL, product, String.class);
        assertEquals(HttpStatus.BAD_REQUEST, entity.getStatusCode());
        String exp = "{\"message\":\"Id is not UUID format\",\"statusCode\":400,\"errorMessageCode\":\"E005\"}";
        assertEquals(exp, entity.getBody());
    }

    @WithMockUser("spring")
    @Test
    public void negativePrice() throws Exception{
        ProductJSON product = fakeRequestNegative();
        ResponseEntity<String> entity = restTemplate.postForEntity(productUpdateURL, product, String.class);
        assertEquals(HttpStatus.BAD_REQUEST, entity.getStatusCode());
        String exp = "{\"message\":\"Standard Price No Vat is Negative\",\"statusCode\":400,\"errorMessageCode\":\"E004\"}";
        assertEquals(exp, entity.getBody());
    }

}
