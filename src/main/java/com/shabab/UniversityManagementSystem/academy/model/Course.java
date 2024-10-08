package com.shabab.UniversityManagementSystem.academy.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.shabab.UniversityManagementSystem.security.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


import java.util.*;
import lombok.*;

/**
 * Project: UniversityManagementSystem-SpringBoot
 * Author: Shabab
 * Created on: 25/08/2024
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "acd_courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Course name is required")
    @Size(max = 60, message = "Max 60 Characters")
    @Column(length = 60, nullable = false)
    private String name;

    @NotBlank(message = "Course code is required")
    @Size(max = 10, message = "Max 10 Characters")
    @Column(length = 10, nullable = false)
    private String code;

    @NotNull(message = "Course credit is required")
    @Min(value = 1, message = "Credit must be a positive number")
    @Column(nullable = false)
    private Integer credit;

    @Size(max = 200, message = "Max 200 Characters")
    @Column(length = 200)
    private String description;

    @NotNull(message = "Semester is required")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private Semester semester;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "acd_course_teachers",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id")
    )
    private List<User> teachers = new ArrayList<>();

    public Course(Long id) {
        this.id = id;
    }
}
