
 CREATE TABLE tb_category(
    id_category int PRIMARY KEY auto_increment,
    name_category VARCHAR(35) not null
    );

CREATE TABLE tb_article(
    id int PRIMARY key auto_increment,
    title VARCHAR(35) not null,
    aurthor VARCHAR(35) not null,
    description VARCHAR(90) DEFAULT 'NA',
    thumnail VARCHAR(150) not null,
    category_id int REFERENCES tb_category(id_category) on DELETE CASCADE on UPDATE CASCADE);


