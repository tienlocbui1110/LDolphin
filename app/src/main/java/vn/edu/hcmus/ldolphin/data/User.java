package vn.edu.hcmus.ldolphin.data;

public class User {
    private String name, id, phone, cmnd, email;

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public User(String name, String id, String phone, String cmnd, String email) {

        this.name = name;
        this.id = id;
        this.phone = phone;
        this.cmnd = cmnd;
        this.email = email;
    }
}
