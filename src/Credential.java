package src;

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

}
