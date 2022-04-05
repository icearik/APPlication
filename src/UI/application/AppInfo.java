package application;

import DataObjects.AppFunctions;
import DataObjects.AppInformation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
/**
 * convenience class. Used to display detailed view for applications
 * @author icearik
 *
 */
public class AppInfo {
	/**
	 * creates a detailed view for regular applications.
	 * @param app AppInformation Object
	 */
	public static void display(AppInformation app) {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(app.getAppID());
		window.setMinWidth(250);
		Label dev = new Label("Developer: " + app.getDeveloper());
		TextArea brief = new TextArea(app.getBriefDesc());
		brief.setMaxWidth(200);
		brief.setMaxHeight(50);
		TextArea detailed = new TextArea(app.getDetailedDesc());
		detailed.setMaxWidth(200);
		detailed.setMaxHeight(100);
		Button close = new Button("Back to Table");
		close.setOnAction(e -> window.close());
		String path = "https://i.pinimg.com/originals/05/eb/89/05eb8920feaeea11115158fe2602d051.png";
		Image image = new Image(app.getImageID());
		ImageView imageView = new ImageView(image);
		imageView.setFitWidth(30);
		imageView.setFitHeight(30);
		VBox layout = new VBox(10);
		layout.getChildren().addAll(dev, brief, detailed, imageView, close);
		layout.setAlignment(Pos.CENTER);
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
	}
	/**
	 * Creates detailed view for pending applications
	 * @param app pending app
	 * @throws Exception if the app cannot be approved, throws exception
	 */
	public static void displayAndInsert(AppInformation app) throws Exception {
		AppFunctions functions = new AppFunctions();
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(app.getAppID());
		window.setMinWidth(250);
		Label dev = new Label("Developer: " + app.getDeveloper());
		TextArea brief = new TextArea(app.getBriefDesc());
		brief.setMaxWidth(200);
		brief.setMaxHeight(50);
		TextArea detailed = new TextArea(app.getDetailedDesc());
		detailed.setMaxWidth(200);
		detailed.setMaxHeight(100);
		Button close = new Button("Back to Table");
		close.setOnAction(e -> window.close());
		Button approve = new Button("approve");
		approve.setOnAction(e -> {
			try {
				functions.insert(app.getAppID());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		String path = "https://i.pinimg.com/originals/05/eb/89/05eb8920feaeea11115158fe2602d051.png";
		Image image = new Image(path);
		ImageView imageView = new ImageView(image);
		imageView.setFitWidth(30);
		imageView.setFitHeight(30);
		VBox layout = new VBox(10);
		layout.getChildren().addAll(dev, brief, detailed, imageView, approve, close);
		layout.setAlignment(Pos.CENTER);
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
	}
}
