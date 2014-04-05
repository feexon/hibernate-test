package domain;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.SQLInsert;

import javax.persistence.*;

/**
 * @author Administrator
 * @version 1.0 2014/4/5,17:20
 */
@Entity
@Access(AccessType.FIELD)
@SQLInsert(sql = "insert into address (province,country,\"order\") values(:province,:country,1)")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;
    public String province;
    public String country;
    @Formula("`order`")
    public int order = 0;

    public Address() {
    }

    public Address(String province, String country) {
        this.province = province;
        this.country = country;
    }
}
