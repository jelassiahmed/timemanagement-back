package com.timemanagemenet.timemanagementapp.Entity;


import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name="CONJOINT")
@SQLDelete(sql = "UPDATE  CONJOINT SET is_deleted = 1 where id_conjoint=?")
@Where(clause = "is_deleted=0")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Conjoint {
    @EmbeddedId
    private ConjointId idConjoint;

    @Column(name = "conjoint_name", nullable = false)
    private String conjointName;

    @Column(name = "conjoint_surname", nullable = false)
    private String conjointSurname;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Conjoint conjoint = (Conjoint) o;
        return getIdConjoint() != null && Objects.equals(getIdConjoint(), conjoint.getIdConjoint());
    }

    @Override
    public int hashCode() {
        return Objects.hash(idConjoint);
    }
}
