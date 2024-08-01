module.exports = (fastify) => {
    return async (req, res) => {
        const username = req.query.username;
        QUERY = `select * from
             (SELECT users.username, RANK() OVER (ORDER BY score DESC) AS rank
              FROM users
             ) a where a.username = $1;`;
        try {
            const result = await fastify.pg.query(QUERY, [username]);
            res.send(result.rows[0]);
        } catch (err) {
            console.error("Database query error: ", err);
            res.status(500).send({ error: "Database query error" });
        }
    };
};
