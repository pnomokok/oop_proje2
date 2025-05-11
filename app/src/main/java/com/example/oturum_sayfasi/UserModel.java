package com.example.oturum_sayfasi;

public class UserModel {
    private String username;
    private String password;

    public UserModel() {
        // Boş constructor gerekli (Retrofit için)
    }

    public UserModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getter ve Setter'lar

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
}

