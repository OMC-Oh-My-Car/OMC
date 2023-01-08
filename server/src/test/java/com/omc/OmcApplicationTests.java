package com.omc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
class OmcApplicationTests {
	@Autowired
	private MockMvc mvc;

	@Test
	@DisplayName("JWT로 로그인하여 MemberContext 생성 후 회원정보 얻기")
	void test1() throws Exception {
		ResultActions resultActions = mvc
				.perform(
						post("/member/login")
								.content("""
										{
											"email": "test@gmail.com",
											"password": "1234"
										}
										""".stripIndent())
								.contentType(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8))
				)
				.andDo((print()));

		resultActions
				.andExpect(status().is2xxSuccessful());

		MvcResult mvcResult = resultActions.andReturn();

		MockHttpServletResponse response = mvcResult.getResponse();

		String accessToken = response.getHeader("Authorization");

		resultActions = mvc
				.perform(
						get("/member/me")
								.header("Authorization", "Bearer " + accessToken)
				)
				.andDo(print());

		resultActions
				.andExpect(status().is2xxSuccessful());

	}

}
