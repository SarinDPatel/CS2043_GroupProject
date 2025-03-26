package org.project.gsm;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.project.gsm.src.Controllers.CredentialController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(CredentialController.class)
@ComponentScan(basePackages = "com.project.gsm.src.Controllers")
@ContextConfiguration(classes = CredentialController.class)
public class CredentialControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void testPostLoginSuccess() throws Exception {

		mockMvc.perform(post("/api/credentials/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{ \"U_ID\":\"TEST1\",\"username\":\"TestUser\",\"password\":\"TestPwd\",\"role\":\"CLIENT\"}"))
				.andExpect(status().isOk());

	}

	@Test
	void testPostLoginFailure() throws Exception {

		mockMvc.perform(post("/api/credentials/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{ \"U_ID\":\"E1\",\"username\":\"TestEmp\",\"password\":\"Test1234\",\"role\":\"EMPLOYEE\"}"))
				.andExpect(status().isUnauthorized());
	}

	@Test
	void testPostRegisterSuccess() throws Exception {

		mockMvc.perform(post("/api/credentials/register")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{ \"newUsername\":\"TestUser2\",\"newPassword\":\"TestPwd2\",\"role\":\"CLIENT\"}"))
				.andExpect(status().isOk());

	}

	@Test
	void testPostRegisterFailure() throws Exception {

		mockMvc.perform(post("/api/credentials/register")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{ \"newUsername\":\"TestUser\",\"newPassword\":\"TestPwd2\",\"role\":\"CLIENT\"}"))
				.andExpect(status().isConflict());
	}
}
