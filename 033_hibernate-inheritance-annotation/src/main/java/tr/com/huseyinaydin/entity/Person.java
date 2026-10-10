package tr.com.huseyinaydin.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "PERSONS")
@Inheritance(strategy = InheritanceType.JOINED)
/*
Normalleştirilmiş yapısıyla gereksiz boş kolonları önlerken veri bütünlüğünü koruması,
JOINED stratejisini kalıtım hiyerarşilerinde genellikle en dengeli ve sürdürülebilir tercih haline getirir.
*/

//@DiscriminatorColumn(name = "person_type", discriminatorType = DiscriminatorType.STRING) // SINGLE_TABLE
public class Person {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY) // SINGLE_TABLE
    //@GeneratedValue(strategy = GenerationType.AUTO) // TABLE_PER_CLASS
    @GeneratedValue(strategy = GenerationType.IDENTITY) // JOINED
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_seq")
    //@SequenceGenerator(name = "person_seq", sequenceName = "person_sequence", allocationSize = 1)
    @Column(name = "PERSON_ID")
    private Long personId;

    @Column(name = "FIRST_NAME")
    private String firstname;

    @Column(name = "LAST_NAME")
    private String lastname;

    @Column(name = "ADDRESS", nullable = false)
    private String address;

    public Person() {
    }

    public Person(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Person(String firstname, String lastname, String address) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}