package com.cos.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bytebuddy.asm.Advice;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Size(min=1,max=100, message="First Name '${validatedValue}' must be between {min} and {max} chars long")
    @NotBlank(message="Fist name can not white spage")
    @Column(length = 100, nullable = false)
    private String firstName;

    @Column(length = 100, nullable = false)
    private String lastName;

    private String branch;

    private Integer age;

    @Email(message=("Provide valid email"))
    @Column(unique = true,nullable = false,length = 100)
    private String email;


}
