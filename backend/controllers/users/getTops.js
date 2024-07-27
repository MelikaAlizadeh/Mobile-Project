module.exports = (fastify) => {
    return async (req, res) => {
        const n = req.query.n
        const QUERY = `SELECT username, score, country, RANK() OVER (ORDER BY score DESC) AS rank FROM users ORDER BY rank LIMIT $1;`;
        try {
            const result = await fastify.pg.query(QUERY, [n]);
            res.send(result.rows);
        } catch (err) {
            console.error("Database query error: ", err);
            res.status(500).send({ error: "Database query error" });
        }
    };
};
