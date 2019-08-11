package com.da.wapi.controller;


import com.da.common.model.json.MetaDataJSON;
import com.da.common.model.json.PricingInformationJSON;
import com.da.common.model.json.ProductDescriptionJSON;
import com.da.common.model.json.ProductJSON;
import com.da.wapi.OauthHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;



@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ProductControllerTest extends OauthHelper {


    @LocalServerPort
    public int port;
    @Autowired
    public TestRestTemplate restTemplate;
    String productUpdateURL = "/api/v1/update";



   @MockBean
   RabbitAdmin rabbitAdmin;
   @MockBean
   RabbitTemplate rabbitTemplate;




    private ProductJSON fakeRequest(){
        MetaDataJSON metaDataJSON = new MetaDataJSON("title","site_name","description",
                "keywords", "canonical");
        PricingInformationJSON pricingInformationJSON = new PricingInformationJSON( new BigDecimal("10.00"),
                new BigDecimal("9.00"), new BigDecimal("10.00"));
        ProductDescriptionJSON productDescriptionJSON = new ProductDescriptionJSON("title","subtitle",
                "text");

        return new ProductJSON("ADc","name","model","product_type",metaDataJSON,
                pricingInformationJSON, productDescriptionJSON);
    }

    private ProductJSON fakeRequestNegative(){
        MetaDataJSON metaDataJSON = new MetaDataJSON("title","site_name","description",
                "keywords", "canonical");
        PricingInformationJSON pricingInformationJSON = new PricingInformationJSON( new BigDecimal("10.00"),
                new BigDecimal("-9.00"), new BigDecimal("10.00"));
        ProductDescriptionJSON productDescriptionJSON = new ProductDescriptionJSON("title","subtitle",
                "text");

        return new ProductJSON("ABC","name","model","product_type",metaDataJSON,
                pricingInformationJSON, productDescriptionJSON);
    }



  /*  @WithMockUser
    @Test
    public void shouldAllowAnyAuthenticatedUser() throws Exception {
        MvcResult result = mockMvc.perform(post(productUpdateURL)).andReturn();
        System.out.println(result.getResponse().getStatus());
    }

    @Test
    @WithMockUser
    public void shouldAllowAnyAuthenticatedUser1() throws Exception {
         mockMvc.perform(post(productUpdateURL).contentType(MediaType.APPLICATION_JSON)
                .content(fakeRequest().toString()).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

    }*/

    @Test
    public void testForbidden() throws Exception{
        ProductJSON product = fakeRequest();
        ResponseEntity<String> entity = restTemplate.postForEntity(productUpdateURL, product, String.class);
        System.out.println(entity.getBody());
        assertEquals(HttpStatus.UNAUTHORIZED, entity.getStatusCode());
    }

    @Test
    public void getOk() throws Exception{
        ProductJSON product = fakeRequest();
        String tokenValue = obtainAccessToken("clientId", "user", "pass");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization","Bearer "+tokenValue);
        HttpEntity<ProductJSON > req = new HttpEntity<>(product, headers);
        ResponseEntity<String> entity = restTemplate.postForEntity(productUpdateURL, req, String.class);
        System.out.println(entity.getBody());
        assertEquals(HttpStatus.OK, entity.getStatusCode());
    }

    @Test
    public void getValidationError ()throws Exception{
        ProductJSON product = fakeRequest();
        product.setId(null);
        String tokenValue = obtainAccessToken("clientId", "user", "pass");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization","Bearer "+tokenValue);
        HttpEntity<ProductJSON > req = new HttpEntity<>(product, headers);
        ResponseEntity<String> entity = restTemplate.postForEntity(productUpdateURL, req, String.class);
        assertEquals(HttpStatus.BAD_REQUEST, entity.getStatusCode());
        String exp = "{\"message\":\"Product ID is null\",\"statusCode\":400,\"errorMessageCode\":\"E003\"}";
        assertEquals(exp, entity.getBody());
    }

    @Test
    public void negativePrice() throws Exception{
        ProductJSON product = fakeRequestNegative();
        String tokenValue = obtainAccessToken("clientId", "user", "pass");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization","Bearer "+tokenValue);
        HttpEntity<ProductJSON > req = new HttpEntity<>(product, headers);
        ResponseEntity<String> entity = restTemplate.postForEntity(productUpdateURL, req, String.class);
        assertEquals(HttpStatus.BAD_REQUEST, entity.getStatusCode());
        String exp = "{\"message\":\"Standard Price No Vat is Negative\",\"statusCode\":400,\"errorMessageCode\":\"E004\"}";
        assertEquals(exp, entity.getBody());
    }

}
