package com.timemanagemenet.timemanagementapp.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="DIPLOME")
@SQLDelete(sql = "UPDATE  DIPLOME SET is_deleted = 1 where id_diplome=?")
@Where(clause = "is_deleted=0")
@Data
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




}
