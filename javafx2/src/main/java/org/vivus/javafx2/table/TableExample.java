package org.vivus.javafx2.table;

import javafx.application.Application;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class TableExample extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		init(primaryStage);
		primaryStage.show();
	}

	@SuppressWarnings("unchecked")
	private synchronized void init(Stage primaryStage) {
		VBox root = new VBox();
		primaryStage.setScene(new Scene(root));

		final ObservableList<Person> data = FXCollections.observableArrayList(new Person(true,
				"Jacob", "Smith", "jacob.smith@example.com"), new Person(false, "Isabella",
				"Johnson", "isabella.johnson@example.com"), new Person(true, "Ethan", "Williams",
				"ethan.williams@example.com"), new Person(true, "Emma", "Jones",
				"emma.jones@example.com"), new Person(false, "Michael", "Brown",
				"michael.brown@example.com"), new Person(false, "Michael", "Brown",
				"michael.brown@example.com"), new Person(false, "Michael", "Brown",
				"michael.brown@example.com"), new Person(false, "Michael", "Brown",
				"michael.brown@example.com"), new Person(false, "Michael", "Brown",
				"michael.brown@example.com"), new Person(false, "Michael", "Brown",
				"michael.brown@example.com"), new Person(false, "Michael", "Brown",
				"michael.brown@example.com"), new Person(false, "Michael", "Brown",
				"michael.brown@example.com"), new Person(false, "Michael", "Brown",
				"michael.brown@example.com"), new Person(false, "Michael", "Brown",
				"michael.brown@example.com"), new Person(false, "Michael", "Brown",
				"michael.brown@example.com"), new Person(false, "Michael", "Brown",
				"michael.brown@example.com"), new Person(false, "Michael", "Brown",
				"michael.brown@example.com"), new Person(false, "Michael", "Brown",
				"michael.brown@example.com"), new Person(false, "Michael", "Brown",
				"michael.brown@example.com"), new Person(false, "Michael", "Brown",
				"michael.brown@example.com"), new Person(false, "Michael", "Brown",
				"michael.brown@example.com"), new Person(false, "Michael", "Brown",
				"michael.brown@example.com"), new Person(false, "Michael", "Brown",
				"michael.brown@example.com"), new Person(false, "Michael", "Brown",
				"michael.brown@example.com"), new Person(false, "Michael", "Brown",
				"michael.brown@example.com"), new Person(false, "Michael", "Brown",
				"michael.brown@example.com"), new Person(false, "Michael", "Brown",
				"michael.brown@example.com"), new Person(false, "Michael", "Brown",
				"michael.brown@example.com"));
		final TableView<Person> tableView = new TableView<Person>();
		tableView.setItems(data);
		tableView.setEditable(true);
		root.getChildren().add(tableView);

		// "Invited" column
		TableColumn<Person, Boolean> invitedCol = new TableColumn<Person, Boolean>();
		CheckBox allCheckBox = new CheckBox();
		onActionChangeAll(allCheckBox, tableView);
		invitedCol.setGraphic(allCheckBox);
		invitedCol.setMinWidth(50);
		invitedCol.setCellValueFactory(new PropertyValueFactory<Person, Boolean>("invited"));
		invitedCol
				.setCellFactory(new Callback<TableColumn<Person, Boolean>, TableCell<Person, Boolean>>() {
					public TableCell<Person, Boolean> call(TableColumn<Person, Boolean> p) {
						return new CheckBoxTableCell<Person, Boolean>() {
							@Override
							public void updateItem(Boolean item, boolean empty) {
								super.updateItem(item, empty);
								this.setDisable(false);
								if (this.getIndex() % 2 == 0) {
									this.setDisable(true);
								}
							}
						};
					}
				});

		// "first Name" column
		TableColumn<Person, String> firstNameCol = new TableColumn<Person, String>();
		firstNameCol.setResizable(false);
		firstNameCol.setEditable(false);
		firstNameCol.setText("First");
		firstNameCol.setCellValueFactory(new PropertyValueFactory<Person, String>("firstName"));
		// "Last Name" column
		TableColumn<Person, String> lastNameCol = new TableColumn<Person, String>();
		lastNameCol.setText("Last");
		lastNameCol.setCellValueFactory(new PropertyValueFactory<Person, String>("lastName"));
		// "Email" column
		TableColumn<Person, String> emailCol = new TableColumn<Person, String>();
		emailCol.setText("Email");
		emailCol.setMinWidth(200);
		emailCol.setCellValueFactory(new PropertyValueFactory<Person, String>("email"));

		tableView.getColumns().addAll(invitedCol, firstNameCol, lastNameCol, emailCol);
	}

	protected void onActionChangeAll(final CheckBox allCheckBox, final TableView<Person> tableView) {
		allCheckBox.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				boolean value = allCheckBox.selectedProperty().get();
				for (Person person : tableView.getItems()) {
					person.setInvited(value);
				}
			}
		});
	}

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
}
