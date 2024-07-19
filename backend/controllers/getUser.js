module.exports = (fastify) => {
    return async (req, res) => {
        const username = req.body.username;
        await fastify.pg.query("INSERT INTO users (username) VALUES ($1)", [
            username,
        ]);
        res.send("ok");
    };
};