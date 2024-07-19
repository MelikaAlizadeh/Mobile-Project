module.exports = (fastify) => {
    const getAllUsers = require("../controllers/getAllUsers")(fastify);
    const getUser = require("../controllers/getUser.js")(fastify);
    const createUser = require("../controllers/createUser")(fastify);
    const saveResume = require("../controllers/saveResume")(fastify);

    fastify.get("/users", getAllUsers);
    fastify.get("/user", getUser);
    fastify.post("/signup", createUser)
    fastify.post("/resume", saveResume)
};