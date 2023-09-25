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
import java.time.LocalDateTime;
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
public class Conjoint {
    @EmbeddedId
    private ConjointId idConjoint;

    @Column(name = "conjoint_name", nullable = false)
    private String conjointName;

    @Column(name = "conjoint_surname", nullable = false)
    private String conjointSurname;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "is_deleted")
    private Integer isDeleted;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "updated_by")
    private String updatedBy;

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
