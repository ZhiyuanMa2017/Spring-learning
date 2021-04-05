package com.squirrel.basicWeb.Web;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
public class WebControllerTest {

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(new WebController()).build();
    }

    @Test
    public void getUser() throws Exception {
        String reponseString = mockMvc.perform(MockMvcRequestBuilders.post("/getUser")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andReturn().getResponse().getContentAsString();
        System.out.println("result:" + reponseString);
    }

    @Test
    public void getUsers() throws Exception {
        String reponseString = mockMvc.perform(MockMvcRequestBuilders.post("/getUsers")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andReturn().getResponse().getContentAsString();
        System.out.println("result:" + reponseString);
    }
}
