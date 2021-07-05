package bts.twitter.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "usr")
public class User {

    @Id
    private long id;

    private String name;

    @OneToMany(mappedBy = "author")
    private List<Message> messages;
}
