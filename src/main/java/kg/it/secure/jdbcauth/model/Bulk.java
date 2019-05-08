package kg.it.secure.jdbcauth.model;

public class Bulk {
    private int id;
    private String text;

    public Bulk() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Bulk(int id, String text) {
        this.id = id;
        this.text = text;
    }
}
