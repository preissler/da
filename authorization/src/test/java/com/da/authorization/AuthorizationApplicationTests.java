package com.da.authorization;

import org.bouncycastle.util.encoders.Base64;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.UnsupportedEncodingException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthorizationApplicationTests {
	private static final String CLIENT_ID = "clientId";
	private static final String CLIENT_SECRET = "secret";
	private static final String USER_ID = "user";
	private static final String USER_SECRET = "pass";

	@Autowired
	WebApplicationContext context;

	@Autowired
	FilterChainProxy springSecurityFilterChain;

	MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).addFilters(springSecurityFilterChain).build();
	}

	@Test
	public void clientAuthenticationPassesUsingUserCredentialsOnClientCredentialsGrantFlow() throws Exception {
		mockMvc.perform(post("/oauth/token")
				.param("grant_type", "client_credentials")
				.header("Authorization", httpBasicCredentials(CLIENT_ID, CLIENT_SECRET)))
				.andExpect(status().isOk());
	}

	@Test
	public void clientAuthenticationPassesUsingUserAndPassword() throws Exception {
		mockMvc.perform(post("/oauth/token")
				.param("grant_type", "password")
				.param("username", USER_ID)
				.param("password", USER_SECRET)
				.header("Authorization", httpBasicCredentials(CLIENT_ID, CLIENT_SECRET)))
				.andExpect(status().isOk());
	}

	static String httpBasicCredentials(String userName, String password) {
		String headerValue = "Basic ";
		byte[] toEncode = null;
		try {
			toEncode = (userName + ":" + password).getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) { }
		headerValue += new String(Base64.encode(toEncode));
		return headerValue;
	}
}
