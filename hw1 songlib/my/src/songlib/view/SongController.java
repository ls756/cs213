/*
 * Le Sun 		RUID: 154008594
 * Shuhan Liu 	RUID: 154007082
 */

package songlib.view;

import java.util.Collections;
import java.util.Comparator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import songlib.SongLib;
import songlib.model.Song;

public class SongController {
    @FXML
    private TableView<Song> songList;
    @FXML
    private TableColumn<Song, String> nameColumn;

    @FXML
    private Label nameLabel;
    @FXML
    private Label artistLabel;
    @FXML
    private Label albumLabel;
    @FXML
    private Label yearLabel;

    // Reference to the main application.
    private SongLib songlib;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public SongController() {
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
        showSongList(null);

        // Listen for selection changes and show the song details when changed.
        songList.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showSongList(newValue));
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setSongLib(SongLib songlib) {
        this.songlib = songlib;

        // Add observable list data to the table
        songList.setItems(songlib.getSongList());

      //it shows the first song
        songList.getSelectionModel().select(0);
        showSongList(songList.getSelectionModel().getSelectedItem());
    }

    /**
     * Fills all text fields to show details about the song.
     * If the specified song is null, all text fields are cleared.
     *
     * @param song the song or null
     */
    private void showSongList(Song song) {
        if (song != null) {
            // Fill the labels with info from the song object.
            nameLabel.setText(song.getName());
            artistLabel.setText(song.getArtist());
            albumLabel.setText(song.getAlbum());
            yearLabel.setText(Integer.toString(song.getYear()));
        } else {
            // Song is null, remove all the text.
            nameLabel.setText("");
            artistLabel.setText("");
            yearLabel.setText("");
            albumLabel.setText("");
        }
    }

    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void handleDeleteSong() {
        int selectedIndex = songList.getSelectionModel().getSelectedIndex();
        if(selectedIndex >= 0){
        	songList.getItems().remove(selectedIndex);
        	songList.getSelectionModel().select(selectedIndex);
        }else{
        	songlib.showErrorDialog();
        }
    }

    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new song.
     */
    @FXML
    private void handleNewSong() {
        Song newSong = new Song();
        boolean[] check = new boolean[2];
        check = songlib.showEditDialog(newSong);
        boolean okClicked = check[0];
        boolean cancelClicked = check[1];
        int index = 0;
        if (!cancelClicked) {
        	if(okClicked){
        		songlib.getSongList().add(newSong);
        		int i=0;
        		while(i<songlib.getSongList().size()){
        			if(newSong == songlib.getSongList().get(i)){
        				index = i;
        				break;
        			}
        			i++;
        		}
        		myComparator comparator = new myComparator();
        		Collections.sort(songlib.getSongList(), comparator);
        		songList.getSelectionModel().select(index);
        	}else{
        		songlib.showRepeatDialog();
        	}
        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected song.
     */
    @FXML
    private void handleEditSong() {
        Song selectedSong = songList.getSelectionModel().getSelectedItem();
        if (selectedSong != null) {
            boolean[] check = new boolean[2];
            check = songlib.showEditDialog(selectedSong);
        	boolean okClicked = check[0];
        	boolean cancelClicked = check[1];
            if(!cancelClicked){
            	if (okClicked){
            		showSongList(selectedSong);
            		myComparator comparator = new myComparator();
            		Collections.sort(songlib.getSongList(), comparator);
            	}else{
            		songlib.showRepeatDialog();
            	}
            }
        }
    }
}