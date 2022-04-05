package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import javafx.scene.control.PasswordField;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import DataObjects.*;
public class LoginController {
	@FXML
	private TextField txtUsername;
	@FXML
	private PasswordField txtPassword;
	@FXML
	private Label lblStatus;
	@FXML
	private Label label1;
	@FXML
	private Label signUpLabel;
	@FXML
	private UserFunctions functions;
	@FXML
	/**
	 * initialize UserFunctions object
	 */
	void initialize() {
		// to do
		functions = new UserFunctions();
	}
	/**
	 * login event handler. If the user exists, opens a userview for regular users and 
	 * admin view for moderators and admins
	 * @param event login button pressed
	 * @throws Exception
	 */
	@FXML
	public void login(ActionEvent event) throws Exception {
		// Currently checks if the username is user and password is pass
		if (functions.confirmUser(txtUsername.getText(), txtPassword.getText())) {
			Stage primaryStage = new Stage();
			String status = functions.getStatus(txtUsername.getText());
			Parent root;
			String path = ((status != null && status.equals("admin"))?"AdminView.fxml" : "MainApp.fxml");
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(path));
			root = (Parent) fxmlLoader.load();
			Scene scene = new Scene(root,625,375);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			Node  source = (Node)  event.getSource(); 
		    Stage stage  = (Stage) source.getScene().getWindow();
		    stage.close();
		} else {
			lblStatus.setText("Login failed");
		}
	}
	/**
	 * Event Listener on Label[#signUpLabel].onMouseClicked. If the user doesn't have an
	 * account yet it closes the current form and opens registration form.
	 * @param event
	 * @throws Exception
	 */
	// 
	@FXML
	public void openSignUp(MouseEvent event) throws Exception {
		// TODO Autogenerated
		Stage primaryStage = new Stage();
		Parent root;
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Register.fxml"));
		root = (Parent) fxmlLoader.load();
		Scene scene = new Scene(root,300,400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
		Node  source = (Node)  event.getSource(); 
	    Stage stage  = (Stage) source.getScene().getWindow();
	    stage.close();
	}
}