module.exports = (fastify) => {
    const getQuestions = require("../controllers/questions/getQuestions.js")(fastify);

    fastify.get("/getQuestions", getQuestions);
};