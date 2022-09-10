create SCHEMA IF NOT EXISTS db;

create TABLE db.subject (id character varying(255) PRIMARY KEY NOT NULL, code integer NOT NULL, name character varying(255));

create TABLE db.student (id character varying(255) PRIMARY KEY NOT NULL, firstName character varying(255), lastName character varying(255),
age integer NOT NULL, admissionDate timestamp without time zone);

create TABLE db.grade (id character varying(255) PRIMARY KEY NOT NULL, value integer NOT NULL, subject_id character varying(255) NOT NULL);

create TABLE db.group (id character varying(255) PRIMARY KEY NOT NULL, name character varying(255));

create TABLE db.lecturer (id character varying(255) PRIMARY KEY NOT NULL, firstname character varying(255),  lastname character varying(255),
age integer NOT NULL, subject_id character varying(255) NOT NULL);

create TABLE db.grade_student(grade_id character varying(255) NOT NULL, students_id character varying(255) NOT NULL);

create TABLE db.group_student(group_id character varying(255) NOT NULL, students_id character varying(255) NOT NULL);


insert into db.subject (id, code, name) values ('5354a108-e35f-41d5-a910-9082bba519af', 01, 'Programming');
insert into db.subject  (id, code, name) values ('03c7596e-681e-4c90-8fae-8975a353245f', 02, 'Math');
insert into db.subject  (id, code, name) values ('e0ab519d-5636-4fcc-90a9-4996e0f68f1b', 03, 'Physics');

insert into db.student (id, firstName, lastName, age, admissionDate) values ('81a4a968-d5a3-455b-9625-73621cbef0a9', 'Ivan', 'Smith', 19, '2022-09-10 18:04:05');
insert into db.student (id, firstName, lastName, age, admissionDate) values ('2c429777-3586-4a06-a097-37359c643f54', 'Peter', 'Bartender', 20, '2022-09-10 18:06:05');
insert into db.student (id, firstName, lastName, age, admissionDate) values ('fc851d78-949e-4f43-994f-1a39cf1a397c', 'Ivan', 'Bobrov', 21, '2022-09-10 18:08:05');

insert into db.grade (id, value, subject_id) values ('0571754e-fd9e-482b-8d59-24e3f94543cf', 5, '5354a108-e35f-41d5-a910-9082bba519af');
insert into db.grade (id, value, subject_id) values ('498f9549-2eee-4e5b-85fd-7e5a0079ea83', 4, '03c7596e-681e-4c90-8fae-8975a353245f');
insert into db.grade (id, value, subject_id) values ('6eb08f09-4282-49c5-86f8-6bc911521b60', 3, 'e0ab519d-5636-4fcc-90a9-4996e0f68f1b');

insert into db.group (id, name) values ('87e40414-3bae-4115-b5bf-e95093ccf98f', '1TK-31');
insert into db.group (id, name) values ('7fa68355-5a2f-4c0d-9d22-b64ac27119c5', '2LTK-2');
insert into db.group (id, name) values ('e50303b6-e317-4c4d-ab07-a3303059f10b', '121-P');

insert into db.lecturer (id, firstname, lastname, age, subject_id) values ('2e374f16-5a86-45ff-9cb8-0c6eb8573fa3', 'Lina', 'Kostenko', 50, '5354a108-e35f-41d5-a910-9082bba519af');
insert into db.lecturer (id, firstname, lastname, age, subject_id) values ('8a60872f-94f3-4e02-b68c-5893fab09456',  'Fedor', 'Emelianenko', 40, '03c7596e-681e-4c90-8fae-8975a353245f');
insert into db.lecturer (id, firstname, lastname, age, subject_id) values ('c5bb2965-c32d-4a0b-a1e8-4fad03ddea40', 'Vasiliy', 'Pupkin', 34, 'e0ab519d-5636-4fcc-90a9-4996e0f68f1b');

insert into db.grade_student(grade_id, students_id) values ('0571754e-fd9e-482b-8d59-24e3f94543cf', '81a4a968-d5a3-455b-9625-73621cbef0a9');
insert into db.grade_student(grade_id, students_id) values ('498f9549-2eee-4e5b-85fd-7e5a0079ea83', '2c429777-3586-4a06-a097-37359c643f54');
insert into db.grade_student(grade_id, students_id) values ('6eb08f09-4282-49c5-86f8-6bc911521b60', 'fc851d78-949e-4f43-994f-1a39cf1a397c');

insert into db.group_student(group_id, students_id) values ('87e40414-3bae-4115-b5bf-e95093ccf98f', '81a4a968-d5a3-455b-9625-73621cbef0a9');
insert into db.group_student(group_id, students_id) values ('7fa68355-5a2f-4c0d-9d22-b64ac27119c5', '2c429777-3586-4a06-a097-37359c643f54');
insert into db.group_student(group_id, students_id) values ('e50303b6-e317-4c4d-ab07-a3303059f10b', 'fc851d78-949e-4f43-994f-1a39cf1a397c');

