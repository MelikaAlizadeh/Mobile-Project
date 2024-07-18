--get all users.
SELECT * FROM users;

--check if a email is already used. if so can't register.
SELECT COUNT(*)
FROM users
WHERE email = 'given_email';

--check if a username is already used. if so can't register.
SELECT COUNT(*)
FROM users
WHERE username = 'given_username';

--check the username and password match
SELECT COUNT(*)
FROM users
WHERE username = 'given_username' AND password = 'given_password';

--get score of the user
SELECT score
FROM users
WHERE username = 'given_username';