package org.example.Model;

public class Users {
    private int id_user;
    private String first_name;
    private String last_name;

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getIdUsuario(){
        return id_user;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getLast_name() {
        return last_name;
    }


}