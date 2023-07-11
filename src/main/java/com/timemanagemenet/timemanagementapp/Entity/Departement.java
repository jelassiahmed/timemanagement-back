package com.timemanagemenet.timemanagementapp.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Departement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long departementId;
    private String departementName;
    private long departementManagerId;

    @ElementCollection
    @CollectionTable(name = "departement_user_ids", joinColumns = @JoinColumn(name = "departement_id"))
    @Column(name = "keycloak_user_id")
    private List<String> keycloakUserId;

}
