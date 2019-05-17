CREATE TABLE comments (
  id         INTEGER PRIMARY KEY,
  postid INTEGER,
  theComment VARCHAR(128),
  author VARCHAR(32)
  );