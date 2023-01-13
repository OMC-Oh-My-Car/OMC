package com.omc.domain.product.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.omc.domain.product.dto.ProductDto;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static com.omc.util.ApiDocumentUtils.getRequestPreProcessor;
import static com.omc.util.ApiDocumentUtils.getResponsePreProcessor;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class ProductPostTest extends ProductControllerTest {

	@DisplayName("상품등록 테스트")
	@Test
	public void createTest() throws Exception {
		// given
		ProductDto.Request request = ProductDto.Request.builder()
													   .subject("테스트 상품")
													   .description("테스트 상품입니다.")
													   .address("서울시 강남구")
													   .zipcode("12345")
													   .facilities("#편의시설1#편의시설2")
													   .telephone("02-1234-5678")
													   .price(10000L)
													   .checkIn("15:00")
													   .checkOut("11:00")
													   .build();

		String content = gson.toJson(request);

		MockMultipartFile imgUrl1 = new MockMultipartFile(
			"imgUrl",
			"imagefile.png",
			"image/png",
			"<<png data>>".getBytes());

		MockMultipartFile imgUrl2 = new MockMultipartFile(
			"imgUrl",
			"imagefile.png",
			"image/png",
			"<<png data>>".getBytes());

		// when
		ResultActions actions =
			mockMvc.perform(
				multipart(HttpMethod.POST, "/product")
					.file(imgUrl1)
					.file(imgUrl2)
					.file(new MockMultipartFile("product", "", "application/json",
												content.getBytes(StandardCharsets.UTF_8)))
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
					.content(content)
						   );

		// then
		actions
			.andExpect(status().isCreated())
			.andDo(document(
				"post-product",
				getRequestPreProcessor(),
				getResponsePreProcessor(),
				requestFields(
					List.of(
						fieldWithPath("subject").type(JsonFieldType.STRING).description("상품명"),
						fieldWithPath("description").type(JsonFieldType.STRING).description("상품 설명"),
						fieldWithPath("price").type(JsonFieldType.NUMBER).description("상품 가격"),
						fieldWithPath("address").type(JsonFieldType.STRING).description("주소"),
						fieldWithPath("zipcode").type(JsonFieldType.STRING).description("주소의 우편번호"),
						fieldWithPath("facilities").type(JsonFieldType.STRING).description("편의시설 : #으로 구분"),
						fieldWithPath("telephone").type(JsonFieldType.STRING).description("전화번호"),
						fieldWithPath("checkIn").type(JsonFieldType.STRING).description("체크인 시간"),
						fieldWithPath("checkOut").type(JsonFieldType.STRING).description("체크아웃 시간")
						   ))));

	}
}
