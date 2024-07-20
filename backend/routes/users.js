module.exports = (fastify) => {
    const getAllUsers = require("../controllers/getAllUsers")(fastify);
    const getUser = require("../controllers/getUser.js")(fastify);
    const createUser = require("../controllers/createUser")(fastify);
    const updateScore = require("../controllers/updateScore")(fastify);
    const getTops = require("../controllers/topUsers.js")(fastify);

    fastify.get("/getAllUsers", getAllUsers);
    fastify.get("/getUser", getUser);
    fastify.get("/getTops", getTops);
    fastify.post("/createUser", createUser)
    fastify.post("/updateScore", updateScore)
};