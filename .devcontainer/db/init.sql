DROP DATABASE IF EXISTS `my-books-db`;
CREATE DATABASE `my-books-db`;

USE `my-books-db`;

-- bookテーブルの削除と再作成
DROP TABLE IF EXISTS `books`;
CREATE TABLE `books` (
  `id` VARCHAR(255) NOT NULL,
  `title` VARCHAR(255) NOT NULL,
  `description` TEXT NOT NULL,
  `genre_ids` VARCHAR(255) NOT NULL,
  `authors` VARCHAR(255) NOT NULL,
  `publisher` VARCHAR(255) NOT NULL,
  `published_date` DATE NOT NULL,
  `price` int(11) NOT NULL,
  `page_count` int(11) NOT NULL,
  `isbn` VARCHAR(255) NOT NULL,
  `image_url` VARCHAR(255) NOT NULL,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` TINYINT(1) DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
);

-- genreテーブルの削除と再作成
DROP TABLE IF EXISTS `genres`;
CREATE TABLE `genres` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `description` TEXT,
  `name_en` VARCHAR(255) NOT NULL,
  `description_en` TEXT,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` TINYINT(1) DEFAULT 0,
  PRIMARY KEY (`id`)
) AUTO_INCREMENT=10;

-- データのロード
LOAD DATA INFILE '/docker-entrypoint-initdb.d/data.csv'
INTO TABLE books
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
(`id`, `title`, `description`, `genre_ids`, `authors`, `publisher`, `published_date`, `price`, `page_count`, `isbn`, `image_url`);

INSERT INTO `genres` (`name`, `description`, `name_en`, `description_en`) VALUES
('ミステリー', '謎解きや推理をテーマにした作品', 'Mystery', 'Works themed around solving mysteries or detective stories.'),
('サスペンス', '緊張感や驚きを伴う作品', 'Suspense', 'Works filled with tension and surprise.'),
('ロマンス', '恋愛をテーマにした作品', 'Romance', 'Works centered around love and romantic relationships.'),
('ファンタジー', '魔法や異世界を舞台にした作品', 'Fantasy', 'Works set in magical or otherworldly settings.'),
('SF', '科学技術や未来をテーマにした作品', 'Science Fiction (SF)', 'Works themed around science, technology, or the future.'),
('ホラー', '恐怖をテーマにした作品', 'Horror', 'Works centered around fear and terror.'),
('歴史', '歴史的な出来事や人物をテーマにした作品', 'Historical', 'Works themed around historical events or figures.'),
('絵本', '子供向けのイラストが多い本', 'Picture Book', 'Books with many illustrations for children.'),
('教科書', '教育機関で使用される教材', 'Textbook', 'Educational materials used in academic institutions.'),
('専門書', '特定の分野に特化した書籍', 'Specialized Book', 'Books focused on specific fields or subjects.'),
('研究書', '学術的な研究をまとめた書籍', 'Research Book', 'Scholarly works summarizing academic research.'),
('環境', '自然や環境問題をテーマにした作品', 'Environment', 'Works themed around nature or environmental issues.'),
('冒険', '冒険や探検をテーマにした作品', 'Adventure', 'Works themed around exploration or exciting journeys.'),
('図鑑', '特定のテーマに関する情報を集めた書籍', 'Encyclopedia', 'Books collecting information on specific themes.'),
('音楽', '音楽に関する書籍', 'Music', 'Books about music or related topics.'),
('ドラマ', '人間関係や感情を描いた作品', 'Drama', 'Works depicting human relationships and emotions.'),
('教育', '教育に関する書籍', 'Education', 'Books related to teaching or educational topics.');
