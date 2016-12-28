package Album.view;

import Album.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CreateUserController {
	@FXML
    private TextField nameField;
	
	private Stage dialogStage;
	
	private MainApp main;
	
	public CreateUserController() {
	}
	
	@FXML
	private void initialize() {
	}

	public void setStage(Stage dialogStage) {
	      this.dialogStage = dialogStage;
	}
	
	public void setMain(MainApp main){
		this.main = main;
	}

	public MainApp getMain(){
	   	return this.main;
	}
	
	public String getName() {
    	return nameField.getText();
    }
	
	@FXML
	private void handleCreate(){
		String user = nameField.getText();
		main.createUser(user);
		dialogStage.close();
	}
	
}
