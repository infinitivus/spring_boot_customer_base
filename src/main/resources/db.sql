
CREATE TABLE `person` (
                          `id` int(11) NOT NULL AUTO_INCREMENT,
                          `birthday` varchar(255) DEFAULT NULL,
                          `email` varchar(255) DEFAULT NULL,
                          `name` varchar(255) DEFAULT NULL,
                          `phone_number` varchar(255) DEFAULT NULL,
                          `surname` varchar(255) DEFAULT NULL,
                          `mobile_home_id` int(11) DEFAULT NULL,
                          PRIMARY KEY (`id`),
                          KEY `FKd882usik4w43t5wbi99esqfkk` (`mobile_home_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `mobile_home` (
                               `id` int(11) NOT NULL AUTO_INCREMENT,
                               `brand` varchar(255) DEFAULT NULL,
                               `license_plate` varchar(255) DEFAULT NULL,
                               `model` varchar(255) DEFAULT NULL,
                               `type` varchar(255) DEFAULT NULL,
                               `vin` varchar(255) DEFAULT NULL,
                               `year_of_release` varchar(255) DEFAULT NULL,
                               PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `repair_work` (
                               `id` int(11) NOT NULL AUTO_INCREMENT,
                               `cost_work` bigint(20) DEFAULT NULL,
                               `date` varchar(255) DEFAULT NULL,
                               `master` varchar(255) DEFAULT NULL,
                               `name_the_work` varchar(255) DEFAULT NULL,
                               `home_id` int(11) DEFAULT NULL,
                               PRIMARY KEY (`id`),
                               KEY `FK1wf8gsh942ydmb8p1wk243gt7` (`home_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `spare_part` (
                              `id` int(11) NOT NULL AUTO_INCREMENT,
                              `article` varchar(255) DEFAULT NULL,
                              `cost_part` bigint(20) DEFAULT NULL,
                              `name_spare_part` varchar(255) DEFAULT NULL,
                              PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `parts_work` (
                              `repair_work_id` int(11) NOT NULL,
                              `spare_parts_id` int(11) NOT NULL,
                              KEY `FKmium5du1ovtcxrkg4c2awjjn0` (`spare_parts_id`),
                              KEY `FKt9iewdx89laxp86ur0cr3j0ev` (`repair_work_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `user_data` (
                             `id` int(11) NOT NULL AUTO_INCREMENT,
                             `password` varchar(64) NOT NULL,
                             `username` varchar(255) DEFAULT NULL,
                             PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `user_role` (
                             `id` int(11) NOT NULL AUTO_INCREMENT,
                             `role` varchar(20) NOT NULL,
                             PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `userdata_userrole` (
                                     `user_id` int(11) NOT NULL,
                                     `role_id` int(11) NOT NULL,
                                     PRIMARY KEY (`user_id`,`role_id`),
                                     KEY `FK7kyb3x39e1yehmhr4js0txoxe` (`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci