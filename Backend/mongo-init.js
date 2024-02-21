db.createUser(
    {
        user: "group28",
        pwd: "password",
        roles: [
            {
                role: "dbOwner",
                db: "testdb"
            }
        ]
    }
);