-- Dodajemy użytkowników
INSERT INTO USERS (USERNAME, FIRSTNAME, LASTNAME, PASSWORD, ENABLED)
VALUES
    ('user0', 'Jan', 'Kowalski', '{bcrypt}$2a$10$PkOkJGhop4i4yZIq9UwkjOE8d8ydwtFonzWPiTdR051v2x/XU6Oq6', true),
    ('user1', 'Anna', 'Nowak', '{bcrypt}$2a$10$PkOkJGhop4i4yZIq9UwkjOE8d8ydwtFonzWPiTdR051v2x/XU6Oq6', true),
    ('user2', 'Piotr', 'Wiśniewski', '{bcrypt}$2a$10$PkOkJGhop4i4yZIq9UwkjOE8d8ydwtFonzWPiTdR051v2x/XU6Oq6', true);

-- Dodajemy filmy przypisane do użytkowników
INSERT INTO MOVIES (TITLE, DIRECTOR, RELEASEDATE, USER_ID, VOTEAVERAGE)
VALUES
    ('Inception', 'Christopher Nolan', '2010-07-16', 'user0', '6,5'),
    ('The Matrix', 'Lana Wachowski', '1999-03-31', 'user0', '5,4'),
    ('Forrest Gump', 'Robert Zemeckis', '1994-07-06', 'user1', '5,5'),
    ('Titanic', 'James Cameron', '1997-12-19', 'user1', '6,6'),
    ('The Shining', 'Stanley Kubrick', '1980-05-23', 'user2', '4,4');

INSERT INTO AUTHORITIES (USERNAME, AUTHORITY) VALUES ('user0', 'ROLE_USER');
INSERT INTO AUTHORITIES (USERNAME, AUTHORITY) VALUES ('user1', 'ROLE_USER');
INSERT INTO AUTHORITIES (USERNAME, AUTHORITY) VALUES ('user2', 'ROLE_USER');