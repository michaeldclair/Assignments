drop table if exists student_class_relationship;
drop table if exists major_class_relationship;
drop table if exists assignment;
drop table if exists class;
drop table if exists instructor;
drop table if exists major;





create table major (
 id int primary key auto_increment,
 description varchar(20),
 minimum_sat int
);


create table instructor (
 id int primary key auto_increment,
 first_name varchar(30),
 last_name varchar(30),
 major_id int,
 years_experience int,
 tenure tinyint,
 foreign key (major_id) references major(id)
);


create table class (
 id int primary key auto_increment,
 description varchar(20),
 instructor_id int,
 foreign key (instructor_id) references instructor(id)
);

create table assignment (
 id int primary key,
 student_id int,
 assignment_nbr int,
 grade_id int,
 class_id int,
 foreign key (student_id) references student(id),
 foreign key (grade_id) references grade(id),
 foreign key (class_id) references class(id)
);
 


create table major_class_relationship (
 id int primary key auto_increment,
 major_id int not null,
 class_id int not null,
 foreign key (major_id) references major(id),
 foreign key (class_id) references class(id)
);


create table student_class_relationship (
 id int primary key auto_increment,
 student_id int not null,
 class_id int not null,
 foreign key (student_id) references student(id),
 foreign key (class_id) references class(id)
);




 insert major (description, minimum_sat) values ('General Business', 800);
 insert major (description, minimum_sat) values ('Accounting', 1000);
 insert major (description, minimum_sat) values ('Finance', 1100);
 insert major (description, minimum_sat) values ('Math', 1300);
 insert major (description, minimum_sat) values ('Engineering', 1350);
 insert major (description, minimum_sat) values ('Education', 900);
 insert major (description, minimum_sat) values ('General Studies', 500);

 insert instructor (first_name, last_name, major_id, years_experience, tenure) 
	values ('Bobby', 'Businessman', 1, 3, 0);
 insert instructor (first_name, last_name, major_id, years_experience, tenure) 
	values ('Billy', 'Bizkid', 1, 9, 1);
 insert instructor (first_name, last_name, major_id, years_experience, tenure) 
	values ('Adam', 'Accountant', 2, 2, 0);
 insert instructor (first_name, last_name, major_id, years_experience, tenure) 
	values ('Alice', 'Accountsalot', 2, 12, 1);
 insert instructor (first_name, last_name, major_id, years_experience, tenure) 
	values ('Fred', 'Financeton', 3, 4, 0);
 insert instructor (first_name, last_name, major_id, years_experience, tenure) 
	values ('Franz', 'Financand', 3, 11, 1);
 insert instructor (first_name, last_name, major_id, years_experience, tenure) 
	values ('Mark', 'Mathenstein', 4, 4, 0);
 insert instructor (first_name, last_name, major_id, years_experience, tenure) 
	values ('Marla', 'Mathsmerrily', 4, 9, 1);
 insert instructor (first_name, last_name, major_id, years_experience, tenure) 
	values ('Edgar', 'Enginons', 5, 3, 0);
 insert instructor (first_name, last_name, major_id, years_experience, tenure) 
	values ('Ella', 'Engino', 5, 3, 0);
 insert instructor (first_name, last_name, major_id, years_experience, tenure) 
	values ('Edward', 'Educato', 6, 3, 0);
 insert instructor (first_name, last_name, major_id, years_experience, tenure) 
	values ('Eelman', 'Eelman', 6, 13, 1);
 insert instructor (first_name, last_name, major_id, years_experience, tenure) 
	values ('Gena', 'General', 7, 3, 0);
 insert instructor (first_name, last_name, major_id, years_experience, tenure) 
	values ('Solomon', 'Studies', 7, 10, 1);


 insert class (description, instructor_id) values ('English 101', 13);
 insert class (description, instructor_id) values ('English 102', 13);
 insert class (description, instructor_id) values ('English 103', 13);
 insert class (description, instructor_id) values ('English 201', 14);
 insert class (description, instructor_id) values ('English 202', 14);
 insert class (description, instructor_id) values ('English 203', 14);
 insert class (description, instructor_id) values ('English 301', 13);
 insert class (description, instructor_id) values ('English 302', 14);
 insert class (description, instructor_id) values ('English 303', 13);
 insert class (description, instructor_id) values ('Math 201', 1);
 insert class (description, instructor_id) values ('Math 202', 1);
 insert class (description, instructor_id) values ('Math 203', 2);
 insert class (description, instructor_id) values ('Math 204', 2);
 insert class (description, instructor_id) values ('Math 301', 7);
 insert class (description, instructor_id) values ('Math 302', 7);
 insert class (description, instructor_id) values ('Math 303', 8);
 insert class (description, instructor_id) values ('Math 304', 8);
 insert class (description, instructor_id) values ('History 101', 11);
 insert class (description, instructor_id) values ('History 201', 11);
 insert class (description, instructor_id) values ('History 301', 12);
 insert class (description, instructor_id) values ('Computer Science 311', 9);
 insert class (description, instructor_id) values ('Computer Science 312', 9);
 insert class (description, instructor_id) values ('Computer Science 313', 9);
 insert class (description, instructor_id) values ('Computer Science 441', 10);
 insert class (description, instructor_id) values ('Computer Science 442', 10);
 insert class (description, instructor_id) values ('Computer Science 443', 10);
 insert class (description, instructor_id) values ('Psychology 101', 1);
 insert class (description, instructor_id) values ('Psychology 102', 2);
 insert class (description, instructor_id) values ('Psychology 231', 3);
 insert class (description, instructor_id) values ('Psychology 232', 13);
 insert class (description, instructor_id) values ('Education 221', 3);
 insert class (description, instructor_id) values ('Education 222', 3);
 insert class (description, instructor_id) values ('Education 223', 4);
 insert class (description, instructor_id) values ('Education 351', 4);
 insert class (description, instructor_id) values ('Education 352', 5);
 insert class (description, instructor_id) values ('Education 353', 6);

 insert major_class_relationship (major_id, class_id) values (2, 1);
 insert major_class_relationship (major_id, class_id) values (1, 2);
 insert major_class_relationship (major_id, class_id) values (7, 3);
 insert major_class_relationship (major_id, class_id) values (2, 4);
 insert major_class_relationship (major_id, class_id) values (1, 5);
 insert major_class_relationship (major_id, class_id) values (7, 6);
 insert major_class_relationship (major_id, class_id) values (7, 7);
 insert major_class_relationship (major_id, class_id) values (7, 8);
 insert major_class_relationship (major_id, class_id) values (7, 9);
 insert major_class_relationship (major_id, class_id) values (2, 10);
 insert major_class_relationship (major_id, class_id) values (2, 11);
 insert major_class_relationship (major_id, class_id) values (3, 12);
 insert major_class_relationship (major_id, class_id) values (3, 13);
 insert major_class_relationship (major_id, class_id) values (4, 14);
 insert major_class_relationship (major_id, class_id) values (4, 15);
 insert major_class_relationship (major_id, class_id) values (4, 16);
 insert major_class_relationship (major_id, class_id) values (4, 17);
 insert major_class_relationship (major_id, class_id) values (7, 18);
 insert major_class_relationship (major_id, class_id) values (7, 19);
 insert major_class_relationship (major_id, class_id) values (7, 20);
 insert major_class_relationship (major_id, class_id) values (5, 21);
 insert major_class_relationship (major_id, class_id) values (5, 22);
 insert major_class_relationship (major_id, class_id) values (3, 23);
 insert major_class_relationship (major_id, class_id) values (3, 24);
 insert major_class_relationship (major_id, class_id) values (5, 25);
 insert major_class_relationship (major_id, class_id) values (5, 26);
 insert major_class_relationship (major_id, class_id) values (7, 27);
 insert major_class_relationship (major_id, class_id) values (7, 28);
 insert major_class_relationship (major_id, class_id) values (7, 29);
 insert major_class_relationship (major_id, class_id) values (7, 30);
 insert major_class_relationship (major_id, class_id) values (1, 31);
 insert major_class_relationship (major_id, class_id) values (1, 32);
 insert major_class_relationship (major_id, class_id) values (2, 33);
 insert major_class_relationship (major_id, class_id) values (6, 34);
 insert major_class_relationship (major_id, class_id) values (6, 35);
 insert major_class_relationship (major_id, class_id) values (6, 36);

 insert student_class_relationship (student_id, class_id) values (100, 2);
 insert student_class_relationship (student_id, class_id) values (100, 5);
 insert student_class_relationship (student_id, class_id) values (110, 1);
 insert student_class_relationship (student_id, class_id) values (110, 4);
 insert student_class_relationship (student_id, class_id) values (120, 12);
 insert student_class_relationship (student_id, class_id) values (120, 13);
 insert student_class_relationship (student_id, class_id) values (130, 14);
 insert student_class_relationship (student_id, class_id) values (130, 15);
 insert student_class_relationship (student_id, class_id) values (140, 21);
 insert student_class_relationship (student_id, class_id) values (140, 22);
 insert student_class_relationship (student_id, class_id) values (150, 34);
 insert student_class_relationship (student_id, class_id) values (150, 35);
 insert student_class_relationship (student_id, class_id) values (160, 27);
 insert student_class_relationship (student_id, class_id) values (160, 28);
 insert student_class_relationship (student_id, class_id) values (170, 31);
 insert student_class_relationship (student_id, class_id) values (170, 32);
 insert student_class_relationship (student_id, class_id) values (180, 10);
 insert student_class_relationship (student_id, class_id) values (180, 11);
 insert student_class_relationship (student_id, class_id) values (190, 23);
 insert student_class_relationship (student_id, class_id) values (190, 24);


-- Sees which classes a student has taken
select description as 'Classes Taken' from class where id in (
select class_id from student_class_relationship where student_id = 160);


-- Sees which classes a student still needs to take for their major
select description as 'Classes Not Yet Taken' from class where id in (
select class_id from major_class_relationship 
	where major_id = (select major_id from student where id = 160) 
	and class_id not in (select class_id from student_class_relationship where student_id = 160));






-- DELIMITER $$
-- CREATE TRIGGER `instructor_trg_ins`  BEFORE INSERT ON `instructor`

-- FOR EACH ROW

-- BEGIN
   
-- IF NOT(New.years_experience > 0) THEN
   
-- SIGNAL SQLSTATE '10000'
       
-- SET MESSAGE_TEXT = 'check constraint on instructor failed during insert';
   
-- END IF;

-- END;

-- $$DELIMITER;