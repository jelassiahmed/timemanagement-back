package com.timemanagemenet.timemanagementapp.Entity;


import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="DIPLOME")
@SQLDelete(sql = "UPDATE  DIPLOME SET is_deleted = 1 where id_diplome=?")
@Where(clause = "is_deleted=0")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Diplome {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_diplome", nullable = false)
    private Long idDiplome;

    @Column(name = "diplome_nature", nullable = false)
    private String diplomeNature;

    @Column(name = "diplome_speciality", nullable = false)
    private String diplomeSpeciality;

    @Column(name = "diplome_date", nullable = false)
    private LocalDate diplomeDate;

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
        Diplome diplome = (Diplome) o;
        return getIdDiplome() != null && Objects.equals(getIdDiplome(), diplome.getIdDiplome());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
