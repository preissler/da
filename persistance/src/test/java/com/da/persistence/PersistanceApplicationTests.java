package com.da.persistence;

import com.da.common.model.json.MetaDataJSON;
import com.da.common.model.json.PricingInformationJSON;
import com.da.common.model.json.ProductDescriptionJSON;
import com.da.common.model.json.ProductJSON;
import com.da.persistence.common.model.db.MetaData;
import com.da.persistence.common.model.db.PricingInformation;
import com.da.persistence.common.model.db.Product;
import com.da.persistence.common.model.db.ProductDescription;
import com.da.persistence.common.repository.ProductRedisRepository;
import com.da.persistence.common.repository.ProductRepository;
import com.da.persistence.queue.Consumer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PersistanceApplicationTests {

	@Autowired
	Consumer consumer;
	@MockBean
	public ProductRedisRepository redisRepository;
	@Autowired
	private ProductRepository productRepository;
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

	@Test
	public void contextLoads() throws Exception{
		consumer.handleMessage(new ObjectMapper().writeValueAsString(fakeRequest()));
		Product product = productRepository.findById(fakeRequest().getId()).get();
		Assert.assertEquals(product.getId(),fakeRequest().getId());
	}

}
