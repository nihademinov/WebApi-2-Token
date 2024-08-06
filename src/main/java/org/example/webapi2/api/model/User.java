package org.example.webapi2.api.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "UserAccount")
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
@SQLDelete(sql = "UPDATE \"UserAccount\" SET \"DeletedAt\" = CURRENT_TIMESTAMP WHERE \"UserAccountId\" = ?")
@Where(clause = "\"DeletedAt\" IS NULL")
public class User  implements UserDetails { //custom userDetails implementation

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserAccountId")
    private Long userAccountId;


    @CreationTimestamp
    @Column(name = "CreatedAt", updatable = false)
    private LocalTime createdAt;

    @UpdateTimestamp
    @Column(name = "UpdatedAt")
    private LocalTime updatedAt;

    @Column(name = "DeletedAt")
    private LocalTime deletedAt;

    @Column(name = "Email", unique = true, nullable = false)
    @Email(message = "Email should be valid")
    private String email;

    @Column(name = "FirstName")
    @NotBlank
    private String firstName;

    @Column(name = "LastName")
    @NotBlank
    private String lastName;

    @Column(name = "Password")
    @NotBlank
    private String password;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "UserRoles",
            joinColumns = @JoinColumn(name = "UserId"),
            inverseJoinColumns = @JoinColumn(name = "RoleId")
    )
    private List<Role> roles = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Contact> contacts = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Product> products = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    @Override
    public String getPassword() {
        return password;
    }
}
