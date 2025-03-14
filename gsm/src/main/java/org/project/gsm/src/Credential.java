package org.project.gsm.src;

public class Credential {

	private final String U_ID;
	private String username;
	private String password;
	private Roles role;

	public Credential(String U_ID, String username, String password, Roles role) {
		this.U_ID = U_ID;
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public String getU_ID() {
		return U_ID;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public Roles getRole() {
		return role;
	}

	@Override
	public boolean equals(Object other) {
		if (other == null || this.getClass() != other.getClass()) {
			return false;
		}
		Credential otherCred = (Credential) other;
		boolean userIDMatches = (this.getU_ID().equals(otherCred.getU_ID()));
		boolean usernameMatches = (this.getUsername().equals(otherCred.getUsername()));
		boolean passwdMatches = (this.getPassword().equals(otherCred.getPassword()));
		boolean roleMatches = (this.getRole() == otherCred.getRole());

		return userIDMatches && usernameMatches && passwdMatches && roleMatches;

	}

}
