package org.project.gsm.src.Controllers;

import org.apache.catalina.startup.CredentialHandlerRuleSet;
import org.project.gsm.src.Credential;
import org.springframework.context.support.BeanDefinitionDsl.Role;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.project.gsm.src.Roles;

@RestController
@RequestMapping("api/credentials")
public class CredentialController {
	@PostMapping("/login") // Can be changed to have /{id} at the end to facilitiate getting specific id
	public ResponseEntity<String> login(@RequestBody Credential requestCredentials) {
		boolean success = requestCredentials.equals(getCredentialFromDB());
		ResponseEntity<String> toReturn = null;
		if (success) {
			toReturn = ResponseEntity.ok("Login successful");
		} else {
			toReturn = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
		}
		return toReturn;
	}

	public Credential getCredentialFromDB() {
		Credential trialCreds = new Credential("TEST123", "TestUser", "TestPwd", Roles.CLIENT);
		return trialCreds;
		// TODO: Change stub to actual method

	}

}
