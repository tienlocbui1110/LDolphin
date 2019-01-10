package vn.edu.hcmus.ldolphin.data;

public class User {
    private String name, id, email;

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public User(String email, String name, String id) {
        this.name = name;
        this.id = id;
        this.email = email;

    }
}
