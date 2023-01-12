package com.omc.domain.product.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.omc.domain.product.dto.ProductDto;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.nio.charset.StandardCharsets;

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

		MockMultipartFile imgUrl = new MockMultipartFile(
			"imgUrl",
			"imagefile.png",
			"image/png",
			"<<png data>>".getBytes());

		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("product", content);

		// when
		ResultActions actions =
			mockMvc.perform(
				multipart(HttpMethod.POST, "/product")
					.file(imgUrl)
					.file(new MockMultipartFile("product", "", "application/json",
												content.getBytes(StandardCharsets.UTF_8)))
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON));

		// then
		actions
			.andExpect(status().isCreated())
		;

	}
}
