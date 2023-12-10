package Model;

public class ClientStatistics {
	private int clientId;
	private String firstName;
	private String lastName;
	private int treeCount;
	private double totalDue;
	private double totalPaid;

	// Getters and setters

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getTreeCount() {
		return treeCount;
	}

	public void setTreeCount(int treeCount) {
		this.treeCount = treeCount;
	}

	public double getTotalDue() {
		return totalDue;
	}

	public void setTotalDue(double totalDue) {
		this.totalDue = totalDue;
	}

	public double getTotalPaid() {
		return totalPaid;
	}

	public void setTotalPaid(double totalPaid) {
		this.totalPaid = totalPaid;
	}
}
