package com.appinventiv.assignment.movie_rest.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;


@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(NON_NULL)
public class Movie{


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    private String name;

    @DecimalMin("0.5")
    @DecimalMax("5")
    private Float rating;

    @OneToOne(cascade = {CascadeType.ALL})
    private Category category;

    public Movie(Integer id) {
        this.id = id;
    }

    public Movie(String name) {
        this.name = name;
    }
}
