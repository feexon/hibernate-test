package domain;

import org.hibernate.annotations.Cascade;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * @author Administrator
 * @version 1.0 2014/4/10,17:09
 */
@Entity
public class Address {
    @Id
    public Integer id;

    public String country;
    @OneToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    public User user;
}
