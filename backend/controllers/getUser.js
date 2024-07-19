module.exports = (fastify) => {
    return async (req, res) => {
        const username = req.query.username;
        const QUERY = `SELECT * FROM users WHERE username = $1;`;
        try {
            const result = await fastify.pg.query(QUERY, [username]);
            res.send(result.rows[0]);
        } catch (err) {
            console.error("Database query error: ", err);
            res.status(500).send({ error: "Database query error" });
        }
    };
};
