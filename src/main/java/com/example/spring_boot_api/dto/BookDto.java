package com.example.spring_boot_api.dto;

import java.sql.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// ID
// タイトル
// 概要
// ジャンルID
// 作者
// 出版社
// 出版日
// 価格
// ページ数
// ISBN
// イメージ画像URL

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    private String id;
    private String title;
    private String description;
    private List<Integer> genreIds;
    private List<String> authors;
    private String publisher;
    private Date publishedDate;
    private Integer price;
    private Integer pageCount;
    private String isbn;
    private String imageUrl;
}
