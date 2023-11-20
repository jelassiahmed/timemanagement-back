package com.timemanagemenet.timemanagementapp.Entity;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@AllArgsConstructor
@Table(name="RECLAMATION")
@NoArgsConstructor
@SQLDelete(sql = "UPDATE  ABSENCE SET is_deleted = 1 where id_reclamation=?")
@Where(clause = "is_deleted=0")
@Entity
@Getter
@Setter
@ToString
public class Reclamation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_reclamation", nullable = false)
    private Long idReclamation;

    @Column(name = "reclamation_type")
    private String reclamationType;

    @Column(name = "reclamation_description")
    private String reclamationDescription;

    @Column(name = "reclamation_date")
    private String reclamationDate;

    @Column(name = "reclamation_status")
    private String reclamationStatus;

    @Column(name = "keycloak_user_id")
    private String keycloakUserId;

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

    // @OneToOne relation with absence
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_absence", referencedColumnName = "id_absence")
    private Absence absence;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Reclamation that = (Reclamation) o;
        return getIdReclamation() != null && Objects.equals(getIdReclamation(), that.getIdReclamation());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
