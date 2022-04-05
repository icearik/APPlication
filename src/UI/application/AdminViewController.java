package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import DataObjects.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;

import javafx.scene.control.TableView;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableColumn;

public class AdminViewController {
	@FXML
	private MenuItem closeMenu;
	@FXML
	private Button signOut;
	@FXML
	private MenuItem rating;
	@FXML
	private MenuItem date;
	@FXML
	private Button addBut;
	@FXML
	private Button pendingBut;
	@FXML
	private TextField searchBar;
	@FXML
	private Button searchBut;
	@FXML
	private TableView table;
	@FXML
	private TableColumn appCol;
	@FXML
	private TableColumn comCol;
	@FXML
	private TableColumn briefCol;
	@FXML
	private TableColumn detCol;
	@FXML
	private TableColumn verCol;
	@FXML
	private AppFunctions fs;
	@FXML 
	private boolean changed = false;
	@FXML
	private ObservableList<AppInformation> pending = FXCollections.observableArrayList(
			new AppInformation("PendingApp1", "Company1"), new AppInformation("PendingApp2", "Company2"),
			new AppInformation("PendingApp3", "Company3"), new AppInformation("PendingApp4", "Company4"),
			new AppInformation("PendingApp5", "Company5"), new AppInformation("PendingApp6", "Company6"),
			new AppInformation("PendingApp7", "Company7"), new AppInformation("PendingApp8", "Company8"),
			new AppInformation("PendingApp9", "Company9"), new AppInformation("PendingApp10", "Company10"));
	@FXML
	private ObservableList<AppInformation> data;
	@FXML
	private FilterFunctions functions;
	/**
	 * initialize method runs first. Data table is initialized. FilterFunctions object is created
	 * @throws Exception
	 */
	@FXML
	void initialize() throws Exception {
		functions = new FilterFunctions();
		fs = new AppFunctions();
		data = FXCollections.observableList(new ArrayList());
		functions.setTopFilter();
		// Get the information about application with default sort
		List<String> list = functions.getResultsPage(1);
		for (int i = 0; i < list.size(); i++) {
			String app1 = list.get(i);
			AppInformation ap = new AppInformation("dum", "dum");
			ap.getInformation(app1);
			data.add(ap);
		}
		// Set Factories to obtain propertief of AppInformation objects
		appCol.setCellValueFactory(
	            new PropertyValueFactory<AppInformation, String>("appID"));
		comCol.setCellValueFactory(
	            new PropertyValueFactory<AppInformation, String>("developer"));
		briefCol.setCellValueFactory(
	            new PropertyValueFactory<AppInformation, String>("briefDesc"));
		detCol.setCellValueFactory(
	            new PropertyValueFactory<AppInformation, String>("detailedDesc"));
		verCol.setCellValueFactory(
	            new PropertyValueFactory<AppInformation, String>("version"));
		// set event handler on row double clicked. Opens a detailed view of the application

		table.setRowFactory(tv -> {
		    TableRow<AppInformation> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (! row.isEmpty() && event.getButton()==MouseButton.PRIMARY 
		             && event.getClickCount() == 2) {
		        	AppInformation clickedRow = row.getItem();
		        	if (table.getItems().equals(pending)) {
		        		try {
							AppInfo.displayAndInsert(clickedRow);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		        	}
		        	else {
			            AppInfo.display(clickedRow);
		        	}
		        }
		    });
		    return row ;
		});
		table.setItems(data);
	}

	/**
	 * Event Listener on MenuItem[#closeMenu].onAction. Closes the application
	 * @param event close menuitem pressed
	 */
	@FXML
	public void closeApp(ActionEvent event) {
		// TODO Autogenerated
		System.exit(0);
	}

	/**
	 * Event Listener on Button[#signOut].onAction. Signs out from the user view
	 * @param event sign out button clicked
	 * @throws Exception
	 */
	@FXML
	public void logout(ActionEvent event) throws Exception {
		// TODO Autogenerated
		Stage primaryStage = new Stage();
		Parent root;
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Sample.fxml"));
		root = (Parent) fxmlLoader.load();
		Scene scene = new Scene(root, 625, 375);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
	}

	/**
	 * Event Listener on MenuItem[#rating].onAction. Sorts applications by rating
	 * @param event Byrating menuItem clicked
	 * @throws Exception Exception if the form cannot be opened
	 */
	@FXML
	public void byRating(ActionEvent event) throws Exception {
		// TODO Autogenerated
		ObservableList<AppInformation> result = FXCollections.observableList(new ArrayList());
		functions.setRatingFilter();
		List<String> list = functions.getResultsPage(1);
		for (int i = 0; i < list.size(); i++) {
			String app1 = list.get(i);
			AppInformation ap = new AppInformation("1", "1");
			ap.getInformation(app1);
			result.add(ap);
		}
		table.setItems(result);
	}

	/**
	 *  Event Listener on MenuItem[#date].onAction. Sorts applications by  date created
	 * @param event Bydate menuitem clicked
	 * @throws Exception if data cannot be obtained, throws exception
	 */
	@FXML
	public void byDate(ActionEvent event) throws Exception {
		// TODO Autogenerated
		ObservableList<AppInformation> result = FXCollections.observableList(new ArrayList());
		functions.setDateFilter();
		List<String> list = functions.getResultsPage(1);
		for (int i = 0; i < list.size(); i++) {
			String app1 = list.get(i);
			AppInformation ap = new AppInformation("1", "1");
			ap.getInformation(app1);
			result.add(ap);
		}
		table.setItems(result);
	}

	/**
	 * Event Listener on Button[#addBut].onAction. Opens add application form
	 * @param event add App button pressed
	 * @throws Exception
	 */
	@FXML
	public void addApp(ActionEvent event) throws Exception {
		// TODO Autogenerated
		Stage primaryStage = new Stage();
		Parent root;
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddApp.fxml"));
		root = (Parent) fxmlLoader.load();
		Scene scene = new Scene(root, 600, 300);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * Event Listener on Button[#pendingBut].onAction. Shows the pending application
	 * @param event pending button pressed
	 * @throws Exception
	 */
	@FXML
	public void changeTable(ActionEvent event) throws Exception {
		// TODO Autogenerated
		if (!changed) {
			pending = FXCollections.observableList(new ArrayList());
			functions.setSubmissions();
			List<String> list = functions.getResultsPage(1);
			for (int i = 0; i < list.size(); i++) {
				String app1 = list.get(i);
				AppInformation ap = new AppInformation("dum", "dum");
				ap.getSubmissionInformation(app1);
				pending.add(ap);
			}
			table.setItems(pending);
			pendingBut.setText("Back to apps");
			changed = !changed;
		} else {
			initialize();
			pendingBut.setText("Pending apps");
			changed = !changed;
		}
	}

	/**
	 * Event Listener on Button[#searchBut].onAction. Makes a search and changes the table accordingly
	 * @param event search button clicked
	 * @throws Exception
	 */
	@FXML
	public void search(ActionEvent event) throws Exception {
		// TODO Autogenerated
		ObservableList<AppInformation> result = FXCollections.observableList(new ArrayList());
		functions.setSearch(searchBar.getText());
		List<String> list = functions.getResultsPage(1);
		for (int i = 0; i < list.size(); i++) {
			String app1 = list.get(i);
			AppInformation ap = new AppInformation("1", "1");
			ap.getInformation(app1);
			result.add(ap);
		}
		table.setItems(result);
	}
}