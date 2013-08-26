package net.jonathangiles.hacking.tableview.cellSpan;

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

import org.vivus.javafx.table.Person;

public class CellSpanSample extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		init(primaryStage);
		primaryStage.show();
	}

	private void init(Stage primaryStage) {
		VBox root = new VBox();
		primaryStage.setScene(new Scene(root));

		final ObservableList<Person> data = FXCollections.observableArrayList(new Person(true, 1,
				"Jacob", "Smith", "jacob.smith@example.com"), new Person(false, 1, "Isabella",
				"Johnson", "isabella.johnson@example.com"), new Person(false, 1, "Isabella",
				"Johnson", "isabella.johnson@example.com"), new Person(false, 1, "Isabella",
				"Johnson", "isabella.johnson@example.com"), new Person(true, 2, "Ethan",
				"Williams", "ethan.williams@example.com"), new Person(true, 2, "Emma", "Jones",
				"emma.jones@example.com"), new Person(false, 2, "Michael", "Brown",
				"michael.brown@example.com"), new Person(false, 3, "XXX", "XX",
				"XXXXXX@example.com"));
		CellSpanTableView<Person> tableView = new CellSpanTableView<Person>();
		tableView.setItems(data);
		tableView.setEditable(true);
		tableView.setSpanModel(new SpanModel() {
			@Override
			public boolean isCellSpanEnabled() {
				return true;
			}

			@Override
			public CellSpan getCellSpanAt(int rowIndex, int columnIndex) {
				if (columnIndex > 1)
					return null;
				ObservableList<Person> items = data;
				if (items == null || items.isEmpty())
					return null;
				CellSpan span = new CellSpan(computeRowSpan(rowIndex, items), 1);
				return span;
			}

			protected int computeRowSpan(int rowIndex, ObservableList<Person> items) {
				int rowSpan = 1;
				int group = items.get(rowIndex).groupProperty().get();
				for (int i = rowIndex + 1; i < items.size(); i++) {
					int nextGroup = items.get(i).groupProperty().get();
					if (group == nextGroup) {
						rowSpan++;
					} else {
						break;
					}
				}
				return rowSpan;
			}
		});

		Button button = new Button("group");
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

			}
		});

		root.getChildren().add(button);
		root.getChildren().add(tableView);

		initColumns(tableView);
	}

	@SuppressWarnings("unchecked")
	protected void initColumns(final CellSpanTableView<Person> tableView) {
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
		// "group" column
		TableColumn<Person, Integer> groupCol = new TableColumn<Person, Integer>();
		groupCol.setSortable(false);
		groupCol.setResizable(false);
		groupCol.setEditable(false);
		groupCol.setText("group");
		groupCol.setCellValueFactory(new PropertyValueFactory<Person, Integer>("group"));
		// "first Name" column
		TableColumn<Person, String> firstNameCol = new TableColumn<Person, String>();
		firstNameCol.setSortable(false);
		firstNameCol.setResizable(false);
		firstNameCol.setEditable(false);
		firstNameCol.setText("First");
		firstNameCol.setCellValueFactory(new PropertyValueFactory<Person, String>("firstName"));
		// "Last Name" column
		TableColumn<Person, String> lastNameCol = new TableColumn<Person, String>();
		lastNameCol.setSortable(false);
		lastNameCol.setResizable(false);
		lastNameCol.setEditable(false);
		lastNameCol.setText("Last");
		lastNameCol.setCellValueFactory(new PropertyValueFactory<Person, String>("lastName"));
		// "Email" column
		TableColumn<Person, String> emailCol = new TableColumn<Person, String>();
		emailCol.setSortable(false);
		emailCol.setResizable(false);
		emailCol.setEditable(false);
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
