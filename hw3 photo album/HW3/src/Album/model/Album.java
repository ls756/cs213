package Album.model;

import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Model class for an Album.
 *
 */
public class Album {

    private final StringProperty name;
    private final IntegerProperty nop;	//number of photos
    private final StringProperty doop;	//date of the oldest photo
    private final StringProperty rod;	//range of dates
    private final ArrayList<Photo> photoList;

    /**
     * Default constructor.
     */
    public Album() {
        this(null);
    }

    /**
     * Constructor with some initial data.
     *
     * @param name
     */
    public Album(String name) {
        this.name = new SimpleStringProperty(name);

        // Some initial dummy data, just for convenient testing.
        this.doop = new SimpleStringProperty("");
        this.nop = new SimpleIntegerProperty();
        this.rod = new SimpleStringProperty("");
        this.photoList = null;
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

    public String getDoop() {
        return doop.get();
    }

    public void setDoop(String doop) {
        this.doop.set(doop);
    }

    public StringProperty doopProperty() {
        return doop;
    }

    public String getRod() {
        return rod.get();
    }

    public void setRod(String rod) {
        this.rod.set(rod);
    }

    public StringProperty rodProperty() {
        return rod;
    }

    public int getNop() {
        return nop.get();
    }

    public void setNop(int nop) {
        this.nop.set(nop);
    }

    public IntegerProperty nopProperty() {
        return nop;
    }
    public ArrayList<Photo> getPhotoList() {
        return photoList;
    }

    public void setphotoList(Photo photo) {
        this.photoList.add(photo);
    }

    public ArrayList<Photo> PhotoList() {
        return photoList;
    }

}