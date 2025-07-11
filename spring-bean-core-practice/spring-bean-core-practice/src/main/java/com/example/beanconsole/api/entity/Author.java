package com.example.beanconsole.api.entity;

import jakarta.persistence.*;


@Entity
public class Author {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

       public Author(String name, String email,String phone,Long id){
           this.name=name;
           this.email=email;
           this.phone=phone;
           this.id=id;
       }

        private  String phone;
        public Author(){

        }

       public String getName(){
            return  name;
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
       public String getPhone(){
            return phone;
       }
        public void setPhone(String phone){
                this.phone=phone;
        }
        public Long getId(){
                return id;
        }
        public void setId(Long id){
                this.id=id;
        }
}
