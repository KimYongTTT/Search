DROP TABLE IF EXISTS search_word;

CREATE TABLE search_word COMMENT '검색어' (
    id BIGINT AUTO_INCREMENT,
    keyword VARCHAR(255)    NOT NULL,
    search_count BIGINT NOT NULL DEFAULT 1,
    PRIMARY KEY (id),
    UNIQUE INDEX ux_01_keyword (keyword)
);

DROP TABLE IF EXISTS search_history;

CREATE TABLE search_history COMMENT '검색 기록' (
    id BIGINT AUTO_INCREMENT,
    search_word_id BIGINT NOT NULL,
    created_datetime DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(id),
    INDEX ix_01_createdDatetime (created_datetime)
);

DROP TABLE IF EXISTS search_count_batch;

CREATE TABLE search_count_batch COMMENT '검색 횟수 배치' (
    id BIGINT AUTO_INCREMENT,
    started_datetime DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(id)
)

