package com.da.wapi;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mock.web.MockServletContext;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.Filter;

public class ResourceServerConfigurationTests {
    private static InMemoryTokenStore tokenStore = new InMemoryTokenStore();

    private OAuth2AccessToken token;
    private OAuth2Authentication authentication;

    @Rule
    public ExpectedException expected = ExpectedException.none();

    @Before
    public void init() {
        token = new DefaultOAuth2AccessToken("FOO");
        ClientDetails client = new BaseClientDetails("client", null, "read", "client_credentials", "ROLE_CLIENT");
        authentication = new OAuth2Authentication(
                new TokenRequest(null, "client", null, "client_credentials").createOAuth2Request(client), null);
        tokenStore.clear();
    }
    @Test
    public void testDefaults() throws Exception {
        tokenStore.storeAccessToken(token, authentication);
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setServletContext(new MockServletContext());
        context.register(ResourceServerContext.class);
        context.refresh();
        MockMvc mvc = buildMockMvc(context);
        mvc.perform(MockMvcRequestBuilders.get("/")).andExpect(MockMvcResultMatchers.status().isUnauthorized());
        mvc.perform(MockMvcRequestBuilders.get("/").header("Authorization", "Bearer FOO"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
        context.close();
    }

    @Configuration
    @EnableResourceServer
    @EnableWebSecurity
    protected static class ResourceServerContext {
        @Bean
        public TokenStore tokenStore() {
            return tokenStore;
        }
    }

    private MockMvc buildMockMvc(AnnotationConfigWebApplicationContext context) {
        return MockMvcBuilders.webAppContextSetup(context)
                .addFilters(new DelegatingFilterProxy(context.getBean("springSecurityFilterChain", Filter.class)))
                .build();
    }
}
