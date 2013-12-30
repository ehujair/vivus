package org.vivus.javafx2.table;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.ProgressBarTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import org.javafxdata.control.cell.ExpandingTableCell;

public class TableCellExample extends Application {
	private static final int PREF_WIDTH = 150;

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
		root.getChildren().add(tableView);

		// "Invited" column
		TableColumn<Person, Boolean> checkBoxTableCell = new TableColumn<Person, Boolean>();
		checkBoxTableCell.setText("CheckBoxTableCell");
		checkBoxTableCell.setPrefWidth(PREF_WIDTH);
		checkBoxTableCell.setCellValueFactory(new PropertyValueFactory<Person, Boolean>("invited"));
		checkBoxTableCell
				.setCellFactory(new Callback<TableColumn<Person, Boolean>, TableCell<Person, Boolean>>() {
					public TableCell<Person, Boolean> call(TableColumn<Person, Boolean> p) {
						return new CheckBoxTableCell<Person, Boolean>();
					}
				});
		// "first Name" column
		TableColumn<Person, Boolean> choiceBoxTableCell = new TableColumn<Person, Boolean>();
		choiceBoxTableCell.setText("ChoiceBoxTableCell");
		choiceBoxTableCell.setPrefWidth(PREF_WIDTH);
		choiceBoxTableCell.setCellValueFactory(new PropertyValueFactory<Person, Boolean>("invited"));
		choiceBoxTableCell
				.setCellFactory(new Callback<TableColumn<Person, Boolean>, TableCell<Person, Boolean>>() {
					public TableCell<Person, Boolean> call(TableColumn<Person, Boolean> p) {
						return new ChoiceBoxTableCell<Person, Boolean>();
					}
				});
		// "Last Name" column
		TableColumn<Person, Boolean> comboBoxTableCell = new TableColumn<Person, Boolean>();
		comboBoxTableCell.setText("ComboBoxTableCell");
		comboBoxTableCell.setPrefWidth(PREF_WIDTH);
		comboBoxTableCell.setCellValueFactory(new PropertyValueFactory<Person, Boolean>("invited"));
		comboBoxTableCell
				.setCellFactory(new Callback<TableColumn<Person, Boolean>, TableCell<Person, Boolean>>() {
					public TableCell<Person, Boolean> call(TableColumn<Person, Boolean> p) {
						return new ComboBoxTableCell<Person, Boolean>();
					}
				});
		// "Email" column
		TableColumn<Person, Boolean> expandingTableCell = new TableColumn<Person, Boolean>();
		expandingTableCell.setText("ExpandingTableCell");
		expandingTableCell.setPrefWidth(PREF_WIDTH);
		expandingTableCell.setCellValueFactory(new PropertyValueFactory<Person, Boolean>("invited"));
		expandingTableCell
				.setCellFactory(new Callback<TableColumn<Person, Boolean>, TableCell<Person, Boolean>>() {
					public TableCell<Person, Boolean> call(TableColumn<Person, Boolean> p) {
						return new ExpandingTableCell<Person, Boolean>();
					}
				});
		// "Email" column
		TableColumn<Person, Double> progressBarTableCell = new TableColumn<Person, Double>();
		progressBarTableCell.setText("ProgressBarTableCell");
		progressBarTableCell.setPrefWidth(PREF_WIDTH);
		progressBarTableCell.setCellValueFactory(new PropertyValueFactory<Person, Double>("age"));
		progressBarTableCell
				.setCellFactory(new Callback<TableColumn<Person, Double>, TableCell<Person, Double>>() {
					public TableCell<Person, Double> call(TableColumn<Person, Double> p) {
						return new ProgressBarTableCell<Person>();
					}
				});
		// "Email" column
		TableColumn<Person, Boolean> textFieldTableCell = new TableColumn<Person, Boolean>();
		textFieldTableCell.setText("TextFieldTableCell");
		textFieldTableCell.setPrefWidth(PREF_WIDTH);
		textFieldTableCell.setCellValueFactory(new PropertyValueFactory<Person, Boolean>("invited"));
		textFieldTableCell
				.setCellFactory(new Callback<TableColumn<Person, Boolean>, TableCell<Person, Boolean>>() {
					public TableCell<Person, Boolean> call(TableColumn<Person, Boolean> p) {
						return new TextFieldTableCell<Person, Boolean>();
					}
				});

		tableView.getColumns().addAll(checkBoxTableCell, choiceBoxTableCell, comboBoxTableCell,
				expandingTableCell, progressBarTableCell, textFieldTableCell);
	}

}
