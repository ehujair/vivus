package org.vivus.adb;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import org.vivus.adb.util.FxmlUtil;

public class AdbApplication extends Application {
	private static final StageHolder STAGE_HOLDER = new StageHolder();

	public static Stage getStage() {
		return STAGE_HOLDER.stage;
	}

	private static class StageHolder {
		private Stage stage;

		public void setStage(Stage stage) {
			this.stage = stage;
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		STAGE_HOLDER.setStage(primaryStage);
		Parent root = FxmlUtil.load(Constants.FXML_MAIN);
		if (primaryStage.getScene() == null) {
			primaryStage.setScene(new Scene(root));
		}
		primaryStage.setTitle("NDA管理工具");
		ObservableList<Image> icons = primaryStage.getIcons();
//		icons.add(new Image(Constants.IMG_ICON));
		// primaryStage.setFullScreen(true); // 2.2
		// primaryStage.setMaximized(true); // 8
//		primaryStage.initStyle(StageStyle.TRANSPARENT);
		primaryStage.setIconified(false);
//		maximize(primaryStage);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
