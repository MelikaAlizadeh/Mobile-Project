module.exports = (fastify) => {
    return async (req, res) => {
        try {
            const { username, password } = req.body;
            await fastify.pg.query(
                "UPDATE users SET password = $2 WHERE username = $1; ",
                [username, password]
            );
            res.send({ status: "ok" });
        } catch (error) {
            console.error('Error executing query:', error);
        }
    };
};
