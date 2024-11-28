package model;


@lombok.Getter
@lombok.Setter
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor 
@lombok.Data 
public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String passwordHash;
    private boolean isAdmin;
    private boolean isOwner;
    String code;

    
}
