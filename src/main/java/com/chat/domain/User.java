package com.chat.domain;

import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  //@UniqueElements
  private String username;

  private String password;

  private String email;

  @Enumerated(EnumType.STRING)
  private Type type;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "user_room",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "room_id"))
  private Set<Room> rooms = new HashSet<>();

  public User() {}

  public User(String username, String password, String email, Type type) {
    this.username = username;
    this.password = password;
    this.email = email;
    this.type = type;
  }

  //
  // Métodos de acesso
  //
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String userName) {
    this.username = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Type getType() {
    return type;
  }

  public void setType(Type type) {
    this.type = type;
  }

  public Set<Room> getRooms() {
    return rooms;
  }

  public void setRooms(Set<Room> rooms) {
    this.rooms = rooms;
  }
}
