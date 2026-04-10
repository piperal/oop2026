package ee.piperal.veebipood.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true) //Peab olema DB-s unikaalne
    private String email;
    private String password;
    @Column(unique = true) //Peab olema DB-s unikaalne
    private String personalCode;

    //CascareType.REMOVE --> when Person is deleted, then so is the Address

    //CascareType.PERSIST when Peron is added with new Address,
    //                    which isn't in the DB, then it is added

    //CascareType.MERGE --> When Person and Address of person is changed,
    //                      then Person and Address is also changed

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;
}
