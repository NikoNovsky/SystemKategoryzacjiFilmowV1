-- Dodajemy użytkowników
INSERT INTO USERS (USERNAME, FIRSTNAME, LASTNAME, PASSWORD, ENABLED)
VALUES
    ('user0', 'Jan', 'Kowalski', '{bcrypt}$2a$10$chWMGf1hYYeELZdqowYEWOENcP9/KLc.KTFaDAJDNgOjJInmNDcIu', true),
    ('user1', 'Anna', 'Nowak', '{bcrypt}$2a$10$chWMGf1hYYeELZdqowYEWOENcP9/KLc.KTFaDAJDNgOjJInmNDcIu', true),
    ('user2', 'Piotr', 'Wiśniewski', '{bcrypt}$2a$10$chWMGf1hYYeELZdqowYEWOENcP9/KLc.KTFaDAJDNgOjJInmNDcIu', true);

-- Dodajemy filmy przypisane do użytkowników
INSERT INTO MOVIES (TITLE, DIRECTOR, RELEASEDATE, USER_ID)
VALUES
    ('Inception', 'Christopher Nolan', '2010-07-16', 'user0'),
    ('The Matrix', 'Lana Wachowski', '1999-03-31', 'user0'),
    ('Forrest Gump', 'Robert Zemeckis', '1994-07-06', 'user1'),
    ('Titanic', 'James Cameron', '1997-12-19', 'user1'),
    ('The Shining', 'Stanley Kubrick', '1980-05-23', 'user2');

INSERT INTO AUTHORITIES (USERNAME, AUTHORITY) VALUES ('user0', 'ROLE_USER');
INSERT INTO AUTHORITIES (USERNAME, AUTHORITY) VALUES ('user1', 'ROLE_USER');
INSERT INTO AUTHORITIES (USERNAME, AUTHORITY) VALUES ('user2', 'ROLE_USER');