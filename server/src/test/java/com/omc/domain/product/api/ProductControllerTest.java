package com.omc.domain.product.api;


import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.test.web.servlet.MockMvc;

import com.google.gson.Gson;
import com.omc.domain.member.entity.Member;
import com.omc.domain.member.entity.Social;
import com.omc.domain.member.entity.UserRole;
import com.omc.domain.product.service.ProductService;

// @WithMockUser
@MockBean(JpaMetamodelMappingContext.class)
@WebMvcTest(ProductController.class)
@AutoConfigureRestDocs
public class ProductControllerTest {

	@Autowired
	protected Gson gson = new Gson();

	@Autowired
	protected MockMvc mockMvc;

	@MockBean
	protected ProductService productService;

	protected Member member;

	@BeforeEach
	void setUp() {
		member = Member.builder()
					   .email("test@test.com")
					   .phone("010-1234-5678")
					   .isSocial(Social.ORIGIN)
					   .nickname("test")
					   .password("1234")
					   .profileImg("test.png")
					   .userRole(UserRole.ROLE_USER)
					   .build();
	}
}
