package springprojects.entities;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Getter
@Setter
@Entity
@Table(name = "evaluations")
public class Evaluation {

        @Id
        @GeneratedValue
        private int id;
        @ManyToOne
        @JoinColumn(name ="competitor_id")
        private Competitor competitor;
        @Column
        private double value;

        public Evaluation() {
        }

        public Evaluation(Competitor competitor, double value) {
            this.competitor = competitor;
            this.value = value;
        }

}
