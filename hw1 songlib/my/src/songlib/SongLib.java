/*
 * Le Sun 		RUID: 154008594
 * Shuhan Liu 	RUID: 154007082
 */

package songlib;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import songlib.model.Song;
import songlib.view.EditDialogController;
import songlib.view.SongController;

public class SongLib extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private boolean init = false;

    /**
     * The data as an observable list of Songs.
     */
    private ObservableList<Song> songList = FXCollections.observableArrayList();

    /**
     * Constructor
     */
    public SongLib() {
    }

    /**
     * Returns the data as an observable list of Songs.
     * @return
     */
    public ObservableList<Song> getSongList() {
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
    	}
    	return songList;
    }

    /**
     * set the primaryStage.
     * it is not resizable.
     */

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Song Library");
        primaryStage.setResizable(false);

        initRootLayout();
        showSongLibrary();
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SongLib.class.getResource("view/rootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the songLibrary inside the root layout.
     */
    public void showSongLibrary() {
        try {
            // Load songLibrary.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SongLib.class.getResource("view/Song.fxml"));
            AnchorPane Song = (AnchorPane) loader.load();

            // Set songLibrary into the center of root layout.
            rootLayout.setCenter(Song);

            // Give the controller access to the SongLib.
            SongController controller = loader.getController();
            controller.setSongLib(this);;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Opens a dialog to edit details for the specified song.
     * The function returns two boolean value.
     * first one is to check if the user clicks OK.
     * second one is to check if the user clicks cancel.
     *
     * @return true if the user clicked OK, false otherwise.
     */
    public boolean[] showEditDialog(Song song) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SongLib.class.getResource("view/EditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Song");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the song into the controller.
            EditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setSong(song);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            boolean[] check = new boolean[2];
            check[0] = controller.isOkClicked();
            check[1] = controller.isCancelClicked();
            return check;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Opens a dialog to show the error message when User is deleting data without selecting
     */
    public void showErrorDialog() {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SongLib.class.getResource("view/ErrorDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Warning");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Opens a dialog to show the error message when User is adding
     * a song that existed already
     */
    public void showRepeatDialog() {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SongLib.class.getResource("view/RepeatDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Warning");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    @Override
    public void stop(){
    	try{
    		PrintWriter writer = new PrintWriter("cache.txt","UTF-8");
    		for(Song o:songList){
    			writer.println(o.getName()+"/"+o.getArtist()+"/"+o.getAlbum()+"/"+o.getYear());
    		}
    		writer.close();
    	}
    	catch(IOException e){
    		e.printStackTrace();
    	}
    }

    public static void main(String[] args) {
        launch(args);
    }
}