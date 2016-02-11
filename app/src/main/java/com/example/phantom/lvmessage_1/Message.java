package com.example.phantom.lvmessage_1;

/**
 * Created by Erwan on 12/11/2015.
 */
public class Message {

    private int id;
    private String message;
    private String auteur;

    public Message(String message) {
        this.message = message;
    }

    public Message(String message, String auteur) {
        this.message = message;
        this.auteur = auteur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }
}
