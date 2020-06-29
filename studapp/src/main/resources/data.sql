INSERT INTO students (firstName, lastName, dateOfBirth, jmbag, numberOfEcts) VALUES
  ('Ivan', 'Zrno', TO_DATE('01/10/2000', 'DD/MM/YYYY'), '0245698325', 120),
  ('Ivana', 'Pevec', TO_DATE('15/12/1999', 'DD/MM/YYYY'), '0245698324', 130),
  ('Petar', 'Matić', TO_DATE('17/11/1983', 'DD/MM/YYYY'), '0245698328', 110);

INSERT INTO courses (COURSE_ID, COURSE_NAME, COURSE_ECTS) VALUES
  (1, 'Web aplikacije u Javi', 6),
  (2, 'Programiranje u jeziku Java', 6),
  (3, 'Razvoj aplikacija na Android platformi', 5);

INSERT INTO student_course(COURSE_ID, STUDENT_JMBAG) VALUES
  (1, '0245698325'),
  (1, '0245698324'),
  (2, '0245698328'),
  (3, '0245698325');

insert into authority(id, name) values
  (1, 'ROLE_ADMIN');
insert into authority(id, name) values
  (2, 'ROLE_USER');
insert into authority(id, name) values
  (3, 'ROLE_DELETER');

insert into user (id, username, password, first_name, last_name) values
  (1, 'admin', '$2y$12$voLh/jAObL9wEeqSi.eg4OUo1phDUTPuppBmoM.4BrenA0Ctx6VDS', 'admin', 'admin');
insert into user (id, username, password, first_name, last_name) values
  (2, 'user', '$2y$12$voLh/jAObL9wEeqSi.eg4OUo1phDUTPuppBmoM.4BrenA0Ctx6VDS', 'user', 'user');
insert into user (id, username, password, first_name, last_name) values
  (3, 'deleter', '$2y$12$voLh/jAObL9wEeqSi.eg4OUo1phDUTPuppBmoM.4BrenA0Ctx6VDS', 'deleter', 'deleter');


insert into user_authority(user_id, authority_id)
values (1,1);
insert into user_authority(user_id, authority_id)
values (2,2);
insert into user_authority(user_id, authority_id)
values (3,3);