const fastify = require("fastify")({ logger: true });

require("./plugins/postgres")(fastify);
require("./plugins/cors")(fastify);
require("./plugins/multipart")(fastify);
require("./routes/users")(fastify);

fastify.listen({ port: process.env.PORT, host: process.env.HOST });