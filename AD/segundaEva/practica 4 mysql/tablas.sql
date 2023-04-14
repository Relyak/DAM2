
drop table plataformas;
drop table juegos;

CREATE TABLE juegos (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(255) NOT NULL,
  genero VARCHAR(255),
  lanzamiento VARCHAR(10)

);

CREATE TABLE plataformas (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(255) NOT NULL,
  empresa VARCHAR(255),
  lanzamiento VARCHAR(10),
  juego_id INT,
  FOREIGN KEY (juego_id) REFERENCES juegos(id) ON DELETE CASCADE
);

INSERT INTO juegos (nombre, genero, lanzamiento)
VALUES
 
  ('Grand Theft Auto V', 'Acción-Aventura', '2013-09-17'),
  ('Call of Duty: Modern Warfare', 'FPS', '2019-10-25'),
  ('League of Legends', 'MOBA', '2009-10-27'),
  ('World of Warcraft', 'MMORPG', '2004-11-23'),
  ('Overwatch', 'FPS', '2016-05-24'),
  ('Fortnite', 'Battle Royale', '2017-07-25'),
  ('Super Mario Bros.', 'Plataformas', '1985-09-13'),
  ('The Legend of Zelda', 'Aventura', '1986-02-21'),
  ('Tetris', 'Puzzle', '1984-06-06'),
  ('Minecraft', 'Sandbox', '2011-11-18');

INSERT INTO plataformas (nombre, empresa, lanzamiento, juego_id)
VALUES
  ('Nintendo Switch', 'Nintendo', '2017-03-03', 1),
  ('Xbox Series X|S', 'Microsoft', '2020-11-10', 2),
  ('PC', 'N/A', '1971-01-01', 3),
  ('iOS', 'Apple', '2007-07-29', 4),
  ('Android', 'Google', '2008-09-23', 5);

