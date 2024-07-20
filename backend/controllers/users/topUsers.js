module.exports = (fastify) => {
    return async (req, res) => {
        const n = req.query.n
        const QUERY = `SELECT username, score, country FROM users ORDER BY score DESC LIMIT $1;`;
        try {
            const result = await fastify.pg.query(QUERY, [n]);
            res.send(result.rows);
        } catch (err) {
            console.error("Database query error: ", err);
            res.status(500).send({ error: "Database query error" });
        }
    };
};
