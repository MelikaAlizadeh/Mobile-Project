require("dotenv").config()

const fastify = require("fastify")({ logger: true });

require("./plugins/postgres.js")(fastify);
require("./plugins/cors.js")(fastify);
require("./plugins/multipart.js")(fastify);
require("./routes/users.js")(fastify);
require("./routes/questions.js")(fastify);

fastify.listen({ port: process.env.PORT, host: process.env.HOST });