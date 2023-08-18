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
    private UUID user_id;

    @Column(nullable = true, length = 100)
    private String name;

    @Column(nullable = false, length = 100)
    private String create_user;

    @CreationTimestamp
    @ColumnDefault("CURRENT_TIMESTAMP()")
    @Column(nullable = false)
    private Date create_date;

    @Column(nullable = false, length = 100)
    private String last_update_user;

    @ColumnDefault("CURRENT_TIMESTAMP()")
    @UpdateTimestamp
    @Column(nullable = false)
    private Date last_update_date;
}
