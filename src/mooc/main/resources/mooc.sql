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


CREATE TABLE IF NOT EXISTS `steps` (
  `id` CHAR(36) NOT NULL,
  `title` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `steps_challenges` (
  `id` CHAR(36) NOT NULL,
  `statement` TEXT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_steps_challenges__step_id` FOREIGN KEY (`id`) REFERENCES `steps` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


CREATE TABLE IF NOT EXISTS `steps_videos` (
  `id` CHAR(36) NOT NULL,
  `url` VARCHAR(255) NOT NULL,
  `text` TEXT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_steps_video__step_id` FOREIGN KEY (`id`) REFERENCES `steps` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
