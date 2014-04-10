package domain;

import javax.persistence.*;

/**
 * @author Administrator
 * @version 1.0 2014/4/10,17:07
 */
@Entity
@Access(AccessType.FIELD)
public class User {
    @Id
    public Integer id;
    public String name;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    public Address address;
}
