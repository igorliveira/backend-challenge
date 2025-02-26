package com.api.jwt_validator.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class JwtValidatorControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void validJwtTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/api/jwtvalidate").contentType(MediaType.APPLICATION_JSON).content(
				"eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05sIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg"))
				.andExpect(MockMvcResultMatchers.content().string("true"));
	}
	
	@Test
	void invalidJwtTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/api/jwtvalidate").contentType(MediaType.APPLICATION_JSON).content(
				"eyJhbGciOiJzI1NiJ9.dfsdfsfryJSr2xrIjoiQWRtaW4iLCJTZrkIjoiNzg0MSIsIk5hbrUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05fsdfsIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg"))
				.andExpect(MockMvcResultMatchers.content().string("false"));
	}
	
	@Test
	void invalidJwtClaimNameTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/api/jwtvalidate").contentType(MediaType.APPLICATION_JSON).content(
				"eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiRXh0ZXJuYWwiLCJTZWVkIjoiODgwMzciLCJOYW1lIjoiTTRyaWEgT2xpdmlhIn0.6YD73XWZYQSSMDf6H0i3-kylz1-TY_Yt6h1cV2Ku-Qs"))
				.andExpect(MockMvcResultMatchers.content().string("false"));
	}
	
	@Test
	void invalidJwtAdditionalClaims() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/api/jwtvalidate").contentType(MediaType.APPLICATION_JSON).content(
				"eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiTWVtYmVyIiwiT3JnIjoiQlIiLCJTZWVkIjoiMTQ2MjciLCJOYW1lIjoiVmFsZGlyIEFyYW5oYSJ9.cmrXV_Flm5mfdpfNUVopY_I2zeJUy4EZ4i3Fea98zvY"))
				.andExpect(MockMvcResultMatchers.content().string("false"));
	}
	

}
