package com.timemanagemenet.timemanagementapp.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class EnfantId implements Serializable {

    private Long fatherId;
    private Integer conjointId;
    private Integer childNumber;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EnfantId enfantId = (EnfantId) o;
        return fatherId != null && Objects.equals(fatherId, enfantId.fatherId)
                && conjointId != null && Objects.equals(conjointId, enfantId.conjointId)
                && childNumber != null && Objects.equals(childNumber, enfantId.childNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fatherId, conjointId, childNumber);
    }
}
