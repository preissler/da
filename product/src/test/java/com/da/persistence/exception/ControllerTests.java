package com.da.persistence.exception;

import com.da.common.model.json.ProductJSON;
import com.da.persistence.OauthHelper;
import com.da.persistence.common.model.redis.MetaDataRedis;
import com.da.persistence.common.model.redis.PricingInformationRedis;
import com.da.persistence.common.model.redis.ProductDescriptionRedis;
import com.da.persistence.common.model.redis.ProductRedis;
import com.da.persistence.common.repository.ProductRedisRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerTests extends OauthHelper {
    @LocalServerPort
    public int port;
    @Autowired
    public TestRestTemplate restTemplate;

    String getProducts ="/api/v1/products";

    @MockBean
    public ProductRedisRepository redisRepository;

    private ArrayList<ProductRedis> firstPageRedisPropertis(){
        MetaDataRedis metaData = new MetaDataRedis("ID","page_title","site name","description","keywords", "//canonical//");
        PricingInformationRedis pricingInformation = new PricingInformationRedis("ID", new BigDecimal("20.00"),new BigDecimal("10.00"), new BigDecimal("20.00") );
        ProductDescriptionRedis productDescription = new ProductDescriptionRedis("ID", "title product", "subtitle", "text");
        ProductRedis product = new ProductRedis("ID","ID" ,"name","Number", "product type",metaData, pricingInformation, productDescription);

        ArrayList<ProductRedis> list = new ArrayList<ProductRedis>();
        list.add(product);
        list.add(null);
        list.add(null);
        return list;

    }

    @Test
    public void getOk(){
        Mockito.when(redisRepository.findAll(Mockito.any(Pageable.class))).thenReturn(Page.empty());
        HttpHeaders headers = new HttpHeaders();
        String tokenValue = obtainAccessToken("clientId", "user", "pass");
        headers.add("Authorization","Bearer "+tokenValue);
        HttpEntity<ProductJSON> req = new HttpEntity<>(headers);
        ResponseEntity<String> entity = restTemplate.exchange( getProducts, HttpMethod.GET, req, String.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
    }

    @Test
    public void timeout() throws Exception{
        Mockito.when(redisRepository.findAll(Mockito.any(Pageable.class))).thenAnswer(
                new Answer<Page>() {
                    @Override
                    public Page<ProductRedis> answer(InvocationOnMock invocation){
                        try {
                            Thread.sleep(10000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return Page.empty();
                    }
                });

        HttpHeaders headers = new HttpHeaders();
        String tokenValue = obtainAccessToken("clientId", "user", "pass");
        headers.add("Authorization","Bearer "+tokenValue);
        HttpEntity<ProductJSON> req = new HttpEntity<>(headers);
        ResponseEntity<String> entity = restTemplate.exchange( getProducts, HttpMethod.GET, req, String.class);
        assertEquals(HttpStatus.REQUEST_TIMEOUT, entity.getStatusCode());
    }

    @Test
    public void internalError(){
        Mockito.when(redisRepository.findAll(Mockito.any(Pageable.class))).thenReturn(null);
        HttpHeaders headers = new HttpHeaders();
        String tokenValue = obtainAccessToken("clientId", "user", "pass");
        headers.add("Authorization","Bearer "+tokenValue);
        HttpEntity<ProductJSON> req = new HttpEntity<>(headers);
        ResponseEntity<String> entity = restTemplate.exchange( getProducts, HttpMethod.GET, req, String.class);
        System.out.println(entity.getBody());
        String error = "{\"message\":\"Internal Error\",\"statusCode\":500,\"errorMessageCode\":\"E003\"}";
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, entity.getStatusCode());
        assertTrue(entity.getBody().contains(error));
    }
    @Test
    public void internalErrorExeption(){
        Page<ProductRedis> page = Mockito.mock(Page.class);
        Mockito.when(page.getTotalElements()).thenReturn(6L);
        Mockito.when(page.getTotalPages()).thenReturn(2);
        Mockito.when(page.getNumber()).thenReturn(0);
        Mockito.when(page.get()).thenReturn(firstPageRedisPropertis().stream());
        Mockito.when(page.getNumberOfElements()).thenReturn(3);
        Mockito.when(page.spliterator()).thenReturn(firstPageRedisPropertis().spliterator());
        Mockito.when(page.iterator()).thenReturn(firstPageRedisPropertis().iterator());

        HttpHeaders headers = new HttpHeaders();
        String tokenValue = obtainAccessToken("clientId", "user", "pass");
        headers.add("Authorization","Bearer "+tokenValue);
        HttpEntity<ProductJSON> req = new HttpEntity<>(headers);
        ResponseEntity<String> entity = restTemplate.exchange( getProducts, HttpMethod.GET, req, String.class);
        System.out.println(entity.getBody());
        String error = "{\"message\":\"Internal Error\",\"statusCode\":500,\"errorMessageCode\":\"E003\"}";
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, entity.getStatusCode());
        assertTrue(entity.getBody().contains(error));
    }
}
