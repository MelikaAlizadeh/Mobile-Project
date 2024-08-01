module.exports = (fastify) => {
    return async (req, res) => {
        try {
            const { username, country } = req.body;
            await fastify.pg.query(
                "UPDATE users SET score = $2 WHERE username = $1; ",
                [username, country]
            );
            res.send({ status: "ok" });
        } catch (error) {
            console.error('Error executing query:', error);
        }
    };
};
