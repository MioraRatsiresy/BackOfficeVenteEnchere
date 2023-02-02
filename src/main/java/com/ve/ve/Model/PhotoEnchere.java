package com.ve.ve.Model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
  
@Document(collection = "PhotoEnchere")
  
// Class
public class PhotoEnchere {
  
    private int idEnchere;
    public int getIdEnchere() {
        return idEnchere;
    }
    public void setIdEnchere(int idEnchere) {
        this.idEnchere = idEnchere;
    }
    private String photo;
    public String getPhoto() {
        return photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }
}