package com.example.newssb.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "news")
public class NewsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "shortdescription")
    private String shortDescription;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private CategoryEntity category;


    @Column(name = "linkTTS")
    private String LinkTTS;

    @Column(name = "htmlPage", columnDefinition = "TEXT")
    private String htmlPage;

    @Column(name = "tomtat", columnDefinition = "TEXT")
    private String tomTat;

}
