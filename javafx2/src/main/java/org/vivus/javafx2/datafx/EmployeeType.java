package org.vivus.javafx2.datafx;

public enum EmployeeType {
	FULL_TIME("Full time"),

	PART_TIME("Part time"),

	CASUAL("Casual"),

	CONTRACTOR("Contractor");

	private String label;

	EmployeeType(String label) {
		this.label = label;
	}

	public String toString() {
		return this.label;
	}
}