package e.welcome.datingclone;

public class User {
    public String name, number, gender, email, password;

    public User() {

    }


    public User(String name, String number, String gender, String email,
                String password) {
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.number = number;
        this.password = password;


    }

}