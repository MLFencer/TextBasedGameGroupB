package Controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sun.rmi.runtime.Log;
import org.json.JSONObject;

public class LoginController {
	@FXML private TextField txtUsername, txtEmail;
	@FXML private PasswordField txtPassword, txtConfirmPassword;
	@FXML private Button btnLogin, btnPos, btnBack, btnSubmit;
	@FXML private Label lblActionStatus, lblStatusReg;
	@FXML private Hyperlink linkRegister;

	@FXML
	public void clickOnLogin(ActionEvent event) throws Exception
	{
		String username = txtUsername.getText();
		String password = txtPassword.getText();
		
		if(requestUserEntry(username, password))
		{
			lblActionStatus.setText("Success!");
			lblActionStatus.setTextFill(Color.GREEN);
		    Stage stage = (Stage) btnLogin.getScene().getWindow();
		    stage.close();
		    mainMenuPage(username);
		}
		else
		{
			lblActionStatus.setText("Invalid Username or Password!");
			lblActionStatus.setTextFill(Color.FIREBRICK);
		}
		txtUsername.setText("");
		txtPassword.setText("");

	}
	
	@FXML
	public void clickOnRegister(ActionEvent event) throws Exception
	{
		Stage stage = (Stage) linkRegister.getScene().getWindow();
		stage.close();
		try 
		{
			registerPage();
		} catch (Exception e) {
			e.printStackTrace();
		}		        
	}

	@FXML
	public void clickOnSubmit(ActionEvent event) throws Exception
	{
		String username = txtUsername.getText();
		String password = txtPassword.getText();
		if(username.equals(""))
		{
			lblStatusReg.setText("Must have a Username!");
		}
		else if(!password.equals(txtConfirmPassword.getText()))
		{
			lblStatusReg.setText("Passwords Dont Match!");
		}
		else
		{
			URL url = new URL("http://www.db.nofool.net/add.php?user=" + username + "&password=" + password);
			BufferedReader in = new BufferedReader(
		            new InputStreamReader(url.openStream()));
		    String inputLine;
		    inputLine = in.readLine();
		    in.close();
		    if(inputLine.substring(1, inputLine.length()-1).equals("false"))
		    {
		    	lblStatusReg.setText("User Already Exists!");
		    }
		    else
		    {
		    	Stage stage = (Stage) btnSubmit.getScene().getWindow();
				stage.close();
				Stage primaryStage = new Stage();
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/playerLoginView.fxml"));
				Parent root = fxmlLoader.load();
				LoginController controller = fxmlLoader.<LoginController>getController();
				controller.lblActionStatus.setText("User Succesfully Registered!");
				Scene scene = new Scene(root,500,250);
				primaryStage.setScene(scene);
				primaryStage.show();
		    }
		}
		txtUsername.setText("");
		txtPassword.setText("");
		txtConfirmPassword.setText("");
	}
	@FXML 
	public void clickOnBack(ActionEvent event) throws Exception
	{
		Stage stage = (Stage) btnBack.getScene().getWindow();
		stage.close();
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/View/playerLoginView.fxml"));
		Scene scene = new Scene(root,500,250);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public void mainMenuPage(String user) throws Exception
	{
		Stage primaryStage = new Stage();
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/mainMenuView.fxml"));
		Parent root = fxmlLoader.load();
		MainMenuController controller = fxmlLoader.<MainMenuController>getController();
		controller.setUsername(user);
		Scene scene = new Scene(root,296,270);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	@FXML
	public void registerPage() throws Exception
	{
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/View/newUserView.fxml"));
		Scene scene = new Scene(root,400,400);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	private boolean requestUserEntry(String user, String pass) throws Exception
	{		
		URL url = new URL("http://www.db.nofool.net/getPassword.php?user=" + user);
	    BufferedReader in = new BufferedReader(
	            new InputStreamReader(url.openStream()));
	    String inputLine;
	    inputLine = in.readLine();
	      
	    String returnString = "";
	    JSONObject json_data = new JSONObject(inputLine);
	    returnString = json_data.getString("password");
	    in.close();
	    if(returnString.equals(pass))
	    	return true;
	    else
	    	return false;
	 }
}
