package com.magic.microservice.spring_provider_user;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.magic.microservice.spring_provider_user.entity.MobilePhone;

/**
 * @Author: Magicfeng007
 * @Description:
 * @Date: Created in 2018-06-10---下午2:24
 */
@WebAppConfiguration
@SpringBootTest(classes = ProviderUserApplication.class)
@RunWith(SpringRunner.class)
public class ControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    
    @Autowired
    private RestTemplate restTemplate;

    private MockMvc mockMvc;
    
    @Before
    public void setMockMvc(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    
    @Test
    public void testRestTemplate() {
    	HashMap<String, Object> map = new HashMap<>();
    	map.put("id", "1");
//    	MobilePhone mobilePhone = restTemplate.getForObject("http://localhost:8072/queryMobileByid?id={id}",MobilePhone.class, map);
    	//等价于
    	MobilePhone mobilePhone = restTemplate.getForObject("http://localhost:8072/queryMobileByid?id={id}",MobilePhone.class, "1");
    	System.out.println(mobilePhone);
    }
    
    @Test
    public void testRestTemplate2() {
    	MobilePhone mobilePhone0 = new MobilePhone();
    	mobilePhone0.setId(1);
    	HashMap<String, Object> map = new HashMap<>();
    	map.put("id", "1");
    	LinkedMultiValueMap<String, Object> dataMap = new LinkedMultiValueMap<>();
    	dataMap.add("id", "1");
    	ResponseEntity<MobilePhone> mobilePhone = restTemplate.getForEntity("http://localhost:8072/queryMobileByid?id={id}",MobilePhone.class, map);//restTemplate.getForObject("http://localhost:8072/queryMobileByid",MobilePhone.class, map);
    	System.out.println(mobilePhone.getBody().toString());
    }


    @Test
    public void addMobilePhoneBatch(){
        try {
            MultiValueMap<String,String> mobilePhone1 = new LinkedMultiValueMap<>();

            mobilePhone1.add("mobilePhoneMap['a'].brand","XIAOMI");
            mobilePhone1.add("mobilePhoneMap['a'].model","8");
            mobilePhone1.add("mobilePhoneMap['a'].price","2999.0");
            mobilePhone1.add("mobilePhoneMap['a'].configuration","[cpu:4core,RAM=5G,ROM=120G]");


            MultiValueMap<String,String> mobilePhone2 = new LinkedMultiValueMap<>();

            mobilePhone2.add("mobilePhoneMap['b'].brand","XIAOMI");
            mobilePhone2.add("mobilePhoneMap['b'].model","NOTE");
            mobilePhone2.add("mobilePhoneMap['b'].price","2559.0");
            mobilePhone2.add("mobilePhoneMap['b'].configuration","[cpu:4core,RAM=3G,ROM=64G]");

            mockMvc.perform(MockMvcRequestBuilders.post("/addMobilePhoneBatch")
                    .params(mobilePhone1)
                    .params(mobilePhone2)
                    .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                    .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void addMobilePhoneListIdBatchTest(){
        try {
            MultiValueMap<String,String> mobilePhone1 = new LinkedMultiValueMap<>();

            mobilePhone1.add("mobilePhoneId","1");
            mobilePhone1.add("mobilePhoneId","2");

            mockMvc.perform(MockMvcRequestBuilders.post("/addMobilePhoneListIdBatch")
                    .params(mobilePhone1))
                    //.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                    .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void listTest2(){
        try {
            MobilePhone mobilePhone = new MobilePhone();
            mobilePhone.setBrand("motolola");

            ObjectMapper objectMapper = new ObjectMapper();
            String jsonStr = objectMapper.writeValueAsString(mobilePhone);
            System.out.println(jsonStr);


            mockMvc.perform(MockMvcRequestBuilders.post("/listTest2")
                    .content("[" + jsonStr + "]")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void addMobilePhoneListBatch(){
        MobilePhone mobilePhone = new MobilePhone();
        mobilePhone.setBrand("BlakBarry");

        MobilePhone mobilePhone2 = new MobilePhone();
        mobilePhone2.setBrand("SUNSUM");

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr = "[";
        try {
            jsonStr += objectMapper.writeValueAsString(mobilePhone);
            jsonStr += ",";
            jsonStr += objectMapper.writeValueAsString(mobilePhone2);
            jsonStr += "]";
            mockMvc.perform(MockMvcRequestBuilders.post("/addMobilePhoneListBatch")
                    .content(jsonStr)
                    .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void addMobilePhoneMapBatch(){
        MobilePhone mobilePhone = new MobilePhone();
        mobilePhone.setBrand("BlakBarry1");

        MobilePhone mobilePhone2 = new MobilePhone();
        mobilePhone2.setBrand("SUNSUM1");

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr = "{\"a\":";
        try {
            jsonStr += objectMapper.writeValueAsString(mobilePhone);
            jsonStr += ",\"b\":";
            jsonStr += objectMapper.writeValueAsString(mobilePhone2);
            jsonStr += "}";
            System.out.println("jsonStr:" + jsonStr);
            mockMvc.perform(MockMvcRequestBuilders.post("/addMobilePhoneMapBatch")
                    .content(jsonStr)
                    .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void queryMobile(){
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/queryMobile")
                    .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void queryMobileById2(){
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/queryMobileByid").param("id", "1"))
                    //.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void queryMobileByJdbcTemplate(){
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/queryMobileByJdbcTemplate")
                    .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).param("brand","XIAOMI"))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


}
