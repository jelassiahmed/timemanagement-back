package com.timemanagemenet.timemanagementapp.Entity;


import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE  POST SET is_deleted = 1 where id_post=?")
@Table(name="POST")
@Where(clause = "is_deleted=0")
@Entity
@Getter
@Setter
@ToString
public class Post implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_post", nullable = false)
    private Long idPost;

    @Column(name = "post_name", nullable = false)
    private String postName;

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
        Post post = (Post) o;
        return getIdPost() != null && Objects.equals(getIdPost(), post.getIdPost());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
