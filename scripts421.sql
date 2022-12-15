CREATE TABLE faculty
(
    id    SERIAL PRIMARY KEY,
    name  CHARACTER VARYING(30),
    color CHARACTER VARYING(30),
    UNIQUE (name, color)
);

CREATE TABLE student
(
    id         SERIAL PRIMARY KEY,
    name       CHARACTER VARYING(30) NOT NULL UNIQUE,
    age        INTEGER DEFAULT 20
        CONSTRAINT positive_age CHECK (age > 15),
    faculty_id INTEGER,
    FOREIGN KEY (faculty_id) REFERENCES faculty (id)
);