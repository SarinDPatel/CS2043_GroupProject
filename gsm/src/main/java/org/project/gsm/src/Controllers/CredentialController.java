package org.project.gsm.src.Controllers;

import java.util.ArrayList;

import org.project.gsm.src.Credential;
import org.project.gsm.src.Roles;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/credentials")
public class CredentialController {

	public static class RegistrationObject {
		private String newUsername;
		private String newPassword;
		private Roles role;

		public RegistrationObject(String newUsername, String newPassword, Roles role) {
			this.newUsername = newUsername;
			this.newPassword = newPassword;
			this.role = role;
		}

		public String getNewUsername() {
			return newUsername;
		}

		public String getNewPassword() {
			return newPassword;
		}

		public Roles getRole() {
			return role;
		}

	}

	@PostMapping("/login") // Can be changed to have /{id} at the end to facilitiate getting specific id
	public ResponseEntity<String> login(@RequestBody Credential requestCredentials) {
		boolean success = findCredentialsInDB(requestCredentials);
		ResponseEntity<String> toReturn = null;
		if (success) {
			toReturn = ResponseEntity.ok("Login successful");
		} else {
			toReturn = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
		}
		return toReturn;
	}

	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody RegistrationObject regObj) {

		String newUsername = regObj.getNewUsername();
		String newPassword = regObj.getNewPassword();
		Roles role = regObj.getRole();

		boolean exists = getIfUsernameExists(newUsername);
		ResponseEntity<String> toReturn = null;
		if (exists) {
			toReturn = ResponseEntity.status(HttpStatus.CONFLICT).body("Username already taken");
		} else {
			boolean success = false;
			try {
				success = createUser(newUsername, newPassword, role);
			} catch (Exception e) {
				System.out.println(e);
			}
			if (success) {
				toReturn = ResponseEntity.ok("Registration successful");
			} else {
				toReturn = ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
						.body("Registration failed. Please try again");
			}
		}
		return toReturn;
	}

	private boolean findCredentialsInDB(Credential toSearch) {
		ArrayList<Credential> cList = getCredentialsFromDB();
		for (Credential c : cList) {
			if (c.equals(toSearch)) {
				return true;
			}
		}
		return false;

	}

	private boolean getIfUsernameExists(String toCheck) {
		ArrayList<Credential> cList = getCredentialsFromDB();
		for (Credential c : cList) {
			if (c.getUsername().equals(toCheck)) {
				return true;
			}
		}
		return false;
	}

	/* Utilities and Stubs */

	private ArrayList<Credential> getCredentialsFromDB() {
		Credential trialCreds = new Credential("TEST1", "TestUser", "TestPwd", Roles.CLIENT);
		ArrayList<Credential> cList = new ArrayList<>(0);
		cList.add(trialCreds);
		return cList;
		// TODO: Change stub to fetch method

	}

	private boolean createUser(String username, String password, Roles role) {
		String newID = generateCredentialIDBasedOnRole(role);
		Credential newUserCredentials = new Credential(newID, username, password, role);
		// TODO: Call insert into DB
		return true; // Replace with rows affected

	}

	private String generateCredentialIDBasedOnRole(Roles role) {
		// TODO: Replace stub
		return "T0";

	}

}
