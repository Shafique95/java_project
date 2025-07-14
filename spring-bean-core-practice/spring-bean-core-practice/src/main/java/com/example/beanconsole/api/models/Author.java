package com.example.beanconsole.api.models;

public class Author {
    Long id;
    String name;
    String email;
    public Author(String name,String email,Long id){
        this.name=name;
        this.email=email;
        this.id=id;
    }
    public Long getId(){
        return  id;
    }
    public void setId(Long id){
        this.id=id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email=email;
    }
}
