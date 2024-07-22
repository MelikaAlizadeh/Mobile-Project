module.exports = (fastify) => {
    const getAllUsers = require("../controllers/users/getAllUsers.js")(fastify);
    const getUser = require("../controllers/users/getUser.js")(fastify);
    const createUser = require("../controllers/users/createUser.js")(fastify);
    const updateScore = require("../controllers/users/updateScore.js")(fastify);
    const getTops = require("../controllers/users/topUsers.js")(fastify);

    fastify.get("/getAllUsers", getAllUsers);
    fastify.get("/getUser", getUser);
    fastify.get("/getTops", getTops);
    fastify.post("/createUser", createUser)
    fastify.post("/updateScore", updateScore)
};