package org.project.gsm.src;

public abstract class Person {

	private Credential credentials;

	public Person(Credential inputCredentials) {
		this.credentials = inputCredentials;
	}

	public Credential getCredential() {
		return credentials;
	}

}
