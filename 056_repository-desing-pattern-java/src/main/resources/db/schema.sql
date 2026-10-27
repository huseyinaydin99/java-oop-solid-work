-- Oracle Database 12c ve üzeri (Oracle Database Free dâhil)
-- Uygulamayı çalıştırmadan önce bu betiği uygulama kullanıcısıyla (DB_USER) bir kez çalıştırın.

CREATE TABLE customers (
    id          NUMBER(19)          GENERATED ALWAYS AS IDENTITY,
    full_name   VARCHAR2(150 CHAR)  NOT NULL,
    email       VARCHAR2(254 CHAR)  NOT NULL,
    status      VARCHAR2(10 CHAR)   DEFAULT 'ACTIVE' NOT NULL,
    created_at  TIMESTAMP           DEFAULT SYSTIMESTAMP NOT NULL,
    CONSTRAINT pk_customers         PRIMARY KEY (id),
    CONSTRAINT uq_customers_email   UNIQUE (email),
    CONSTRAINT ck_customers_status  CHECK (status IN ('ACTIVE', 'PASSIVE'))
);

CREATE INDEX ix_customers_status ON customers (status, id);
