package com.timemanagemenet.timemanagementapp.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Absence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_absence")
    private Long idAbsence;

    private LocalDate absenceDate;
    private AbsenceType absenceType;

    @Column(name = "keycloak_user_id")
    private String keycloakUserId;
}
