const fastify = require("fastify")();

fastify.register(require("@fastify/postgres"), {
	connectionString: "postgres://postgres:admin@localhost:5432/postgres"

});

//get all users
//http://localhost:3000/users
fastify.get('/users', async (request, reply) => {
	const client = await fastify.pg.connect();
	try {
	  const { rows } = await client.query('SELECT * FROM users');
	  reply.send(rows);
	} catch (err) {
	  reply.send(err);
	} finally {
	  client.release();
	}
});

// Route to get a specific user by username
//http://localhost:3000/users/testuser1
fastify.get('/users/:username', async (request, reply) => {
	const { username } = request.params;
	const client = await fastify.pg.connect();
	try {
	  const { rows } = await client.query('SELECT * FROM users WHERE username = $1', [username]);
	  if (rows.length === 0) {
		reply.status(404).send({ error: 'User not found' });
	  } else {
		reply.send(rows[0]);
	  }
	} catch (err) {
	  reply.status(500).send(err);
	} finally {
	  client.release();
	}
});


// Route to add a new user
// http://localhost:3000/users?username=user&password=password&email=user@example.com&score=50&country=UK
fastify.post('/users', async (request, reply) => {
	const { username, password, email, score, country } = request.query;
  
	// Basic validation
	if (!username || !password || !email) {
	  reply.status(400).send({ error: 'Username, password, and email are required' });
	  return;
	}
  
	const client = await fastify.pg.connect();
	try {
	  // Check if the username already exists
	  const usernameCheck = await client.query('SELECT 1 FROM users WHERE username = $1', [username]);
	  if (usernameCheck.rowCount > 0) {
		reply.status(409).send({ error: 'Username already exists' });
		return;
	  }
  
	  // Check if the email already exists
	  const emailCheck = await client.query('SELECT 1 FROM users WHERE email = $1', [email]);
	  if (emailCheck.rowCount > 0) {
		reply.status(409).send({ error: 'Email already exists' });
		return;
	  }
  
	  // Insert the new user
	  const query = 'INSERT INTO users (username, password, email, score, country) VALUES ($1, $2, $3, $4, $5)';
	  await client.query(query, [username, password, email, score || 0, country || null]);
	  reply.status(201).send({ message: 'User added successfully' });
	} catch (err) {
	  reply.status(500).send(err);
	} finally {
	  client.release();
	}
  });


// Route to check if a user exists by username
//http://localhost:3000/users/exists/username/testuser1
fastify.get('/users/exists/username/:username', async (request, reply) => {
	const { username } = request.params;
	const client = await fastify.pg.connect();
	try {
	  const { rowCount } = await client.query('SELECT 1 FROM users WHERE username = $1', [username]);
	  if (rowCount === 0) {
		reply.send({ exists: false });
	  } else {
		reply.send({ exists: true });
	  }
	} catch (err) {
	  reply.status(500).send(err);
	} finally {
	  client.release();
	}
});


// Route to check if a user exists by email
//http://localhost:3000/users/exists/email/testuser1@mail.com
fastify.get('/users/exists/email/:email', async (request, reply) => {
	const { email } = request.params;
	const client = await fastify.pg.connect();
	try {
	  const { rowCount } = await client.query('SELECT 1 FROM users WHERE email = $1', [email]);
	  if (rowCount === 0) {
		reply.send({ exists: false });
	  } else {
		reply.send({ exists: true });
	  }
	} catch (err) {
	  reply.status(500).send(err);
	} finally {
	  client.release();
	}
});


// Route to authenticate user
//http://localhost:3000/login?username=existinguser&password=correctpassword
fastify.get('/login', async (request, reply) => {
	const { username, password } = request.query;
  
	// Basic validation
	if (!username || !password) {
	  reply.status(400).send({ error: 'Username and password are required' });
	  return;
	}
  
	const client = await fastify.pg.connect();
	try {
	  // Retrieve the stored password for the given username
	  const result = await client.query('SELECT password FROM users WHERE username = $1', [username]);
	  if (result.rows.length === 0) {
		reply.status(404).send({ error: 'User not found' });
		return;
	  }
  
	  const storedPassword = result.rows[0].password;
  
	  // Compare the stored password with the provided password
	  if (storedPassword !== password) {
		reply.status(401).send({ error: 'Invalid credentials' });
	  } else {
		reply.send({ message: 'Login successful' });
	  }
	} catch (err) {
	  reply.status(500).send(err);
	} finally {
	  client.release();
	}
  });


// Route to get user's score by username
// http://localhost:3000/users/existinguser/score
fastify.get('/users/:username/score', async (request, reply) => {
	const { username } = request.params;
	const client = await fastify.pg.connect();
	try {
	  const result = await client.query('SELECT score FROM users WHERE username = $1', [username]);
	  if (result.rows.length === 0) {
		reply.status(404).send({ error: 'User not found' });
	  } else {
		reply.send({ score: result.rows[0].score });
	  }
	} catch (err) {
	  reply.status(500).send(err);
	} finally {
	  client.release();
	}
});


fastify.listen({ port: 3000, host: "0.0.0.0" }, (err) => {
	if (err) throw err;
	console.log(`server listening on ${fastify.server.address().port}`);
});
