module.exports = (fastify) => {
    const createUser = require("../controllers/users/createUser.js")(fastify);
    const updateScore = require("../controllers/users/updateScore.js")(fastify);
    const updatePassword = require("../controllers/users/updatePassword.js")(fastify);
    const updateEmail = require("../controllers/users/updateEmail.js")(fastify);
    const updateCountry = require("../controllers/users/updateCountry.js")(fastify);
    fastify.post("/createUser", createUser)
    fastify.post("/updateScore", updateScore)
    fastify.post("/updatePassword", updatePassword)
    fastify.post("/updateEmail", updateEmail)
    fastify.post("/updateCountry", updateCountry)

    const getAllUsers = require("../controllers/users/getAllUsers.js")(fastify);
    const getUser = require("../controllers/users/getUser.js")(fastify);
    const getTops = require("../controllers/users/getTops.js")(fastify);
    const getRank = require("../controllers/users/getRank.js")(fastify);
    fastify.get("/getAllUsers", getAllUsers);
    fastify.get("/getUser", getUser);
    fastify.get("/getRank", getRank);
    fastify.get("/getTops", getTops);
};