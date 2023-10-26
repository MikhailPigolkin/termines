package com.example.termines.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "guides")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Term {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "term_id")
    private UUID termID;
    private String term;
    @Column(columnDefinition = "TEXT")
    private String description;
}
