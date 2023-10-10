package business;

import java.io.Serializable;

//puplic record User(String firstName,String lastName,String email) implements Serializable{}
public class User implements Serializable { 
    private String firstName;
    private String lastName;
    private String email;
    private String dob; // Date of Birth
    private String searchEngine; // How they heard about you
    private String emailOK; // Email announcement preference
    private String emailNo; // Email announcement preference
    
    public User() {
        firstName = "";
        lastName = "";
        email = "";
        dob = "";
        searchEngine = "";
        emailOK = "";
        emailNo = "";
    }
    
    public User(String firstName, String lastName, String email, String dob,
                String searchEngine,String  emailOK, String emailNo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dob = dob;
        this.searchEngine = searchEngine;
        this.emailOK = emailOK;
        this.emailNo = emailNo;
    }  
    public String getFirstName() {
        return firstName;
        }
    public void setFirstName (String firstName) { this.firstName = firstName;
        }
    public String getLastName() { return lastName;
        }
    public void setLastName (String lastName) { this.lastName = lastName;
        }
    public String getEmail() { return email;
        }
    public void setEmail (String email) { this.email = email;
        }
     // Getter and Setter methods for Date of Birth
    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    // Getter and Setter methods for How they heard about you
    public String getSearchEngine() {
        return searchEngine;
    }

    public void setSearchEngine(String searchEngine) {
        this.searchEngine = searchEngine;
    }

    // Getter and Setter methods for Email announcement preferences
    public String isEmailOK() {
        return emailOK;
    }

    public void setEmailOK() {
        this.emailOK = emailOK;
    }

    public String isEmailNo() {
        return emailNo;
    }

    public void setEmailNo() {
        this.emailNo = emailNo;
    }
} 
