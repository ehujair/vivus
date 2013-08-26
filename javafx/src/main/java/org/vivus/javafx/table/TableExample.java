package org.vivus.javafx.table;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
	private void init(Stage primaryStage) {
		VBox root = new VBox();
		primaryStage.setScene(new Scene(root));

		final ObservableList<Person> data = FXCollections.observableArrayList(new Person(true,
				"Jacob", "Smith", "jacob.smith@example.com"), new Person(false, "Isabella",
				"Johnson", "isabella.johnson@example.com"), new Person(true, "Ethan", "Williams",
				"ethan.williams@example.com"), new Person(true, "Emma", "Jones",
				"emma.jones@example.com"), new Person(false, "Michael", "Brown",
				"michael.brown@example.com"));
		final TableView<Person> tableView = new TableView<Person>();
		tableView.setItems(data);
		tableView.setEditable(true);

		Button button = new Button("add");
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				List<Person> newData = new ArrayList<Person>(data);
				for (Person person : data) {
					if (person.invitedProperty().get()) {
						System.out.println("selected: " + person);
						newData.add(newData.indexOf(person) + 1, clone(person));
					}
				}
				tableView.setItems(FXCollections.observableList(newData));
				tableView.fireEvent(new ActionEvent());
			}

			private Person clone(Person person) {
				return new Person(false, person.firstNameProperty().get(), person
						.lastNameProperty().get(), "");
			}
		});

		root.getChildren().add(button);
		root.getChildren().add(tableView);

		// "Invited" column
		TableColumn<Person, Boolean> invitedCol = new TableColumn<Person, Boolean>();
		CheckBox allCheckBox = new CheckBox();
		// invitedCol.setText("Invited");
		onActionChangeAll(allCheckBox, tableView);
		invitedCol.setGraphic(allCheckBox);
		invitedCol.setMinWidth(50);
		invitedCol.setCellValueFactory(new PropertyValueFactory<Person, Boolean>("invited"));
		invitedCol
				.setCellFactory(new Callback<TableColumn<Person, Boolean>, TableCell<Person, Boolean>>() {
					public TableCell<Person, Boolean> call(TableColumn<Person, Boolean> p) {
						System.out.println("setCellFactory Callback");
						return new CheckBoxTableCell<Person, Boolean>();
					}
				});
		// "first Name" column
		TableColumn<Person, String> firstNameCol = new TableColumn<Person, String>();
		// firstNameCol.setSortable(false);
		firstNameCol.setResizable(false);
		firstNameCol.setEditable(false);
		firstNameCol.setText("First");
		firstNameCol.setCellValueFactory(new PropertyValueFactory<Person, String>("firstName"));
		// "Last Name" column
		TableColumn<Person, String> lastNameCol = new TableColumn<Person, String>();
		// lastNameCol.setSortable(false);
		lastNameCol.setText("Last");
		lastNameCol.setCellValueFactory(new PropertyValueFactory<Person, String>("lastName"));
		// "Email" column
		TableColumn<Person, String> emailCol = new TableColumn<Person, String>();
		// emailCol.setSortable(false);
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

}
