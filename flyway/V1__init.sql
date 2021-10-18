DROP TABLE IF EXISTS `products`;
CREATE TABLE `products`
(
    `id`         int                             NOT NULL AUTO_INCREMENT,
    `title`      varchar(45) COLLATE utf8mb4_bin NOT NULL,
    `cost`       int                             NOT NULL,
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

INSERT INTO `products` (`title`, `cost`)
VALUES ('МОЛОКО', 60);
INSERT INTO `products` (`title`, `cost`)
VALUES ('ХЛЕБ', 40);
INSERT INTO `products` (`title`, `cost`)
VALUES ('ПЕЧЕНЬЕ', 80);
INSERT INTO `products` (`title`, `cost`)
VALUES ('ВОДА', 40);
INSERT INTO `products` (`title`, `cost`)
VALUES ('КОНФЕТЫ', 100);
INSERT INTO `products` (`title`, `cost`)
VALUES ('СЫР', 100);
INSERT INTO `products` (`title`, `cost`)
VALUES ('КОЛБАСА', 200);
INSERT INTO `products` (`title`, `cost`)
VALUES ('ЙОГУРТ', 50);
INSERT INTO `products` (`title`, `cost`)
VALUES ('СОК', 100);
INSERT INTO `products` (`title`, `cost`)
VALUES ('МОРКОВЬ', 40);
INSERT INTO `products` (`title`, `cost`)
VALUES ('КАРТОФЕЛЬ', 50);
INSERT INTO `products` (`title`, `cost`)
VALUES ('САЛФЕТКИ', 20);
INSERT INTO `products` (`title`, `cost`)
VALUES ('РИС', 90);
INSERT INTO `products` (`title`, `cost`)
VALUES ('МУКА', 60);
INSERT INTO `products` (`title`, `cost`)
VALUES ('САХАР', 40);
INSERT INTO `products` (`title`, `cost`)
VALUES ('СОЛЬ', 10);
INSERT INTO `products` (`title`, `cost`)
VALUES ('МАСЛО', 120);
INSERT INTO `products` (`title`, `cost`)
VALUES ('ПРИПРАВА', 40);
INSERT INTO `products` (`title`, `cost`)
VALUES ('НАПИТОК', 70);
INSERT INTO `products` (`title`, `cost`)
VALUES ('СПИЧКИ', 10);


DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`
(
    `id`         INT          NOT NULL AUTO_INCREMENT,
    `first_name` varchar(80)  NOT NULL,
    `last_name`  varchar(80)  NOT NULL,
    `username`   VARCHAR(255) NOT NULL,
    `password`   VARCHAR(255) NOT NULL,
    `email`      VARCHAR(255) NOT NULL,
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_bin;

DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles`
(
    `id`         INT         NOT NULL AUTO_INCREMENT,
    `name`       VARCHAR(50) NOT NULL,
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_bin;

DROP TABLE IF EXISTS `users_roles`;
CREATE TABLE `users_roles`
(
    `user_id` INT NOT NULL,
    `role_id` INT NOT NULL,
    PRIMARY KEY (`user_id`, `role_id`),
    INDEX `#role_id_idx` (`role_id` ASC) VISIBLE,
    CONSTRAINT `#user_id`
        FOREIGN KEY (`user_id`)
            REFERENCES `users` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `#role_id`
        FOREIGN KEY (`role_id`)
            REFERENCES `roles` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_bin;

DROP TABLE IF EXISTS `permissions`;
CREATE TABLE `permissions`
(
    `id`         INT         NOT NULL AUTO_INCREMENT,
    `name`       VARCHAR(50) NOT NULL,
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_bin;

DROP TABLE IF EXISTS `roles_permissions`;
CREATE TABLE `roles_permissions`
(
    `role_id`       INT NOT NULL,
    `permission_id` INT NOT NULL,
    PRIMARY KEY (`role_id`, `permission_id`),
    INDEX `#permission_id_idx` (`permission_id` ASC) VISIBLE,
    CONSTRAINT `#roles_id`
        FOREIGN KEY (`role_id`)
            REFERENCES `roles` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `#permission_id`
        FOREIGN KEY (`permission_id`)
            REFERENCES `permissions` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_bin;

DROP TABLE IF EXISTS `orders` CASCADE;
CREATE TABLE `orders`
(
    `id`         INT          NOT NULL AUTO_INCREMENT,
    `user_id`    INT          NULL,
    `phone`      VARCHAR(12)  NOT NULL,
    `address`    VARCHAR(255) NOT NULL,
    `price`      INT          NOT NULL,
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    INDEX `#user_id_from_orders_idx` (`user_id` ASC) VISIBLE,
    CONSTRAINT `#user_id_from_orders`
        FOREIGN KEY (`user_id`)
            REFERENCES `users` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_bin;

DROP TABLE IF EXISTS `order_items`;
CREATE TABLE `order_items`
(
    `id`                INT NOT NULL AUTO_INCREMENT,
    `order_id`          INT NULL,
    `product_id`        INT NULL,
    `quantity`          INT NOT NULL,
    `price`             INT NOT NULL,
    `price_per_product` INT NOT NULL,
    `created_at`        DATETIME DEFAULT CURRENT_TIMESTAMP,
    `updated_at`        DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    INDEX `#order_id_from_order_items_idx` (`order_id` ASC) VISIBLE,
    CONSTRAINT `#order_id_from_order_items`
        FOREIGN KEY (`order_id`)
            REFERENCES `orders` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    INDEX `#product_id_from_products_idx` (`product_id` ASC) VISIBLE,
    CONSTRAINT `#product_id_from_products`
        FOREIGN KEY (`product_id`)
            REFERENCES `products` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_bin;

DROP TABLE IF EXISTS `comments`;
CREATE TABLE `comments`
(
    `id`                INT NOT NULL AUTO_INCREMENT,
    `text`              VARCHAR(255) NOT NULL ,
    `product_id`        INT NOT NULL,
    `user_id`           INT NOT NULL,
    `created_at`        DATETIME DEFAULT CURRENT_TIMESTAMP,
    `updated_at`        DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    INDEX `#product_id_from_comment_idx` (`product_id` ASC) VISIBLE,
    CONSTRAINT `#product_id_from_comment`
        FOREIGN KEY (`product_id`)
            REFERENCES `products` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    INDEX `#user_id_from_comment_idx` (`user_id` ASC) VISIBLE,
    CONSTRAINT `#user_id_from_comment`
        FOREIGN KEY (`user_id`)
            REFERENCES `users` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_bin;

INSERT INTO `roles` (`name`)
VALUES ('ROLE_SUPER_ADMIN');
INSERT INTO `roles` (`name`)
VALUES ('ROLE_ADMIN');
INSERT INTO `roles` (`name`)
VALUES ('ROLE_USER');

INSERT INTO `users` (`username`, `first_name`, `last_name`, `password`, `email`)
VALUES ('den', 'Denis', 'Johnson', '$2a$12$ka6mPQiMeuCt63OZN6gBv.oBjek8Y4hGpjmoOI0CSQwnHH3dwxK7a', 'den@gmail.com');
INSERT INTO `users` (`username`, `first_name`, `last_name`, `password`, `email`)
VALUES ('max', 'Max', 'Johnson', '$2a$12$Az67LePTNyed4qP9lFHzYuPgQhrU/lqubAoaA21tgqDsUogCQ8g8y', 'max@gmail.com');
INSERT INTO `users` (`username`, `first_name`, `last_name`, `password`, `email`)
VALUES ('bob', 'Bob', 'Johnson', '$2a$12$Rqem84t/higAKk5HqpXn0eGgpftk0UjlcHThSAN4IxZKtvcbaHRbO', 'bob@gmail.com');

INSERT INTO `permissions` (`name`)
VALUES ('READ');
INSERT INTO `permissions` (`name`)
VALUES ('WRITE');
INSERT INTO `permissions` (`name`)
VALUES ('DELETE');
INSERT INTO `permissions` (`name`)
VALUES ('CREATE');

INSERT INTO `users_roles` (`user_id`, `role_id`)
VALUES ('1', '1');
INSERT INTO `users_roles` (`user_id`, `role_id`)
VALUES ('2', '2');
INSERT INTO `users_roles` (`user_id`, `role_id`)
VALUES ('3', '3');

INSERT INTO `roles_permissions` (`role_id`, `permission_id`)
VALUES ('1', '1');
INSERT INTO `roles_permissions` (`role_id`, `permission_id`)
VALUES ('1', '2');
INSERT INTO `roles_permissions` (`role_id`, `permission_id`)
VALUES ('1', '3');
INSERT INTO `roles_permissions` (`role_id`, `permission_id`)
VALUES ('1', '4');
INSERT INTO `roles_permissions` (`role_id`, `permission_id`)
VALUES ('2', '1');
INSERT INTO `roles_permissions` (`role_id`, `permission_id`)
VALUES ('2', '2');
INSERT INTO `roles_permissions` (`role_id`, `permission_id`)
VALUES ('2', '3');
INSERT INTO `roles_permissions` (`role_id`, `permission_id`)
VALUES ('3', '1');
INSERT INTO `roles_permissions` (`role_id`, `permission_id`)
VALUES ('3', '2');