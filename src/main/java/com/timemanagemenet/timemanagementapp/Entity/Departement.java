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
    @CollectionTable(name = "user_ids", joinColumns = @JoinColumn(name = "post_id"))
    @Column(name = "user_id")
    private List<String> userIds;

}
