package me.somefood.bimovie.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Table(name = "tbl_poster")
public class Poster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ino;

    private String fname;

    private int idx;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Movie movie;

    public void setIdx(int idx) {
        System.out.println(idx);
        this.idx = idx;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
