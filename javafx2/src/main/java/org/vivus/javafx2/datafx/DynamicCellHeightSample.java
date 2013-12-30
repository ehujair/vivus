package org.vivus.javafx2.datafx;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.ProgressBarTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;

import org.javafxdata.control.cell.ExpandOnMouseEventCellFactory;
import org.javafxdata.control.cell.ExpandOnMouseEventListCell.CellResizeFeatures;
import org.javafxdata.control.cell.ExpandOnMouseEventListCell.CellUpdate;

/**
 * 
 * @author Jonathan Giles
 */
public class DynamicCellHeightSample extends Application {

	private final static ObservableList<Person> personsList;

	static {
		personsList = Person.getTestList();
	}

	public static void main(String... args) {
		launch(DynamicCellHeightSample.class, args);
	}

	@Override
	public void start(Stage stage) {
		stage.setTitle("Dynamic Cell Height Samples");
		final Scene scene = new Scene(new Group(), 875, 700);
		scene.setFill(Color.LIGHTGRAY);
		Group root = (Group) scene.getRoot();

		root.getChildren().add(getContent(scene));

		stage.setScene(scene);
		stage.show();
	}

	public Node getContent(Scene scene) {
		// TabPane
		final TabPane tabPane = new TabPane();
		tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
		tabPane.setPrefWidth(scene.getWidth());
		tabPane.setPrefHeight(scene.getHeight());

		tabPane.prefWidthProperty().bind(scene.widthProperty());
		tabPane.prefHeightProperty().bind(scene.heightProperty());

		// list view examples
		Tab listViewTab = new Tab("ListView");
		buildListViewTab(listViewTab);
		tabPane.getTabs().add(listViewTab);

		// // tree view examples
		// Tab treeViewTab = new Tab("TreeView");
		// buildTreeViewTab(treeViewTab);
		// tabPane.getTabs().add(treeViewTab);
		//
		// table view examples
		Tab tableViewTab = new Tab("TableView");
		buildTableViewTab(tableViewTab);
		tabPane.getTabs().add(tableViewTab);

		return tabPane;
	}

	private void buildListViewTab(Tab tab) {
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(5, 5, 5, 5));
		grid.setHgap(5);
		grid.setVgap(5);

		// create a simple listview with unlimited expansion
		final ListView<Person> listView = new ListView<Person>();
		listView.setItems(personsList);
		listView.setCellFactory(ExpandOnMouseEventCellFactory
				.<Person> forListView(Integer.MAX_VALUE));
		grid.add(Utils.createLabel("Unlimited cells (on click):"), 0, 0);
		grid.add(listView, 0, 1);
		GridPane.setVgrow(listView, Priority.ALWAYS);

		// create a simple listview with only one cell able to be expanded
		final ListView<Person> listView2 = new ListView<Person>();
		listView2.setItems(personsList);
		listView2.setCellFactory(ExpandOnMouseEventCellFactory.<Person> forListView(1,
				MouseEvent.MOUSE_ENTERED));
		grid.add(Utils.createLabel("One expanded cell (on hover):"), 1, 0);
		grid.add(listView2, 1, 1);
		GridPane.setVgrow(listView2, Priority.ALWAYS);

		// create a listview where the content changes when the cell is expanded
		final ListView<Person> listView3 = new ListView<Person>();
		listView3.setItems(personsList);
		listView3.setCellFactory(ExpandOnMouseEventCellFactory.<Person> forListView(1,
				new Callback<CellResizeFeatures<Person>, CellUpdate>() {
					@Override
					public CellUpdate call(CellResizeFeatures<Person> crf) {
						if (crf.isExpanded) {
							ImageView image_48 = new ImageView(new Image(getClass()
									.getResourceAsStream("download2_48.png")));
							String fullName = crf.cell.getItem().getFirstName() + " "
									+ crf.cell.getItem().getLastName();
							return new CellUpdate(fullName, image_48);
						} else {
							return new CellUpdate(crf.cell.getItem().toString(), null);
						}
					}
				}));
		grid.add(Utils.createLabel("Replace cell content:"), 2, 0);
		grid.add(listView3, 2, 1);
		GridPane.setVgrow(listView3, Priority.ALWAYS);

