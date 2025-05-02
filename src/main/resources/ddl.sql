DROP DATABASE algoduck_local_dev;

CREATE DATABASE `algoduck_local_dev`;
USE `algoduck_local_dev`;

CREATE TABLE `algorithms_seq` (
    `next_val` bigint NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
INSERT INTO `algorithms_seq` (next_val) VALUES (1);

CREATE TABLE `aliases_seq` (
    `next_val` bigint NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
INSERT INTO `aliases_seq` (next_val) VALUES (1);

CREATE TABLE `languages_seq` (
    `next_val` bigint NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
INSERT INTO `languages_seq` (next_val) VALUES (1);

CREATE TABLE `members_seq` (
    `next_val` bigint NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
INSERT INTO `members_seq` (next_val) VALUES (1);

CREATE TABLE `problems_seq` (
    `next_val` bigint NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
INSERT INTO `problems_seq` (next_val) VALUES (1);

CREATE TABLE `problems_algorithms_seq` (
    `next_val` bigint NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
INSERT INTO `problems_algorithms_seq` (next_val) VALUES (1);

CREATE TABLE `problem_images_seq` (
    `next_val` bigint NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
INSERT INTO `problem_images_seq` (next_val) VALUES (1);

CREATE TABLE `submissions_seq` (
    `next_val` bigint NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
INSERT INTO `submissions_seq` (next_val) VALUES (1);

CREATE TABLE `testcases_seq` (
    `next_val` bigint NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
INSERT INTO `testcases_seq` (next_val) VALUES (1);

CREATE TABLE `versions_seq` (
    `next_val` bigint NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
INSERT INTO `versions_seq` (next_val) VALUES (1);

-- algoduck_local_dev.algorithms definition

CREATE TABLE `algorithms` (
                              `problem_count` int NOT NULL,
                              `algorithm_id` bigint NOT NULL,
                              `created_at` datetime(6) DEFAULT NULL,
                              `modified_at` datetime(6) DEFAULT NULL,
                              `algorithm_name` varchar(255) NOT NULL,
                              PRIMARY KEY (`algorithm_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- algoduck_local_dev.languages definition

CREATE TABLE `languages` (
                             `created_at` datetime(6) DEFAULT NULL,
                             `language_id` bigint NOT NULL,
                             `modified_at` datetime(6) DEFAULT NULL,
                             `name` varchar(255) NOT NULL,
                             PRIMARY KEY (`language_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- algoduck_local_dev.members definition

CREATE TABLE `members` (
                           `solved` int NOT NULL,
                           `created_at` datetime(6) DEFAULT NULL,
                           `member_id` bigint NOT NULL,
                           `modified_at` datetime(6) DEFAULT NULL,
                           `quit_request_time` datetime(6) DEFAULT NULL,
                           `login_id` varchar(20) NOT NULL,
                           `nickname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                           `email` varchar(40) NOT NULL,
                           `password` varchar(255) NOT NULL,
                           `profile_image_url` text,
                           `status_message` varchar(255) DEFAULT NULL,
                           `member_status` enum('ACTIVE','DELETED','PENDING_DELETION','WAITING') NOT NULL,
                           `role` enum('ADMINISTRATOR','GENERAL','INSRTUCTOR') NOT NULL,
                           PRIMARY KEY (`member_id`),
                           UNIQUE KEY `UKlq5wej6688i1bd6b5c11neptj` (`login_id`),
                           UNIQUE KEY `UKe6u9u9ypoc7oldnpxdjwcdx3` (`nickname`),
                           UNIQUE KEY `UK9d30a9u1qpg8eou0otgkwrp5d` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- algoduck_local_dev.problems definition

CREATE TABLE `problems` (
                            `difficulty` int NOT NULL,
                            `memory_limitation` int DEFAULT NULL,
                            `time_limitation` int DEFAULT NULL,
                            `created_at` datetime(6) DEFAULT NULL,
                            `modified_at` datetime(6) DEFAULT NULL,
                            `problem_id` bigint NOT NULL,
                            `description` text NOT NULL,
                            `input_description` text NOT NULL,
                            `output_description` text NOT NULL,
                            `problem_number` varchar(255) NOT NULL,
                            `title` varchar(255) NOT NULL,
                            PRIMARY KEY (`problem_id`),
                            UNIQUE KEY `UKrx2o7ywheuukoat9j590erwgb` (`problem_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- algoduck_local_dev.aliases definition

CREATE TABLE `aliases` (
                           `algorithm_id` bigint NOT NULL,
                           `alias_id` bigint NOT NULL,
                           `created_at` datetime(6) DEFAULT NULL,
                           `modified_at` datetime(6) DEFAULT NULL,
                           `alias_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                           PRIMARY KEY (`alias_id`),
                           KEY `FKjd0agnlivtd4q4hxdkfv1k5cx` (`algorithm_id`),
                           CONSTRAINT `FKjd0agnlivtd4q4hxdkfv1k5cx` FOREIGN KEY (`algorithm_id`) REFERENCES `algorithms` (`algorithm_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- algoduck_local_dev.problems_algorithms definition

CREATE TABLE `problems_algorithms` (
                                       `algorithm_id` bigint NOT NULL,
                                       `created_at` datetime(6) DEFAULT NULL,
                                       `modified_at` datetime(6) DEFAULT NULL,
                                       `problem_algorithm_id` bigint NOT NULL,
                                       `problem_id` bigint NOT NULL,
                                       PRIMARY KEY (`problem_algorithm_id`),
                                       KEY `FKedidxhfamyec01xh6t0tsinsh` (`algorithm_id`),
                                       KEY `FK23w7shis2jfcihe3g4pmp552b` (`problem_id`),
                                       CONSTRAINT `FK23w7shis2jfcihe3g4pmp552b` FOREIGN KEY (`problem_id`) REFERENCES `problems` (`problem_id`),
                                       CONSTRAINT `FKedidxhfamyec01xh6t0tsinsh` FOREIGN KEY (`algorithm_id`) REFERENCES `algorithms` (`algorithm_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- algoduck_local_dev.problem_images definition

CREATE TABLE `problem_images` (
                                  `created_at` datetime(6) DEFAULT NULL,
                                  `modified_at` datetime(6) DEFAULT NULL,
                                  `problem_id` bigint NOT NULL,
                                  `problem_image_id` bigint NOT NULL,
                                  `problem_image_name` varchar(255) NOT NULL,
                                  `problem_image_url` text NOT NULL,
                                  PRIMARY KEY (`problem_image_id`),
                                  KEY `FK8xvmxm0kyntcniom2kdvhjdn7` (`problem_id`),
                                  CONSTRAINT `FK8xvmxm0kyntcniom2kdvhjdn7` FOREIGN KEY (`problem_id`) REFERENCES `problems` (`problem_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- algoduck_local_dev.testcases definition

CREATE TABLE `testcases` (
                             `is_public` bit(1) NOT NULL,
                             `created_at` datetime(6) DEFAULT NULL,
                             `modified_at` datetime(6) DEFAULT NULL,
                             `problem_id` bigint NOT NULL,
                             `testcase_id` bigint NOT NULL,
                             `testcase_input_name` varchar(255) NOT NULL,
                             `testcase_input_url` text NOT NULL,
                             `testcase_output_name` varchar(255) NOT NULL,
                             `testcase_output_url` text NOT NULL,
                             PRIMARY KEY (`testcase_id`),
                             KEY `FKme0641jfjwft3ryqgkcepbf12` (`problem_id`),
                             CONSTRAINT `FKme0641jfjwft3ryqgkcepbf12` FOREIGN KEY (`problem_id`) REFERENCES `problems` (`problem_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- algoduck_local_dev.versions definition

CREATE TABLE `versions` (
                            `created_at` datetime(6) DEFAULT NULL,
                            `language_id` bigint NOT NULL,
                            `modified_at` datetime(6) DEFAULT NULL,
                            `version_id` bigint NOT NULL,
                            `version_name` varchar(255) NOT NULL,
                            PRIMARY KEY (`version_id`),
                            KEY `FKgjwbbtga3wqe92watcq07h4k2` (`language_id`),
                            CONSTRAINT `FKgjwbbtga3wqe92watcq07h4k2` FOREIGN KEY (`language_id`) REFERENCES `languages` (`language_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- algoduck_local_dev.submissions definition

CREATE TABLE `submissions` (
                               `execution_time` int NOT NULL,
                               `memory_usage` int NOT NULL,
                               `created_at` datetime(6) DEFAULT NULL,
                               `member_id` bigint NOT NULL,
                               `modified_at` datetime(6) DEFAULT NULL,
                               `problem_id` bigint NOT NULL,
                               `submission_id` bigint NOT NULL,
                               `version_id` bigint NOT NULL,
                               `code_name` varchar(255) NOT NULL,
                               `code_url` text NOT NULL,
                               `status` enum('ACCEPTED','COMPILE_ERROR','JUDGING','MEMORY_LIMIT_EXCEEDED','RUNTIME_ERROR','TIME_LIMIT_EXCEEDED','WRONG_ANSWER') NOT NULL,
                               PRIMARY KEY (`submission_id`),
                               UNIQUE KEY `UK5kqts7r3rd33i3ufjaytu1wok` (`version_id`),
                               KEY `FKq08lqmradv74odwjej8rp5i0w` (`member_id`),
                               KEY `FKj5kbdqokftgx992cx24x3s583` (`problem_id`),
                               CONSTRAINT `FK7ye47qxirx9u9lbptfkpx1ehj` FOREIGN KEY (`version_id`) REFERENCES `versions` (`version_id`),
                               CONSTRAINT `FKj5kbdqokftgx992cx24x3s583` FOREIGN KEY (`problem_id`) REFERENCES `problems` (`problem_id`),
                               CONSTRAINT `FKq08lqmradv74odwjej8rp5i0w` FOREIGN KEY (`member_id`) REFERENCES `members` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
