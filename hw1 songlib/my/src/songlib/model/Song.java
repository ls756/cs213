/*
 * Le Sun 		RUID: 154008594
 * Shuhan Liu 	RUID: 154007082
 */

package songlib.model;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Model class for a Song.
 *
 */
public class Song {

    private final StringProperty name;
    private final StringProperty artist;
    private final StringProperty album;
    private final IntegerProperty year;

    /**
     * Default constructor.
     */
    public Song() {
        this(null);
    }

    /**
     * Constructor with some initial data.
     *
     * @param name
     */
    public Song(String name) {
        this.name = new SimpleStringProperty(name);

        // Some initial dummy data, just for convenient testing.
        this.artist = new SimpleStringProperty("");
        this.year = new SimpleIntegerProperty();
        this.album = new SimpleStringProperty("");
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getArtist() {
        return artist.get();
    }

    public void setArtist(String artist) {
        this.artist.set(artist);
    }

    public StringProperty artistProperty() {
        return artist;
    }

    public String getAlbum() {
        return album.get();
    }

    public void setAlbum(String album) {
        this.album.set(album);
    }

    public StringProperty albumProperty() {
        return album;
    }

    public int getYear() {
        return year.get();
    }

    public void setYear(int year) {
        this.year.set(year);
    }

    public IntegerProperty yearProperty() {
        return year;
    }

}