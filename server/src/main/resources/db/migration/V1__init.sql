CREATE TABLE Clients (
    id BIGINT NOT NULL PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL
);

CREATE TABLE Accounts (
    id BIGINT NOT NULL PRIMARY KEY,
    number DECIMAL(20, 0),
    balance DECIMAL(19, 2),
    client_id BIGINT NOT NULL REFERENCES Clients (id)
);

CREATE TABLE Cards (
    id BIGINT NOT NULL PRIMARY KEY,
    number DECIMAL(16, 0),
    pin INTEGER,
    account_id BIGINT NOT NULL REFERENCES Accounts (id)
);