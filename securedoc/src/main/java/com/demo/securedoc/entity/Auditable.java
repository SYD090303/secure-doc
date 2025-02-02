package com.demo.securedoc.entity;


import com.demo.securedoc.domain.RequestContext;
import com.demo.securedoc.exception.ApiException;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.AlternativeJdkIdGenerator;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
public abstract class Auditable {
    @Id
    @SequenceGenerator(name = "primary_key_seq", sequenceName = "primary_key_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "primary_key_seq")
    @Column(name = "id", updatable = false)
    private Long id;
    private String referenceId = new AlternativeJdkIdGenerator().generateId().toString();
    @NotNull
    private Long createdBy;
    @NotNull
    private Long updatedBy;
    @NotNull
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @CreatedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;


    @PrePersist
    public void prePersist() {
        var userId = RequestContext.getUserId();
        if (userId == null) {
            throw new ApiException("Cannot persist entity without user Id in Request Context for this thread");
        }
        setCreatedAt(now());
        setCreatedBy(userId);
        setUpdatedAt(now());
        setUpdatedBy(userId);
    }

    @Override
    public String toString() {
        return "Auditable{" +
                "id=" + id +
                ", referenceId='" + referenceId + '\'' +
                ", createdBy=" + createdBy +
                ", updatedBy=" + updatedBy +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    @PreUpdate
    public void preUpdate() {
        var userId = RequestContext.getUserId();
        if (userId == null) {
            throw new ApiException("Cannot persist entity without user Id in Request Context for this thread");
        }
        setUpdatedAt(now());
        setUpdatedBy(userId);


    }

}
