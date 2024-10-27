package com.example.spring_boot_api.model;

import java.sql.Date;
import com.example.spring_boot_api.shared.EntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "books")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Book extends EntityBase {

    @Id
    private String id;
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String genreIds;
    private String authors;
    private String publisher;
    private Date publishedDate;
    private Integer price;
    private Integer pageCount;
    private String isbn;
    private String imageUrl;
}
