USE test;
Drop table if EXISTS parts;
CREATE TABLE `parts` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(255) NOT NULL,
  `NECESSARILY` TINYINT NOT NULL,
  `COUNT` INT NOT NULL,
  PRIMARY KEY (`id`))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;

insert into parts(NAME, NECESSARILY, COUNT) value ('Материнская плата', 1, 5);
insert into parts(NAME, NECESSARILY, COUNT) value ('Процессор', 1, 5);
insert into parts(NAME, NECESSARILY, COUNT) value ('Венитяляторы', 0, 5);
insert into parts(NAME, NECESSARILY, COUNT) value ('Винчестер', 1, 5);
insert into parts(NAME, NECESSARILY, COUNT) value ('Видеокарта', 1, 5);
insert into parts(NAME, NECESSARILY, COUNT) value ('Оперативная память', 1, 5);
insert into parts(NAME, NECESSARILY, COUNT) value ('Блок питания', 1, 5);
insert into parts(NAME, NECESSARILY, COUNT) value ('Корпус', 0, 5);
insert into parts(NAME, NECESSARILY, COUNT) value ('Подсветка',0, 5);
insert into parts(NAME, NECESSARILY, COUNT) value ('Монитор', 1, 5);
insert into parts(NAME, NECESSARILY, COUNT) value ('Клавиатура', 1, 5);
insert into parts(NAME, NECESSARILY, COUNT) value ('Мышь', 1, 5);
insert into parts(NAME, NECESSARILY, COUNT) value ('Стереосистема', 0, 5);
insert into parts(NAME, NECESSARILY, COUNT) value ('Наушники', 0, 5);
insert into parts(NAME, NECESSARILY, COUNT) value ('Звуковая карта', 1, 5);
insert into parts(NAME, NECESSARILY, COUNT) value ('Внешняя звуковая карта', 0, 5);
insert into parts(NAME, NECESSARILY, COUNT) value ('Джостик', 0, 5);
insert into parts(NAME, NECESSARILY, COUNT) value ('Компьютерный руль', 0, 5);
insert into parts(NAME, NECESSARILY, COUNT) value ('Программатор', 0, 5);
insert into parts(NAME, NECESSARILY, COUNT) value ('Сетевая карта', 1, 5);
insert into parts(NAME, NECESSARILY, COUNT) value ('TV тюнер', 0, 5);
insert into parts(NAME, NECESSARILY, COUNT) value ('Принтер', 0, 5);
insert into parts(NAME, NECESSARILY, COUNT) value ('3D принтер', 1, 5);
insert into parts(NAME, NECESSARILY, COUNT) value ('USB винтелятор', 1, 5);
