package org.project.gsm;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.project.gsm.src.Game;
import org.project.gsm.src.Merch;
import org.project.gsm.src.Playware;
import org.project.gsm.src.Controllers.EmployeeController;
import org.project.gsm.src.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@ExtendWith(SpringExtension.class) // JUnit 5 extension
@WebMvcTest(EmployeeController.class) // Load only the EmployeeController
@ComponentScan(basePackages = "com.project.gsm.src.Controllers")
@ContextConfiguration(classes = EmployeeController.class)
public class EmployeeControllerTest {

	@Autowired
	private MockMvc mockMvc; // Simulates HTTP requests

	@MockBean
	private TransactionService transactionService;

	@Test
	void testPostProcessTransactionSuccess() throws Exception {
		ArrayList<Playware> items = new ArrayList<>();
		Merch merch = new Merch("TestMerch1", 20.0, null, null);
		Game game = new Game("TestGame1", 30.0, null, null);
		items.add(merch);
		items.add(game);
		Mockito.when(transactionService.processTransaction(items)).thenReturn(true); // Mocking success

		mockMvc.perform(post("/api/employees/checkout")
				.contentType(MediaType.APPLICATION_JSON)
				.content("["
						+ "{\"type\":\"game\", \"name\":\"Super Mario Odyssey\", \"basePrice\":59.99, \"consoles\":[\"Nintendo Switch\"], \"categories\":[\"Action\", \"Adventure\"]},"
						+ "{\"type\":\"merch\", \"name\":\"Super Mario T-shirt\", \"basePrice\":19.99, \"genres\":[\"Clothing\"], \"sizes\":[\"S\", \"M\", \"L\", \"XL\"]},"
						+ "{\"type\":\"game\", \"name\":\"The Legend of Zelda: Breath of the Wild\", \"basePrice\":69.99, \"consoles\":[\"Nintendo Switch\", \"Wii U\"], \"categories\":[\"Action\", \"Adventure\", \"RPG\"]}"
						+ "]"))

				.andExpect(status().isOk())
				.andExpect(content().string("Checkout successful"));
	}

	@Test
	void testPostProcessTransactionFailure() throws Exception {
		Merch merch = new Merch("TestMerch1", 20.0, null, null);
		Game game = new Game("TestGame1", 30.0, null, null);
		ArrayList<Playware> items = new ArrayList<>();
		items.add(merch);
		items.add(game);
		Mockito.when(transactionService.processTransaction(items)).thenReturn(false); // Mocking failure

		mockMvc.perform(post("/api/employees/checkout")
				.contentType(MediaType.APPLICATION_JSON)
				.content("["
						+ "{\"type\":\"game\", \"name\":\"Super Mario Odyssey\", \"basePrice\":59.99, \"consoles\":[\"Nintendo Switch\"], \"categories\":[\"Action\", \"Adventure\"]},"
						+ "{\"type\":\"merch\", \"name\":\"Super Mario T-shirt\", \"basePrice\":19.99, \"genres\":[\"Clothing\"], \"sizes\":[\"S\", \"M\", \"L\", \"XL\"]},"
						+ "{\"type\":\"game\", \"name\":\"The Legend of Zelda: Breath of the Wild\", \"basePrice\":69.99, \"consoles\":[\"Nintendo Switch\", \"Wii U\"], \"categories\":[\"Action\", \"Adventure\", \"RPG\"]}"
						+ "]"))
				.andExpect(status().isInternalServerError())
				.andExpect(content().string("Processing transactions caused error"));

	}
}
