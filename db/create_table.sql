CREATE TABLE users
(
    username VARCHAR(255) PRIMARY KEY,
    password TEXT,
    email    TEXT,
    score    INT  default 0,
    country  TEXT default null
);

CREATE TABLE quiz
(
    q_id SERIAL PRIMARY KEY,
    question TEXT,
    option1 TEXT,
    option2 TEXT,
    option3 TEXT,
    option4 TEXT,
    correct_answer INT
);

CREATE TABLE teacher
(
    teacher_id SERIAL PRIMARY KEY,
    f_name TEXT,
    l_name TEXT,
    ph_number VARCHAR(11),
    price DECIMAL,
    score FLOAT
);
