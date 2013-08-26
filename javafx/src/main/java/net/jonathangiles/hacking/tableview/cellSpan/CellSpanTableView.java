package net.jonathangiles.hacking.tableview.cellSpan;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;

/**
 *
 */
public class CellSpanTableView<S> extends TableView<S> {
	public CellSpanTableView() {
		super();
		getStyleClass().add(DEFAULT_STYLE_CLASS);
		this.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		registerCellListener();
	}

	public CellSpanTableView(ObservableList<S> items) {
		super(items);
		getStyleClass().add(DEFAULT_STYLE_CLASS);
		this.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		registerCellListener();
	}

	// --- Span Model
	private ObjectProperty<SpanModel> spanModel = new SimpleObjectProperty<SpanModel>(this,
			"spanModel");

	public final ObjectProperty<SpanModel> spanModelProperty() {
		return spanModel;
	}

	public final void setSpanModel(SpanModel value) {
		spanModelProperty().set(value);
	}

	public final SpanModel getSpanModel() {
		return spanModel.get();
	}

	/***************************************************************************
	 * * Stylesheet Handling * *
	 **************************************************************************/
	private static final String DEFAULT_STYLE_CLASS = "cell-span-table-view";

	private boolean isFirstRun = true;

	@Override
	protected void layoutChildren() {
		// ugly hack to enable adding the cell-span.css file to the scenegraph
		// without requiring user intervention
		if (isFirstRun) {
			Scene scene = getScene();
			if (scene != null) {
				ObservableList<String> stylesheets = scene.getStylesheets();
				String cssPath = CellSpanTableView.class.getResource("cell-span.css")
						.toExternalForm();
				if (!stylesheets.contains(cssPath)) {
					stylesheets.add(cssPath);
					isFirstRun = false;
				}
			}
		}
		super.layoutChildren();
	}

	public void registerCellListener() {
		final TableView.TableViewSelectionModel<S> sm = this.getSelectionModel();
		sm.getSelectedCells().addListener(new ListChangeListener<TablePosition>() {
			@Override
			public void onChanged(Change<? extends TablePosition> change) {
				final CellSpanTableView<S> tableView = CellSpanTableView.this;
				if (sm.getSelectedCells().isEmpty()) {
					return;
				}
				final TablePosition tp = sm.getSelectedCells().get(0);
				if (tp == null) {
					return;
				}
				CellSpan cellSpan = tableView.getSpanModel().getCellSpanAt(
						tp.getRow(), tp.getColumn());
				if(null == cellSpan) {
					return;
				}
				final int rowSpan = cellSpan.getRowSpan();
				if(rowSpan < 2) {
					return;
				}
				for(int row = tp.getRow(); row < tp.getRow() + rowSpan; row++) {
					if(sm.isSelected(row)) {
						continue;
					}
					tableView.getSelectionModel().select(row);
					System.out.println(row);
				}
			}
		});
	}
}
