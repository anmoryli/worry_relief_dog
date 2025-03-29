-- 使用数据库 worry_relief
USE worry_relief;

-- 删除现有表（如果存在）
DROP TABLE IF EXISTS advice;
DROP TABLE IF EXISTS worries;
DROP TABLE IF EXISTS full_name;
DROP TABLE IF EXISTS users;

-- 创建用户表
CREATE TABLE users (
                       user_id INT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(255) NOT NULL UNIQUE ,
                       password VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       created_at datetime DEFAULT NOW(),
                       updated_at datetime DEFAULT NOW() ON UPDATE now()
);

-- 创建烦恼表
CREATE TABLE worries (
                         worry_id INT AUTO_INCREMENT PRIMARY KEY,
                         user_id INT,
                         content TEXT NOT NULL,
                         timestamp datetime DEFAULT NOW(),
                         resolved BOOLEAN DEFAULT FALSE,
                         advice TEXT,
                         FOREIGN KEY (user_id) REFERENCES users(user_id)
);

-- 创建建议表
CREATE TABLE advice (
                        advice_id INT AUTO_INCREMENT PRIMARY KEY,
                        worry_id INT,
                        user_id INT,
                        advice TEXT NOT NULL,
                        timestamp datetime DEFAULT NOW(),
                        FOREIGN KEY (worry_id) REFERENCES worries(worry_id),
                        FOREIGN KEY (user_id) REFERENCES users(user_id)
);

-- 插入模拟用户数据
INSERT INTO users (email, username, password) VALUES
                                                  ('user1@example.com', 'HappyPuppyLover', 'password123'),
                                                  ('user2@example.com', 'CorgiFan', 'corgiLove456'),
                                                  ('user3@example.com', 'DoggoFriend', 'doggo789');

-- 插入模拟烦恼数据
INSERT INTO worries (user_id, content, resolved, advice) VALUES
                                                             (1, '今天工作压力好大，感觉喘不过气来。', TRUE, '小狗建议你出去散散步，呼吸一下新鲜空气！'),
                                                             (1, '最近总是睡不好觉，怎么办？', FALSE, NULL),
                                                             (2, '和朋友吵架了，心情很低落。', TRUE, '小狗建议你主动联系朋友，说声对不起。'),
                                                             (3, '学习任务太多了，完全不知道从哪里开始。', FALSE, NULL),
                                                             (2, '想养一只柯基，但怕自己照顾不好。', FALSE, NULL);

-- 插入模拟建议数据
INSERT INTO advice (worry_id, user_id, advice) VALUES
                                                   (1, 1, '当你感到压力时，不妨深呼吸三次，然后告诉自己一切都会好起来的！'),
                                                   (3, 2, '如果和朋友有矛盾，可以尝试写一封信表达你的感受。'),
                                                   (4, 3, '试着把任务分成小块，一步一步完成，不要给自己太大压力。'),
                                                   (5, 2, '养宠物需要耐心和责任心，但它们会给你带来无尽的快乐！');

-- 查询数据以验证插入是否成功
SELECT * FROM users;
SELECT * FROM worries;
SELECT * FROM advice;


create TABLE full_name (
    `id` int auto_increment primary key,
    `user_id` int,
    `name` varchar(255),
    foreign key (`user_id`) references users(`user_id`)
);