CREATE TABLE IF NOT EXISTS files (
                                     id UUID PRIMARY KEY,
                                     name VARCHAR(255) NOT NULL,
    content BYTEA NOT NULL,
    content_type VARCHAR(100) NOT NULL,
    size BIGINT NOT NULL,
    upload_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

CREATE TABLE IF NOT EXISTS analysis_results (
                                                file_id UUID PRIMARY KEY REFERENCES files(id) ON DELETE CASCADE,
    paragraphs INTEGER NOT NULL,
    words INTEGER NOT NULL,
    characters INTEGER NOT NULL,
    analysis_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

CREATE INDEX idx_files_name ON files(name);
CREATE INDEX idx_files_upload_time ON files(upload_time);