package org.twin.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Manga {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "manga_gen")
    @SequenceGenerator(name = "manga_gen", sequenceName = "manga_seq")
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
    private String title;
    private String description;
    private int chapter;
}
