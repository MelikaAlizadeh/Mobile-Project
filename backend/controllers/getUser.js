module.exports = (fastify) => {
    return async (req, res) => {
        const username = req.query.username;
        const email = req.query.email;
        try {
            let QUERY = ""
            let input = ""
            if (username) {
                QUERY = `SELECT * FROM users WHERE username = $1;`;
                input = username;
            } else if (email) {
                QUERY = `SELECT * FROM users WHERE email = $1;`;
                input = email;
            }

            const result = await fastify.pg.query(QUERY, [input]);
            res.send(result.rows[0]);
        } catch (err) {
            console.error("Database query error: ", err);
            res.status(500).send({ error: "Database query error" });
        }
    };
};
