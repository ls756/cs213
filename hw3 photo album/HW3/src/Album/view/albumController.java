package Album.view;

import Album.MainApp;
import Album.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class albumController {
    @FXML
    private TableView<Album> albumList;
    @FXML
    private TableColumn<Album, String> nameColumn;

    @FXML
    private Label nameLabel;
    @FXML
    private Label numberLabel;
    @FXML
    private Label dateLabel;
    @FXML
    private Label rangeLabel;
    @FXML
    private TextField newName;
    
    private User U;
    private Stage dialogStage;
    private MainApp main;
  

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public albumController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the song list with the song column.
    	nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

    	// Clear person details.
        showAlbumDetail(null);

        // Listen for selection changes and show the song details when changed.
        albumList.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showAlbumDetail(newValue));
    }

    public void setMain(MainApp main) {
        this.main = main;
    }
    
    
    public void setUser(User U){
    	this.U = U;
    	albumList.setItems(U.getAlbumList());
    }
    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Fills all text fields to show details about the song.
     * If the specified song is null, all text fields are cleared.
     *
     * @param song the song or null
     */
    private void showAlbumDetail(Album al) {
        if (al != null) {
            // Fill the labels with info from the song object.
            nameLabel.setText(al.getName());
            numberLabel.setText(Integer.toString(al.getNop()));
            dateLabel.setText(al.getDoop());
            rangeLabel.setText(al.getRod());
        } else {
            // Song is null, remove all the text.
            nameLabel.setText("");
            numberLabel.setText("");
            dateLabel.setText("");
            rangeLabel.setText("");
        }
    }

    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new song.
     */
    @FXML
    private void handleCreate() {
    	main.showCreateAlbum(1,U,albumList); //model 1 means create
    }
    @FXML
    private void handleRename(){
    	main.showCreateAlbum(2,U,albumList); //model 2 means rename
    }
    

}