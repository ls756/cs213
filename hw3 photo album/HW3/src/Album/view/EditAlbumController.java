package Album.view;

import Album.MainApp;
import Album.model.Album;
import Album.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class EditAlbumController {
	@FXML
    private TextField newName;
	
	private Stage dialogStage;
	
	private MainApp main;
	
	private User U;
	
	private TableView<Album> albumList;
	
	private int model=0; // 1 for create, 2 for rename.
	
	public EditAlbumController() {
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

	public void setModel(int model){
	    this.model = model;
	}
	 
	public MainApp getMain(){
	   	return this.main;
	}
	
	public String getName() {
    	return newName.getText();
    }
	
	public void setUser(User U){
    	this.U = U;
    }
	
	public void setList(TableView<Album> albumList){
		this.albumList=albumList;
	}
	@FXML
    private void handleSave(){
    	String temp = newName.getText();
    	
    	if(model == 1){      //means create
    		//System.out.println(U);
    		if(U.getAlbumList()!=null){
    			boolean exist = false;
    			for(Album A:U.getAlbumList()){
    				if(A.getName().equals(temp)){
    					System.out.println("Already Exist!");
    					exist = true;
    					break;
    				}
    			}
    			if(exist==false){
    				Album A = new Album(temp);
    				U.setAlbumList(A); //add the album to this user's album list;
    			}
    		}
    		else{
    			Album A = new Album(temp);
				U.setAlbumList(A); //add the album to this user's album list;
				
    		}
    		//System.out.println(U.getAlbumList());
    	}
    		
    	if(model == 2){      //means rename
    		int selectedIndex = albumList.getSelectionModel().getSelectedIndex();
    		if(selectedIndex>=0){
    			boolean exist = false;
    			for(Album A:U.getAlbumList()){
    				if(A.getName().equals(temp)){
    					System.out.println("Already Exist!");
    					exist = true;
    					break;
    				}
    			}
    			if(exist==false){
    				albumList.getSelectionModel().getSelectedItem().setName(temp);
    			}
    		}
    	}  
    	dialogStage.close();
    }
	
}
