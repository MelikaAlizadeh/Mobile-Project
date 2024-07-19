module.exports = (fastify) => {
    return async (req, res) => {
        const result = await fastify.pg.query("SELECT * FROM users");
        res.send(result.rows);
    };
};