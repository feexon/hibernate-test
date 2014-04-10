package domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Administrator
 * @version 1.0 2014/4/10,14:20
 */
@Entity
@Access(AccessType.FIELD)
@Table(name = "t_user")
public class User implements Serializable{
    @Id
    public Integer id;
    public String name;

    @Column(name = "cardNo")
    public String card;
}
