CREATE TABLE message (
    id               UUID    NOT NULL PRIMARY KEY,
    user_id          UUID    NOT NULL,
    chat_id          UUID    NOT NULL,
    message_chat_n   INTEGER NOT NULL,
    version          INTEGER NOT NULL,
    payload          VARCHAR NOT NULL
);

CREATE UNIQUE INDEX message_ux1
    ON message (chat_id ASC, message_chat_n DESC, version DESC);

CREATE TABLE chat_sequence (
    chat_id UUID      PRIMARY KEY,
    last_n            INTEGER NOT NULL
);