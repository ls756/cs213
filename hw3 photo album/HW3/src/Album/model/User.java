package Album.model;

import java.util.ArrayList;
import Album.model.*;
import javafx.collections.ObservableList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

public class User {

	private final StringProperty username;
    private final ObservableList<Album> albumList= FXCollections.observableArrayList();

    public User(){
    	this(null);
    }

    public User(String username){
    	this.username = new SimpleStringProperty(username);
    	Album A = new Album("Sample");
    	albumList.add(A);
    }

    public String getName() {
        return username.get();
    }

    public void setName(String name) {
        this.username.set(name);
    }

    public StringProperty nameProperty() {
        return username;
    }

    public ObservableList<Album> getAlbumList() {
        return albumList;
    }

    public void setAlbumList(Album album) {
        this.albumList.add(album);
    }

    public ObservableList<Album> AlbumList() {
        return albumList;
    }

}
