drop database if exists blogPosts;
create database blogPosts;

use blogPosts;

create table `user`(
`id` int primary key auto_increment,
`username` varchar(30) not null unique,
`password` varchar(100) not null,
`enabled` boolean not null);

create table `role`(
`id` int primary key auto_increment,
`role` varchar(30) not null
);

create table `user_role`(
`user_id` int not null,
`role_id` int not null,
primary key(`user_id`,`role_id`),
foreign key (`user_id`) references `user`(`id`),
foreign key (`role_id`) references `role`(`id`));

-- post

create table category(
 categoryID int primary key auto_increment,
 `name` varchar (30)
);

create table blog (
blogID int primary key auto_increment,
title varchar(100),
paragraph text (500),
userID int not null,
categoryID int not null,
approval boolean default (0),
`date` date, 
imagePath varchar(255) null
);

create table tag(
tagID int primary key auto_increment,
`name` varchar (30)
);

create table blog_tag(
blogID int not null,
tagID int not null,
primary key (blogID, tagID),
foreign key (blogID) references blog (blogID),
foreign key (tagID) references tag (tagID)
);



alter table blog
	add foreign key fk_blog_user(userID) references `user`(`id`);
    
alter table blog
	add foreign key fk_blog_category(categoryID) references category(categoryID);
    
    insert into `user`(`id`,`username`,`password`,`enabled`)
    values(1,"admin", "password", true),
        (2,"jasmine","password",true);

insert into `role`(`id`,`role`)
    values(1,"ROLE_ADMIN"), (2,"ROLE_CONTRIBUTOR");
    
insert into `user_role`(`user_id`,`role_id`)
    values(1,1),(1,2),(2,2);

UPDATE user SET password = '$2a$10$dZJRxNfYAqn8IRG8DCXBl.NDeT2O6xQKsPck8RcTEwaOOwMaTB4h6' WHERE id = 1;
UPDATE user SET password = '$2a$10$dZJRxNfYAqn8IRG8DCXBl.NDeT2O6xQKsPck8RcTEwaOOwMaTB4h6' WHERE id = 2;

insert into category (`name`) values ("fun");
insert into category (`name`) values ("promotion");
insert into category (`name`) values ("information");

insert into tag (`name`) values ("first post");
insert into tag (`name`) values ("exciting");



insert into blog (title, paragraph, userID, categoryID, approval, `date`)
	values ("First Post","This is the admin's first blog entry.", 1, 3, true, "2019-10-27");
    
insert into blog_tag (blogID, tagID) values (1,1);
insert into blog_tag (blogID, tagID) values (1,2);

insert into blog (title, paragraph, userID, categoryID, approval, `date`)
	values ("Second Post","This is the admin's second blog entry.", 1, 2, true, "2019-10-28");
-- $2a$10$hfOKhZr4ac58ikGQUgp.KOdUUM8lcMrdBq3dS2gOmnoInxnpuIPRa
-- $2a$10$Rvr6uvEQ67LEEExKrQA9XeHENyKqY1BUazEdng7AQwgGTSoqBLOGG


