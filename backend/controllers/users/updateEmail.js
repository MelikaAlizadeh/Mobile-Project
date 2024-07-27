module.exports = (fastify) => {
    return async (req, res) => {
        try {
            const { username, email } = req.body;
            await fastify.pg.query(
                "UPDATE users SET email = $2 WHERE username = $1; ",
                [username, email]
            );
            res.send({ status: "ok" });
        } catch (error) {
            console.error('Error executing query:', error);
        }
    };
};
