package com.project.sberloggs.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "loggs")
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "date")
    private String date;
    @Column(name = "level")
    private String level;
    @Column(name = "thread")
    private String thread;
    @Column(name = "logger")
    private String logger;
    @Column(name = "message")
    private String message;

    public Log(String date, String level, String thread, String logger, String message) {
        super();
        this.id = id;
        this.date = date;
        this.level = level;
        this.thread = thread;
        this.logger = logger;
        this.message = message;
    }

    public Log() {

    }
}
