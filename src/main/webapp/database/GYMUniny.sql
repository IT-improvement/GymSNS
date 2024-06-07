CREATE TABLE favorites
(
  feed_index  INT NOT NULL,
  user_code   INT NOT NULL,
  create_date TIMESTAMP NOT NULL DEFAULT NOW(),
  PRIMARY KEY (feed_index, user_code),
  FOREIGN KEY (feed_index) REFERENCES feeds(feed_index) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (user_code) REFERENCES users(code) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE feed_comments
(
  feed_comment_index INT NOT NULL AUTO_INCREMENT,
  feed_index         INT NOT NULL,
  user_code          INT NOT NULL,
  content            TIMESTAMP NOT NULL ,
  mod_date           TIMESTAMP NOT NULL DEFAULT NOW(),
  PRIMARY KEY (feed_comment_index),
  FOREIGN KEY (feed_index) REFERENCES feeds(feed_index) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (user_code) REFERENCES users(code) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE feed_images
(
  feed_image_index INT          NOT NULL AUTO_INCREMENT,
  feed_index       INT          NOT NULL,
  image_url        VARCHAR(255) NOT NULL,
  create_date      TIMESTAMP    NOT NULL DEFAULT NOW() ,
  PRIMARY KEY (feed_image_index),
  FOREIGN KEY (feed_index) REFERENCES feeds(feed_index) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE feeds
(
  feed_index   INT         NOT NULL AUTO_INCREMENT,
  user_code    INT         NOT NULL,
  title        VARCHAR(50) NOT NULL,
  content      TEXT        NOT NULL,
  create__date TIMESTAMP   NOT NULL DEFAULT NOW(),
  mod_date     TIMESTAMP   NULL,
  PRIMARY KEY (feed_index),
  FOREIGN KEY (user_code) REFERENCES users(code) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE users
(
    code          INT           AUTO_INCREMENT PRIMARY KEY COMMENT '고유번호',
    id            VARCHAR(20)   NOT NULL COMMENT '아이디',
    password      VARCHAR(255)  NOT NULL COMMENT '비밀번호',
    email         VARCHAR(100)  CHECK (email LIKE '%@%') COMMENT '이메일',
    name          VARCHAR(40)   NOT NULL COMMENT '이름',
    birth         VARCHAR(8)    NOT NULL COMMENT '생일',
    gender        ENUM('M','F') NOT NULL COMMENT '성별',
    telecom       VARCHAR(3)    NOT NULL CHECK(`telecom` IN ('skt', 'kt', 'lgt')) COMMENT '통신사',
    phone         VARCHAR(13)   NOT NULL CHECK(`phone` REGEXP '^[0-9]{3}-[0-9]{4}-[0-9]{4}$') COMMENT '핸드폰번호',
    profile_image VARCHAR(1000) COMMENT '프로필 이미지 링크',
    reg_date      TIMESTAMP     NOT NULL DEFAULT(NOW()) COMMENT '가입일자',
    mod_date      TIMESTAMP     DEFAULT(NOW()) ON UPDATE NOW() COMMENT '수정일자'
) AUTO_INCREMENT=1001 COMMENT '유저';


CREATE TABLE exercise_categories
(
	exercise_category_index INT         PRIMARY KEY AUTO_INCREMENT,
	user_code               INT         NOT NULL,
	name                    VARCHAR(40) NOT NULL,
	create_date             TIMESTAMP   NOT NULL DEFAULT NOW(),
	FOREIGN KEY (user_code) REFERENCES users(code) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE exercises
(
	exercise_index          INT           PRIMARY KEY AUTO_INCREMENT,
	exercise_category_index INT           NOT NULL,
	user_code               INT           NOT NULL,
	name                    VARCHAR(50)   NOT NULL,
	content                 VARCHAR(1000) NOT NULL,
	create_date             TIMESTAMP     NOT NULL DEFAULT NOW(),
	mod_date                TIMESTAMP     NULL ON UPDATE NOW(),
	FOREIGN KEY (exercise_category_index) REFERENCES exercise_categories(exercise_category_index) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (user_code) REFERENCES users(code) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE friend_requests
(
	user_code_self  INT 		NOT NULL,
	user_code_other INT 		NOT NULL,
	create_date     TIMESTAMP	NOT NULL DEFAULT NOW(),
    PRIMARY KEY (user_code_self, user_code_other),
    FOREIGN KEY (user_code_self) REFERENCES users(code) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (user_code_other) REFERENCES users(code) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE friends
(
	friend_index  INT       PRIMARY KEY AUTO_INCREMENT,
	user_code_one INT       NOT NULL,
	user_code_two INT       NOT NULL,
	create_date   TIMESTAMP NOT NULL DEFAULT NOW(),
    FOREIGN KEY (user_code_one) REFERENCES users(code) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (user_code_two) REFERENCES users(code) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE diets (
    diet_index INT AUTO_INCREMENT NOT NULL COMMENT '식단 인덱스',
    user_code INT NOT NULL COMMENT '유저 고유번호',
    food_index INT NOT NULL COMMENT '음식 인덱스',
    total_calories INT NOT NULL COMMENT '총 칼로리',
    total_protein INT NOT NULL COMMENT '총 단백질',
    create_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '식단날짜',
    mod_date TIMESTAMP NULL COMMENT '수정날짜',
    PRIMARY KEY (diet_index),
    FOREIGN KEY (user_code) REFERENCES users(code) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (food_index) REFERENCES foods(food_index) ON DELETE CASCADE ON UPDATE CASCADE
) COMMENT '식단';


CREATE TABLE foods (
    food_index INT AUTO_INCREMENT NOT NULL COMMENT '음식 인덱스',
    user_code INT NOT NULL COMMENT '유저 고유번호',
    food_category_index INT NOT NULL COMMENT '음식 카테고리 인덱스',
    food_name VARCHAR(40) NOT NULL COMMENT '음식 이름',
    protein INT NOT NULL COMMENT '단백질',
    calory INT NOT NULL COMMENT '칼로리',
    carbs INT NOT NULL COMMENT '탄수화물',
    size INT NOT NULL COMMENT '1회 제공량',
    create_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성날짜',
    PRIMARY KEY (food_index),
    FOREIGN KEY (user_code) REFERENCES users(code) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (food_category_index) REFERENCES food_categories(food_category_index) ON DELETE CASCADE ON UPDATE CASCADE
) COMMENT '음식';


CREATE TABLE food_categories (
    food_category_index INT AUTO_INCREMENT NOT NULL COMMENT '음식 카테고리 인덱스',
    user_code INT NOT NULL COMMENT '유저 고유번호',
    category_name VARCHAR(40) NOT NULL COMMENT '음식 카테고리 이름',
    category_image_url VARCHAR(255) NULL COMMENT '음식 카테고리 이미지 링크',
    create_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성날짜',
    PRIMARY KEY (food_category_index),
    FOREIGN KEY (user_code) REFERENCES users(code) ON DELETE CASCADE ON UPDATE CASCADE
) COMMENT '음식 카테고리';


SELECT title, content, create_date FROM feed;
INSERT INTO feed(user_code, title, content) VALUES(?,?,?);
SELECT title, content, user_code, create_date FROM feed WHERE feed_index = ?;
UPDATE feed SET title=?, content=?, mog_date= NOW() WHERE feed_index = ?;
DELETE FROM feed WHERE feed_index = ?;
SELECT comment, user_code, mod_date FROM feed_comment WHERE feed_index = ?;
INSERT INTO feed_comment(comment, user_code) VALUES(?, ?);
UPDATE feed_comment SET comment = ? WHERE feed_index = ?;
DELETE FROM comment WHERE feed_comment_index=?;	
SELECT image_url FROM feed_image WHERE feed_index = ?;


