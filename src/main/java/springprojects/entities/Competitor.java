package springprojects.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter

@Entity
@Table(name = "competitors")
public class Competitor  {
    @Id
    @GeneratedValue
    private int id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "country")
    private String country;
    @Column
    @OneToMany(mappedBy = "competitor", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Evaluation> evaluation;

    @Transient
    private double totalRate;

    @Column
    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    public Competitor() {
    }

    public Competitor(String firstName, String lastName, String country, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.gender = gender;
    }

    public Competitor(int id, String firstName, String lastName,
                      String country, double totalRate, Gender gender) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.totalRate = totalRate;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Evaluation> getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(List<Evaluation> evaluation) {
        this.evaluation = evaluation;
    }

    public double getTotalRate() {
        return totalRate;
    }

    public void setTotalRate(double totalRate) {
        this.totalRate = totalRate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Competitor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", country='" + country + '\'' +
                ", totalRate=" + totalRate +
                ", gender=" + gender +
                '}';
    }

   /* @Override
    public int compareTo(Competitor o) {
        *//**
         * Compares this object with the specified object for order.  Returns a
         * negative integer, zero, or a positive integer as this object is less
         * than, equal to, or greater than the specified object.
         *//*
        if (this.getTotalRate() < o.getTotalRate()) {
            return -1;
        } else if (this.getTotalRate() == o.getTotalRate()) {
            return 0;
        } else  {
            return 1;
        }
    }*/
}
