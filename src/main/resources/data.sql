INSERT INTO Personal_Data(ID, FIO, DOB, phone, email, skype, avatar, target, experiences, additional_educations, examples_code)
 VALUES(1, 'Шалаев Денис Олегович', '10.05.1994 г.', 'моб. +7 960 360 89 09', 'melges73@gmail.com',
  'melges656', 'https://i.ibb.co/pvX2t9J/sumAva.jpg', 'Получение работы на должности java-стажер.',
   'Ноябрь 2014 г. - настоящее время. Программист 1С. Административная часть УлГТУ.',
   'Практиковался в решении задач на https://www.hackerrank.com/ . Ссылка на профиль: https://www.hackerrank.com/melges73',
   '<a href="https://github.com/melges656">GitHub</a>');
INSERT INTO Education(ID, education, PERSONAL_DATA_ID)
VALUES(1, 'УлГТУ. ФИСТ. Прикладная информатика (в экономике). Бакалавр.', 1);
INSERT INTO Education(ID, education, PERSONAL_DATA_ID)
VALUES(2, 'УлГТУ. ФИСТ. Программная инженерия. Магистр.', 1);
INSERT INTO Skill(ID, skill, value, PERSONAL_DATA_ID)
VALUES(1, 'windows', 120, 1);
INSERT INTO Skill(ID, skill, value, PERSONAL_DATA_ID)
VALUES(2, 'linux', 12, 1);
INSERT INTO Skill(ID, skill, value, PERSONAL_DATA_ID)
VALUES(3, 'sql', 72, 1);
INSERT INTO Skill(ID, skill, value, PERSONAL_DATA_ID)
VALUES(4, 'git', 36, 1);
INSERT INTO Skill(ID, skill, value, PERSONAL_DATA_ID)
VALUES(5, 'c#', 60, 1);
INSERT INTO Skill(ID, skill, value, PERSONAL_DATA_ID)
VALUES(6, 'java', 24, 1);
