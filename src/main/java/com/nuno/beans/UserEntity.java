package com.nuno.beans;

public class UserEntity {
    private String username;
    private String password;
    
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) throws BeanException {
    	 if (username.length() > 10) {
             throw new BeanException("Le nom est trop grand ! (10 caractères maximum)");
         }
         else {
             this.username = username; 
         }
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String prenom) {
        this.password = prenom;
    }
}