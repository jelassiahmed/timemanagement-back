package com.timemanagemenet.timemanagementapp.Entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class ConjointId implements Serializable {

    private Long employeeId;
    private Integer conjointId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConjointId that = (ConjointId) o;
        return Objects.equals(employeeId, that.employeeId) &&
                Objects.equals(conjointId, that.conjointId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, conjointId);
    }
}
