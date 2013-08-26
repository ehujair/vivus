package org.vivus.javafx2.popup;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

public class PopupExample extends Application {
	public static final String STACK_TRACE = "org.apache.thrift.TApplicationException: Internal error processing resultFunc \n"
			+ "\t at org.apache.thrift.TApplicationException.read(TApplicationException.java:108)                 \n"
			+ "\t at org.apache.thrift.TServiceClient.receiveBase(TServiceClient.java:71)                         \n"
			+ "\t at org.vivus.thrift.test.TestService$Client.recv_resultFunc(TestService.java:138)               \n"
			+ "\t at org.vivus.thrift.test.TestService$Client.resultFunc(TestService.java:124)                    \n"
			+ "\t at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)                                  \n"
			+ "\t at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:39)                \n"
			+ "\t at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:25)        \n"
			+ "\t at java.lang.reflect.Method.invoke(Method.java:597)                                             \n"
			+ "\t at org.vivus.thrift.impl.NullClientInvocationHandler.invoke(NullClientInvocationHandler.java:28)\n"
			+ "\t at $Proxy4.resultFunc(Unknown Source)                                                           \n"
			+ "\t at org.vivus.thrift.socket.NullTest.testTException(NullTest.java:33)                            \n"
			+ "\t at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)                                  \n"
			+ "\t at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:39)                \n"
			+ "\t at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:25)        \n"
			+ "\t at java.lang.reflect.Method.invoke(Method.java:597)                                             \n"
			+ "\t at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:47)         \n"
			+ "\t at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)          \n"
			+ "\t at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:44)           \n"
			+ "\t at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)            \n"
			+ "\t at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:271)                                \n"
			+ "\t at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:70)            \n"
			+ "\t at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:50)            \n"
			+ "\t at org.junit.runners.ParentRunner$3.run(ParentRunner.java:238)                                  \n"
			+ "\t at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:63)                              \n"
			+ "\t at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:236)                            \n"
			+ "\t at org.junit.runners.ParentRunner.access$000(ParentRunner.java:53)                              \n"
			+ "\t at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:229)                             \n"
			+ "\t at org.junit.runners.ParentRunner.run(ParentRunner.java:309)                                    \n"
			+ "\t at org.eclipse.jdt.internal.junit4.runner.JUnit4TestReference.run(JUnit4TestReference.java:50)  \n"
			+ "\t at org.eclipse.jdt.internal.junit.runner.TestExecution.run(TestExecution.java:38)               \n"
			+ "\t at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:467)   \n"
			+ "\t at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:683)   \n"
			+ "\t at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.run(RemoteTestRunner.java:390)        \n"
			+ "\t at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.main(RemoteTestRunner.java:197)       \n";

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(final Stage primaryStage) throws Exception {
		final Stage popup = createPopup(primaryStage);
		FlowPane root = new FlowPane();
		Button show = new Button("show");
		show.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				popup.show();
			}
		});
		Button hide = new Button("hide");
		hide.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				popup.hide();
			}
		});
		root.getChildren().addAll(show, hide);
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}

	protected Stage createPopup(Window owner) {
		final Stage popup = new Stage();
		popup.initOwner(owner);
		popup.initStyle(StageStyle.TRANSPARENT);
		popup.initModality(Modality.WINDOW_MODAL);
		VBox vBox = new VBox();
		vBox.setAlignment(Pos.CENTER);
		vBox.setSpacing(10);
		vBox.setPrefWidth(300);
		vBox.setPrefHeight(200);
		vBox.setMaxWidth(Double.MAX_VALUE);
		vBox.setMaxHeight(Double.MAX_VALUE);
		Label messageLabel = new Label("出错啦", new ImageView("org/vivus/javafx2/popup/fail.png"));
		vBox.getChildren().add(messageLabel);

//		Label showHideLabel = new Label("show/hide");
//		final TextArea stackTrace = new TextArea(STACK_TRACE);
//		stackTrace.setEditable(false);
//		stackTrace.setVisible(false);
//		showHideLabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
//			@Override
//			public void handle(MouseEvent arg0) {
//				stackTrace.setVisible(!stackTrace.isVisible());
//			}
//		});
//		vBox.getChildren().add(showHideLabel);
//		vBox.getChildren().add(stackTrace);

		TitledPane titledPane = new TitledPane();
		titledPane.setText("异常栈");
		TextArea stackTrace = new TextArea(STACK_TRACE);
		stackTrace.setEditable(false);
		titledPane.setContent(stackTrace);
		titledPane.setExpanded(false);
		vBox.getChildren().add(titledPane);

		Button confirm = new Button("确　定");
		confirm.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				popup.hide();
			}
		});
		vBox.getChildren().add(confirm);
		popup.setScene(new Scene(vBox));
		return popup;
	}

}
