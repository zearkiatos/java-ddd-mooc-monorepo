CREATE DATABASE IF NOT EXISTS `mooc` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `mooc`;

CREATE TABLE IF NOT EXISTS `courses` (
  `id` CHAR(36) NOT NULL,
  `name` varchar(255) NOT NULL,
  `duration` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `videos` (
  `id` CHAR(36) NOT NULL,
  `title` varchar(255) NOT NULL,
  `description` VARCHAR(255) NOT NULL,
  `url` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
