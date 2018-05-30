package modelo;

public class Usuario {

    private String email;
    private String username;
    private String password;

    private enum Gender {
        MALE, FEMALE, NONE
    }
    private enum UserType {
        ADMIN, PROFESOR, ESTUDIANTE
    }
    private Gender gender;
    private UserType userType;

    public Usuario(String email, String username, String password, Gender gender, UserType ut) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.userType = ut;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender g) {
        this.gender = g;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getGenderChar() {
        return "";
    }
}
