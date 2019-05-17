CREATE TABLE users (
  id         INTEGER PRIMARY KEY,
  username VARCHAR(32),
  password  VARCHAR(32),
  favorite VARCHAR(96),
  isUser INTEGER,
  isAdmin INTEGER
);