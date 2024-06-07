package org.twin.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.twin.domain.model.Enums.ERoles;

@Entity
@Table(name = "roles")
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERoles name;

    public Role() {

    }

    public Role(Integer id, ERoles name) {
        this.id = id;
        this.name = name;
    }

    public Role(ERoles name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ERoles getName() {
        return name;
    }

    public void setName(ERoles name) {
        this.name = name;
    }

}
