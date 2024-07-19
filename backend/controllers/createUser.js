module.exports = (fastify) => {
    return async (req, res) => {
        try {
            const { username, password, email, score, country } = req.body;

            await fastify.pg.query(
                "INSERT INTO users (username, password, email, score, country) VALUES ($1, $2, $3, $4, $5)",
                [username, password, email, score, country]
            );
            res.send({ status: "ok" });
        } catch (error) {
            console.error('Error executing query:', error);
        }
    };
};
