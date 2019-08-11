package com.da.persistence.pagination;

import com.da.persistence.common.model.redis.MetaDataRedis;
import com.da.persistence.common.model.redis.PricingInformationRedis;
import com.da.persistence.common.model.redis.ProductDescriptionRedis;
import com.da.persistence.common.model.redis.ProductRedis;
import com.da.persistence.common.repository.ProductRedisRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PaginationTests {

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

        MetaDataRedis metaData1 = new MetaDataRedis("ID1","page_title","site name","description","keywords", "//canonical//");
        PricingInformationRedis pricingInformation1 = new PricingInformationRedis("ID1", new BigDecimal("20.00"),new BigDecimal("10.00"), new BigDecimal("20.00") );
        ProductDescriptionRedis productDescription1 = new ProductDescriptionRedis("ID1", "title product", "subtitle", "text");
        ProductRedis product1 = new ProductRedis("ID1","ID1" ,"name","Number", "product type",metaData1, pricingInformation1, productDescription1);

        MetaDataRedis metaData2 = new MetaDataRedis("ID2","page_title","site name","description","keywords", "//canonical//");
        PricingInformationRedis pricingInformation2 = new PricingInformationRedis("ID2", new BigDecimal("20.00"),new BigDecimal("10.00"), new BigDecimal("20.00") );
        ProductDescriptionRedis productDescription2 = new ProductDescriptionRedis("ID2", "title product", "subtitle", "text");
        ProductRedis product2 = new ProductRedis("ID2","ID2" ,"name","Number", "product type",metaData2, pricingInformation2, productDescription2);

        ArrayList<ProductRedis> list = new ArrayList<ProductRedis>();
        list.add(product);
        list.add(product1);
        list.add(product2);
        return list;

    }

    private ArrayList<ProductRedis> secondPageRedisPropertis(){

        MetaDataRedis metaData3 = new MetaDataRedis("ID3","page_title","site name","description","keywords", "//canonical//");
        PricingInformationRedis pricingInformation3 = new PricingInformationRedis("ID3", new BigDecimal("20.00"),new BigDecimal("10.00"), new BigDecimal("20.00") );
        ProductDescriptionRedis productDescription3 = new ProductDescriptionRedis("ID3", "title product", "subtitle", "text");
        ProductRedis product3 = new ProductRedis("ID3","ID3" ,"name","Number", "product type",metaData3, pricingInformation3, productDescription3);

        MetaDataRedis metaData4 = new MetaDataRedis("ID4","page_title","site name","description","keywords", "//canonical//");
        PricingInformationRedis pricingInformation4 = new PricingInformationRedis("ID4", new BigDecimal("20.00"),new BigDecimal("10.00"), new BigDecimal("20.00") );
        ProductDescriptionRedis productDescription4 = new ProductDescriptionRedis("ID4", "title product", "subtitle", "text");
        ProductRedis product4 = new ProductRedis("ID4","ID4" ,"name","Number", "product type",metaData4, pricingInformation4, productDescription4);

        MetaDataRedis metaData5 = new MetaDataRedis("ID5","page_title","site name","description","keywords", "//canonical//");
        PricingInformationRedis pricingInformation5 = new PricingInformationRedis("ID5", new BigDecimal("20.00"),new BigDecimal("10.00"), new BigDecimal("20.00") );
        ProductDescriptionRedis productDescription5 = new ProductDescriptionRedis("ID5", "title product", "subtitle", "text");
        ProductRedis product5 = new ProductRedis("ID5","ID5" ,"name","Number", "product type",metaData5, pricingInformation5, productDescription5);

        ArrayList<ProductRedis> list = new ArrayList<ProductRedis>();
        list.add(product3);
        list.add(product4);
        list.add(product5);
        return list;

    }


    @Test
    public void getTest(){
        Page<ProductRedis> page = Mockito.mock(Page.class);
        Mockito.when(page.getTotalElements()).thenReturn(6L);
        Mockito.when(page.getTotalPages()).thenReturn(2);
        Mockito.when(page.getNumber()).thenReturn(0);
        Mockito.when(page.get()).thenReturn(firstPageRedisPropertis().stream());
        Mockito.when(page.getNumberOfElements()).thenReturn(3);
        Mockito.when(page.spliterator()).thenReturn(firstPageRedisPropertis().spliterator());
        Mockito.when(page.iterator()).thenReturn(firstPageRedisPropertis().iterator());


        Mockito.when(redisRepository.findAll(Mockito.any(Pageable.class))).thenReturn(page);
        ResponseEntity<String> entity = restTemplate.getForEntity(getProducts+"?page=0&size=3", String.class);
        System.out.println(entity.getBody());
        assertEquals(HttpStatus.OK, entity.getStatusCode());
    }

    @Test
    public void getSecondTest(){
        Page<ProductRedis> page = Mockito.mock(Page.class);
        Mockito.when(page.getTotalElements()).thenReturn(6L);
        Mockito.when(page.getTotalPages()).thenReturn(2);
        Mockito.when(page.getNumber()).thenReturn(1);
        Mockito.when(page.get()).thenReturn(secondPageRedisPropertis().stream());
        Mockito.when(page.getNumberOfElements()).thenReturn(3);
        Mockito.when(page.spliterator()).thenReturn(secondPageRedisPropertis().spliterator());
        Mockito.when(page.iterator()).thenReturn(secondPageRedisPropertis().iterator());

        Mockito.when(redisRepository.findAll(Mockito.any(Pageable.class))).thenReturn(page);
        ResponseEntity<String> entity = restTemplate.getForEntity(getProducts+"?page=1&size=3", String.class);
        System.out.println(entity.getBody());
        assertEquals(HttpStatus.OK, entity.getStatusCode());
    }




}
