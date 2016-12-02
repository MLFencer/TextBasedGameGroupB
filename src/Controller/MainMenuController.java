package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class MainMenuController {
	@FXML
	private Button btnSingleplayer, btnMultiplayer, btnCharacterCreation, btnLogout;
	private String username;
	
	public void clickSingleplayer(ActionEvent event) throws Exception
	{
		Stage stage = (Stage) btnSingleplayer.getScene().getWindow();
		stage.close();
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/View/mainPlayerView.fxml"));
		Scene scene = new Scene(root,600,500);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	// Multiplayer!!!
	public void clickMultiplayer(ActionEvent event) throws Exception
	{
		Stage stage = (Stage) btnMultiplayer.getScene().getWindow();
		stage.close();
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/View/mainPlayerView.fxml"));
		Scene scene = new Scene(root,600,500);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void clickLogout(ActionEvent event) throws Exception
	{
		Stage stage = (Stage) btnLogout.getScene().getWindow();
		stage.close();
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/View/playerLoginView.fxml"));
		Scene scene = new Scene(root,500,250);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void setUsername(String user)
	{
		this.username = user;
	}
}
