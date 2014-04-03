import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Administrator
 * @version 1.0 2014/4/3,12:43
 */
@Entity
@Access(AccessType.FIELD)
@Table(name = "recommend")
public class Photo {
    @Id
    public Integer id;

    @CollectionTable(name = "picture", joinColumns = @JoinColumn(name = "recommendId", referencedColumnName = "id"))
    @ElementCollection(targetClass = String.class)
    @Column(name = "image")
    private Set<String> file;

    public Photo() {
    }


    public Photo(String... files) {
        this.file = new HashSet(Arrays.asList(files));
    }

    public boolean equals(Object that) {
        return ((Photo) that).file.equals(file);
    }

    @Override
    public int hashCode() {
        return file.hashCode();
    }

    @Override
    public String toString() {
        return file.toString();
    }
}
