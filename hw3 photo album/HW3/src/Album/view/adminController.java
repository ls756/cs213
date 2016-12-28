package Album.view;

import java.util.Collections;

import Album.MainApp;
import Album.model.Album;
import Album.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class adminController {
	@FXML
    private TableView<User> userList;
    @FXML
    private TableColumn<User, String> nameColumn;

    private MainApp main;


    public adminController(){

    }

    @FXML
    private void initialize() {
        // Initialize the song list with the song column.
    	nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

    }

    public void setUser(MainApp main) {
        this.main = main;

        // Add observable list data to the table
        userList.setItems(main.getUserList());

    }
    
    @FXML
    private void handleDelete(){
    	int selectedIndex = userList.getSelectionModel().getSelectedIndex();
    	System.out.println(selectedIndex);
    	if(selectedIndex>=0){
    		userList.getItems().remove(selectedIndex);
    		userList.getSelectionModel().select(selectedIndex);
       	}
    }
    
    
    
    @FXML
    public void handleCreate(){
    		
    	main.showCreateUser();
    	
    	
//    	String user = "";

//    	User newUser = new User();
//        boolean[] check = new boolean[2];
//        check = songlib.showEditDialog(newSong);
//        boolean okClicked = check[0];
//        boolean cancelClicked = check[1];
//        int index = 0;
//        if (!cancelClicked) {
//        	if(okClicked){
//        		songlib.getSongList().add(newSong);
//        		int i=0;
//        		while(i<songlib.getSongList().size()){
//        			if(newSong == songlib.getSongList().get(i)){
//        				index = i;
//        				break;
//        			}
//        			i++;
//        		}
//        		myComparator comparator = new myComparator();
//        		Collections.sort(songlib.getSongList(), comparator);
//        		songList.getSelectionModel().select(index);
//        	}else{
//        		songlib.showRepeatDialog();
//        	}
//        }
    }
}