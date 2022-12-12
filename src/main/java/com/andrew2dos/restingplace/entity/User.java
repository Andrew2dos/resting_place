package com.andrew2dos.restingplace.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

//    @NotEmpty(message = "Name's cannot be empty.")
//    @Size(message = "Allows names between 3 and 30 characters long.", min=3, max=30)
    @Column(name = "username")
    private String userName;

//    @NotEmpty(message = "Password cannot be empty.")
//    @Size(message = "Allows names between 4 and 8 characters long.", min=4, max=8)
    @Column(name = "password", unique = true)  //nullable = false,
    private String password;

    @Email // (regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
//    @NotEmpty(message = "Email cannot be empty")
    @Column(name = "email")
    private String email;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<Booking> bookings;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

//    public void addBookingToUser(Booking booking){
//        if(bookings == null){
//            bookings = new ArrayList<>();
//        }
//        bookings.add(booking);
//        booking.setUser(this); // необходимо прописывать в bi-directional
//    }

}