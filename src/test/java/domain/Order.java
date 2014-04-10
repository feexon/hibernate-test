package domain;

import javax.persistence.*;

/**
 * @author Administrator
 * @version 1.0 2014/4/10,14:18
 */
@Entity
@Access(AccessType.FIELD)
@Table(name = "t_order")
public class Order {
    @Id
    public Integer id;
    public String name;
    @ManyToOne
    @JoinColumn(name = "payerCardNo", referencedColumnName = "cardNo")
    public User payer;
}
