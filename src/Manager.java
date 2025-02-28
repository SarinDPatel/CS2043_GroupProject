package src;

public class Manager extends Employee {
	public Manager(Credential inputCredentials) {
		super(inputCredentials);
	}

	public int addInventory(Playware... playwares) {
		int successfulAdds = 0;

		for (Playware playware : playwares) {
			try {
				// TODO: Add to warehouse
				successfulAdds++;
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		return successfulAdds;

	}

	// TODO: Other methods require for there to be a warehouse.
}
