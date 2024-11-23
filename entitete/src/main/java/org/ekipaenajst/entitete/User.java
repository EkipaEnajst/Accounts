package org.ekipaenajst.entitete;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "user")
@NamedQueries(value = {
    @NamedQuery(name = "User.getAll", query="SELECT u FROM User u"),
    @NamedQuery(name = "User.getId", query="SELECT u from User u WHERE u.id= :idParam"),
    @NamedQuery(name = "User.getByLastname", query="SELECT u FROM User u WHERE u.lastName= :lastNameParam ORDER BY u.lastName"),
    @NamedQuery(name = "User.getByFullname",
            query="SELECT u FROM User u WHERE u.lastName = :lastNameParam AND u.firstName = :firstNameParam")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String username;
}
