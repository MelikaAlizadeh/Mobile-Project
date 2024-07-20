module.exports = (fastify) => {
    return async (req, res) => {
        const QUERY = `SELECT * FROM users;`;
        try {
            const result = await fastify.pg.query(QUERY);
            res.send(result.rows);
        } catch (err) {
            console.error("Database query error: ", err);
            res.status(500).send({ error: "Database query error" });
        }
    };
};
