package Album.view;

import Album.MainApp;
import Album.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class loginController {

    @FXML
    private TextField nameField;

    // Reference to the main application.
    private MainApp main;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public loginController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    public void setMain(MainApp main) {
        this.main = main;
    }

    public MainApp getMain(){
    	return this.main;
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public String getName() {
    	return nameField.getText();
    }

    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new song.
     */
    @FXML
    private void handleLogin() {
    	String user = nameField.getText();
    	if(user.equals("admin")){
    		main.showAdmin();
    	}
    	else{
    		boolean found = false;
    		for(User i : main.getUserList()){
    			if(i.getName().equals(user)){
    				main.showAlbumList(i);
    				found = true;
    			}
    		}
    		if(found == false){
    			System.out.println("User not found!!!");
    		}
    	}
    }

}