		tab.setContent(grid);
	}

	private String address = "22 Pound Street,\nDutton Park, 4102,\nQueensland,\nAustralia";
	private String shortAddress = address.replace("\n", " ");

	private void buildTableViewTab(Tab tab) {
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(5, 5, 5, 5));
		grid.setHgap(40);
		grid.setVgap(5);

		// create a Person tableview
		TableColumn<Person, Boolean> invitedColumn = new TableColumn<Person, Boolean>(
				"Remote\nWorker");
		invitedColumn
				.setCellValueFactory(new PropertyValueFactory<Person, Boolean>("telecommuter"));
		invitedColumn.setCellFactory(CheckBoxTableCell.forTableColumn(invitedColumn));

		final TableColumn<Person, String> firstNameColumn = new TableColumn<Person, String>("First");
		firstNameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("firstName"));
		firstNameColumn.setCellFactory(TextFieldTableCell.<Person> forTableColumn());

		final TableColumn<Person, String> lastNameColumn = new TableColumn<Person, String>("Last");
		lastNameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("lastName"));
		lastNameColumn.setCellFactory(TextFieldTableCell.<Person> forTableColumn());

		TableColumn<Person, String> nameColumn2 = new TableColumn<Person, String>("Name");
		nameColumn2.getColumns().setAll(firstNameColumn, lastNameColumn);

		final TableColumn<Person, String> addressColumn = new TableColumn<Person, String>("Address");
		addressColumn.setPrefWidth(300);
		addressColumn
				.setCellValueFactory(new Callback<CellDataFeatures<Person, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<Person, String> param) {
						return new ReadOnlyStringWrapper(shortAddress);
					}
				});

		final TableColumn<Person, Double> progressColumn = new TableColumn<Person, Double>(
				"Progress");
		progressColumn.setMaxWidth(107);
		progressColumn.setMinWidth(107);
		progressColumn.setCellValueFactory(new PropertyValueFactory<Person, Double>("progress"));
		progressColumn
				.setCellFactory(new Callback<TableColumn<Person, Double>, TableCell<Person, Double>>() {
					@Override
					public TableCell<Person, Double> call(TableColumn<Person, Double> param) {
						return new ProgressBarTableCell<Person>();
					}
				});

		final TableColumn<Person, Long> actionColumn = new TableColumn<Person, Long>("Actions");
		actionColumn.setMaxWidth(60);
		actionColumn.setMinWidth(60);
		actionColumn.setCellValueFactory(new PropertyValueFactory<Person, Long>("userID"));
		actionColumn
				.setCellFactory(new Callback<TableColumn<Person, Long>, TableCell<Person, Long>>() {
					@Override
					public TableCell<Person, Long> call(TableColumn<Person, Long> param) {
						return new TableCell<Person, Long>() {
							private HBox buttons;

							{
								ImageView acceptImg = new ImageView(new Image(getClass()
										.getResourceAsStream("check.png")));
								ImageView deleteImg = new ImageView(new Image(getClass()
										.getResourceAsStream("delete.png")));

								acceptImg.setCursor(Cursor.HAND);
								deleteImg.setCursor(Cursor.HAND);

								buttons = new HBox(3);
								buttons.setAlignment(Pos.CENTER);
								buttons.getChildren().addAll(acceptImg, deleteImg);
								setGraphic(buttons);
								setAlignment(Pos.CENTER);
							}

							@Override
							protected void updateItem(Long item, boolean empty) {
								super.updateItem(item, empty);
								setGraphic(empty ? null : buttons);
							}
						};
					}
				});

		TableView<Person> tableView = new TableView<Person>();
		tableView.setEditable(true);
		tableView.setPrefWidth(800);
		// tableView.setRowFactory(ExpandOnMouseEventCellFactory.forTableView(1,
		// ExpandOnMouseEventTableRow.DEFAULT_EXPAND_HEIGHT,
		// MouseEvent.MOUSE_ENTERED, new
		// EventHandler<ExpandOnMouseEventTableRow.CellResizeEvent<Person>>() {
		// @Override public void handle(CellResizeEvent<Person> event) {
		// // show the short or long form of the adresses based on
		// // the expanded state of the row
		// TableCell<Person, String> cell = event.getCells().get(addressColumn);
		// if (cell.isEmpty()) return;
		// cell.setText(event.isExpanded() ? address : shortAddress);
		// ImageView image_48 = new ImageView(new
		// Image(getClass().getResourceAsStream("dude3.png")));
		// cell.setGraphic(event.isExpanded() ? image_48 : null);
		// }
		// }));
		tableView.setItems(personsList);
		tableView.getColumns().setAll(invitedColumn, nameColumn2, addressColumn, progressColumn,
				actionColumn);

		grid.add(Utils.createLabel("Complex TableView (uses many cell factories)"), 1, 0);
		grid.add(tableView, 1, 1);
		GridPane.setVgrow(tableView, Priority.ALWAYS);

		tab.setContent(grid);
	}
}