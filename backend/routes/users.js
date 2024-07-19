module.exports = (fastify) => {
    const getAllUsers = require("../controllers/getAllUsers")(fastify);
    const getUser = require("../controllers/getUser")(fastify);
    const createUser = require("../controllers/createUser")(fastify);
    const saveResume = require("../controllers/saveResume")(fastify);

    fastify.get("/users", getAllUsers);
    fastify.post("/user", getUser);
    fastify.post("/signup", createUser)
    fastify.post("/resume", saveResume)
};