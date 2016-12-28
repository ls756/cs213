/*
 * Le Sun 		RUID: 154008594
 * Shuhan Liu 	RUID: 154007082
 */

package songlib.view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import songlib.model.Song;

/**
 * Dialog to edit details of a song.
 *
 * @author
 */
public class EditDialogController {

    @FXML
    private TextField nameField;
    @FXML
    private TextField artistField;
    @FXML
    private TextField albumField;
    @FXML
    private TextField yearField;

    private Stage dialogStage;
    private Song song;
    private boolean okClicked = false;
    private boolean cancelClicked = true;
    private boolean init = false;
    private static ObservableList<Song> songList = FXCollections.observableArrayList();

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	if(init == false){
    		init=true;
    		File f = new File("cache.txt");
    		if(f.exists()&&!f.isDirectory()){
    			BufferedReader br = null;
    			try{
    				String line;
    				br = new BufferedReader(new FileReader("cache.txt"));
    				while((line=br.readLine())!=null){
    					String[] parts = line.split("/");
    					Song s = new Song(parts[0]);
    					s.setArtist(parts[1]);
    					s.setAlbum(parts[2]);
    					s.setYear(Integer.parseInt(parts[3]));
    					songList.add(s);
    				}
    			}catch(IOException e){
    				e.printStackTrace();
    			}
    		}
    		else{
    			System.out.println("The file does not exist");
    		}
    	}
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
     * Sets the song to be edited in the dialog.
     *
     * @param song
     */
    public void setSong(Song song) {

        this.song = song;

        nameField.setText(song.getName());
        artistField.setText(song.getArtist());
        albumField.setText(song.getAlbum());
        yearField.setText(Integer.toString(song.getYear()));

    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Returns true if the user clicked Cancel, false otherwise.
     *
     * @return
     */
    public boolean isCancelClicked() {
        return cancelClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private boolean handleOk() {

    	if (isInputValid()) {
    		String newName = nameField.getText();
    		String newArtist = artistField.getText();
    		if(songList.isEmpty()){
    			song.setName(nameField.getText());
    			song.setArtist(artistField.getText());
    			song.setAlbum(albumField.getText());
    			song.setYear(Integer.parseInt(yearField.getText()));
    			songList.add(song);
    			okClicked = true;
    			cancelClicked = false;
        		dialogStage.close();
        		return true;
    		}
    		else{
    			int i = 0;
    			while(i<songList.size()){
    				if(newName.compareTo(songList.get(i).getName())==0){
    					if(newArtist.compareTo(songList.get(i).getArtist())==0){
    						okClicked = false;
    						cancelClicked = false;
    						dialogStage.close();
    						return false;
    					}
    				}
    				i++;
    			}
        		song.setName(nameField.getText());
        		song.setArtist(artistField.getText());
        		song.setAlbum(albumField.getText());
        		song.setYear(Integer.parseInt(yearField.getText()));
        		songList.add(song);
        		okClicked = true;
        		cancelClicked = false;
            	dialogStage.close();
            	return true;

    		}


    	}
    	return true;

    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {

        String errorMessage = "";

        if (nameField.getText() == null || nameField.getText().length() == 0) {
            errorMessage += "No valid name!\n";
        }
        if (artistField.getText() == null || artistField.getText().length() == 0) {
            errorMessage += "No valid artist name!\n";
        }

        if (!checkYear(yearField.getText())) {
            errorMessage += "No valid artist name!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkYear(String year){
    	int i = 0;
    	while(i<year.length()){
    		if(!Character.isDigit(year.charAt(i))){
    			return false;
    		}
    		i++;
    	}
    	return true;
    }
}