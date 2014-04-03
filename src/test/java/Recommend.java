import org.hibernate.annotations.Formula;
import org.hibernate.annotations.JoinFormula;
import org.hibernate.annotations.Loader;
import org.hibernate.annotations.Persister;
import org.hibernate.persister.collection.BasicCollectionPersister;
import org.hibernate.persister.collection.OneToManyPersister;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Administrator
 * @version 1.0 2014/4/3,12:01
 */
@Entity
@Table(name = "category")
@Access(AccessType.FIELD)
public class Recommend {
    @Id
    public Integer id;


    public String name;

    @OneToMany
    @JoinColumn(name = "categoryId", referencedColumnName = "id")
    public Set<Photo> pictures = new HashSet<Photo>();
}

