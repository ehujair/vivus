package org.vivus.javafx2.table;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class Person {
	private BooleanProperty invited;
	private IntegerProperty group;
	private StringProperty firstName;
	private StringProperty lastName;
	private StringProperty email;

	public Person(boolean invited, String fName, String lName, String email) {
		this.invited = new SimpleBooleanProperty(invited);
		this.firstName = new SimpleStringProperty(fName);
		this.lastName = new SimpleStringProperty(lName);
		this.email = new SimpleStringProperty(email);
		this.invited.addListener(new ChangeListener<Boolean>() {
			public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
				System.out.println(firstNameProperty().get() + " invited: " + t1);
			}
		});
	}
	
	public Person(boolean invited, Integer group, String fName, String lName, String email) {
		this.invited = new SimpleBooleanProperty(invited);
		this.group = new SimpleIntegerProperty(group);
		this.firstName = new SimpleStringProperty(fName);
		this.lastName = new SimpleStringProperty(lName);
		this.email = new SimpleStringProperty(email);
		this.invited.addListener(new ChangeListener<Boolean>() {
			public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
				System.out.println(firstNameProperty().get() + " invited: " + t1);
			}
		});
	}

	public BooleanProperty invitedProperty() {
		return invited;
	}
	
	public DoubleProperty ageProperty() {
		return new SimpleDoubleProperty();
	}

	public IntegerProperty groupProperty() {
		return group;
	}

	public StringProperty firstNameProperty() {
		return firstName;
	}

	public StringProperty lastNameProperty() {
		return lastName;
	}

	public StringProperty emailProperty() {
		return email;
	}

	public void setInvited(boolean invited) {
		this.invited.set(invited);
	}

	public void setGroup(int group) {
		this.group.set(group);
	}

	public void setLastName(String lastName) {
		this.lastName.set(lastName);
	}

	public void setFirstName(String firstName) {
		this.firstName.set(firstName);
	}

	public void setEmail(String email) {
		this.email.set(email);
	}
}
