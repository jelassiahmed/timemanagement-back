package com.timemanagemenet.timemanagementapp.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE  POST SET is_deleted = 1 where id_post=?")
@Table(name="POST")
@Where(clause = "is_deleted=0")
@Entity
@Data
public class Post implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_post", nullable = false)
    private Long idPost;

    @Column(name = "post_name", nullable = false)
    private String postName;



}
