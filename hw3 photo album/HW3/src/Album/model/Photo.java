package Album.model;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

public class Photo {
	private final StringProperty tags;
    private final StringProperty caption;
    private final StringProperty date;
    private Image image;

    public Photo() {
        this(null);
    }

    /**
     * Constructor with some initial data.
     *
     * @param name
     */
    public Photo(String tag) {
        this.tags = new SimpleStringProperty(tag);

        // Some initial dummy data, just for convenient testing.
        this.caption = new SimpleStringProperty("");
        this.date = new SimpleStringProperty("");
        this.image = new Image("");
    }

    public String getTag() {
        return tags.get();
    }

    public void setTag(String name) {
        this.tags.set(name);
    }

    public StringProperty tagProperty() {
        return tags;
    }

    public String getCaption() {
        return caption.get();
    }

    public void setCaption(String caption) {
        this.caption.set(caption);
    }

    public StringProperty captionProperty() {
        return caption;
    }
    public String getDate() {
        return date.get();
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public StringProperty dateProperty() {
        return date;
    }
    public String getImage() {
        return image.toString();
    }

    public void setImage(String resource) {
        this.image = new Image(resource);
    }

    public Image Image() {
        return this.image;
    }
}
