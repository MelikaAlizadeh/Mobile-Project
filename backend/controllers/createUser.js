module.exports = (fastify) => {
    return async (req, res) => {
        const username = req.body.username;
        const password = req.body.password;
        const result = await fastify.pg.query(
            "INSERT INTO users (username,password) VALUES ($1,$2) RETURNING id",
            [username, password]
        );
        const id = result.rows[0].id;
        res.send(id);
    };
};