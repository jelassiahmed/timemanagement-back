package com.timemanagemenet.timemanagementapp.Entity;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE  ENFANT SET is_deleted = 1 where id_enfant=?")
@Table(name="ENFANT")
@Where(clause = "is_deleted=0")
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Enfant implements Serializable{
    @EmbeddedId
    private EnfantId id;

    @Column(name = "name")
    private String name;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "studies_level") // table
    private String studiesLevel;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Enfant enfant = (Enfant) o;
        return getId() != null && Objects.equals(getId(), enfant.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

