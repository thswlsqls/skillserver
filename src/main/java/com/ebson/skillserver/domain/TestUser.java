package com.ebson.skillserver.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.UUID;

@Entity
@Getter @Setter
public class TestUser {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @ColumnDefault("UNHEX(REPLACE(UUID(), '-', ''))")
    @Column(name = "user_id", nullable = false, columnDefinition = "BINARY(16)")
    private UUID userId;

    @Column(name = "name", nullable = true, length = 100)
    private String name;

    @Column(name = "create_user", nullable = false, length = 100)
    private String createUser;

    @CreationTimestamp
    @ColumnDefault("CURRENT_TIMESTAMP()")
    @Column(name = "create_date", nullable = false)
    private Date createDate;

    @Column(name = "last_update_user", nullable = false, length = 100)
    private String lastUpdateUser;

    @ColumnDefault("CURRENT_TIMESTAMP()")
    @UpdateTimestamp
    @Column(name = "last_update_date", nullable = false)
    private Date lastUpdateDate;
}
