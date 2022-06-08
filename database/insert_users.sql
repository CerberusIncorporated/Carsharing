
USE carsharing;

INSERT INTO users(email, user_password, role)
	VALUES ('user@gmail.com', md5('12345678'), 1),
		   ('admin@gmail.com', md5('administrator'), 2);