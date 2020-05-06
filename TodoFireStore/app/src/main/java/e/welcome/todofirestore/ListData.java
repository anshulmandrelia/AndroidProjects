package e.welcome.todofirestore;

public class ListData {
    public String title;
    public String description;
    public String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ListData()
    {

    }
    public ListData( String title, String description) {

        this.title=title;
        this.description=description;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



}
