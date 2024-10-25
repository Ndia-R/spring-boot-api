package com.example.spring_boot_api.model;

import java.sql.Date;
import java.sql.Timestamp;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    private String id;
    private String title;
    private String description;
    private String genreIds;
    private String authors;
    private String publisher;
    private Date publishedDate;
    private Integer price;
    private Integer pageCount;
    private String isbn;
    private String imageUrl;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Boolean isDeleted;
}
