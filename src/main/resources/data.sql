INSERT INTO Personal_Data(ID, FIO, DOB, phone, email, skype, avatar, target, experiences, additional_educations, examples_code)
 VALUES(0, 'Шалаев Денис Олегович', '10.05.1994 г.', 'моб. +7 960 360 89 09', 'melges73@gmail.com',
  'melges656', 'https://i.ibb.co/pvX2t9J/sumAva.jpg', 'Получение работы на должности java-стажер.',
   'Ноябрь 2014 г. - настоящее время. Программист 1С. Административная часть УлГТУ.',
   'Практиковался в решении задач на https://www.hackerrank.com/ . Ссылка на профиль: https://www.hackerrank.com/melges73',
   '<a href="https://github.com/melges656">GitHub</a>');

INSERT INTO Education(ID, education, PERSONAL_DATA_ID)
VALUES(0, 'УлГТУ. ФИСТ. Прикладная информатика (в экономике). Бакалавр.', 0);
INSERT INTO Education(ID, education, PERSONAL_DATA_ID)
VALUES(1, 'УлГТУ. ФИСТ. Программная инженерия. Магистр.', 0);

INSERT INTO Skill(ID, skill, value, PERSONAL_DATA_ID)
VALUES(0, 'windows', 120, 0);
INSERT INTO Skill(ID, skill, value, PERSONAL_DATA_ID)
VALUES(1, 'linux', 12, 0);
INSERT INTO Skill(ID, skill, value, PERSONAL_DATA_ID)
VALUES(2, 'sql', 72, 0);
INSERT INTO Skill(ID, skill, value, PERSONAL_DATA_ID)
VALUES(3, 'git', 36, 0);
INSERT INTO Skill(ID, skill, value, PERSONAL_DATA_ID)
VALUES(4, 'c#', 60, 0);
INSERT INTO Skill(ID, skill, value, PERSONAL_DATA_ID)
VALUES(5, 'java', 24, 0);

INSERT INTO Tag(Name) VALUES('Java');
INSERT INTO Tag(Name) VALUES('Spring');
INSERT INTO Tag(Name) VALUES('SQL');

INSERT INTO TAG_PERSONAL_DATA_LIST(TAGS_NAME, PERSONAL_DATA_LIST_ID) VALUES('SQL', 0);
INSERT INTO TAG_PERSONAL_DATA_LIST(TAGS_NAME, PERSONAL_DATA_LIST_ID) VALUES('Spring', 0);
INSERT INTO TAG_PERSONAL_DATA_LIST(TAGS_NAME, PERSONAL_DATA_LIST_ID) VALUES('Java', 0);