package org.project.gsm.src;

public abstract class Person {

	protected Credential credentials;

	public Person(Credential inputCredentials) {
		try {
			boolean isValid = login(inputCredentials);
			if (isValid) {
				credentials = inputCredentials;
			} else {
				// TODO: Throw exception
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public boolean login(Credential inputCredentials) {
		// Check for matching credential.
		// Return true if match found
		// Set credentials accordingly.
		return false;

	}
}
