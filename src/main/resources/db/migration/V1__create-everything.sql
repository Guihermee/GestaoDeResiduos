CREATE SEQUENCE SEQ_CAMINHAO INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_ATERRO INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_COLETA INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_FUNCIONARIO INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_ROTA INCREMENT BY 1 START WITH 1;

CREATE TABLE t_aterro (
                          id_aterro      NUMBER NOT NULL,
                          qtd_atual      NUMBER NOT NULL,
                          qtd_aterro     NUMBER NOT NULL,
                          nm_localizacao VARCHAR2(100) NOT NULL,
                          st_capacidade  NUMBER(1) NOT NULL
);

ALTER TABLE t_aterro ADD CONSTRAINT t_aterro_pk PRIMARY KEY ( id_aterro );

CREATE TABLE t_caminhao (
                            id_caminhao    NUMBER NOT NULL,
                            qtd_atual      NUMBER NOT NULL,
                            vl_capacidade  NUMBER NOT NULL,
                            nm_localizacao VARCHAR2(100) NOT NULL
);

ALTER TABLE t_caminhao ADD CONSTRAINT t_caminhao_pk PRIMARY KEY ( id_caminhao );

CREATE TABLE t_coleta (
                          id_coleta              NUMBER NOT NULL,
                          t_caminhao_id_caminhao NUMBER NOT NULL,
                          st_coleta              NUMBER(1) NOT NULL,
                          nm_localizacao         VARCHAR2(100) NOT NULL
);

ALTER TABLE t_coleta ADD CONSTRAINT t_coleta_pk PRIMARY KEY ( id_coleta );

CREATE TABLE t_funcionario (
                               id_funcionario         NUMBER NOT NULL,
                               t_caminhao_id_caminhao NUMBER NOT NULL,
                               nm_funcionario         VARCHAR2(30) NOT NULL,
                               nm_funcao              VARCHAR2(30) NOT NULL,
                               nm_dept                VARCHAR2(30) NOT NULL
);

ALTER TABLE t_funcionario ADD CONSTRAINT t_funcionario_pk PRIMARY KEY ( id_funcionario );

CREATE TABLE t_rota (
                        id_rota                NUMBER NOT NULL,
                        t_caminhao_id_caminhao NUMBER NOT NULL,
                        t_aterro_id_aterro     NUMBER NOT NULL
);

ALTER TABLE t_rota ADD CONSTRAINT t_rota_pk PRIMARY KEY ( id_rota );

ALTER TABLE t_coleta
    ADD CONSTRAINT t_coleta_t_caminhao_fk FOREIGN KEY ( t_caminhao_id_caminhao )
        REFERENCES t_caminhao ( id_caminhao );

ALTER TABLE t_funcionario
    ADD CONSTRAINT t_funcionario_t_caminhao_fk FOREIGN KEY ( t_caminhao_id_caminhao )
        REFERENCES t_caminhao ( id_caminhao );

ALTER TABLE t_rota
    ADD CONSTRAINT t_rota_t_aterro_fk FOREIGN KEY ( t_aterro_id_aterro )
        REFERENCES t_aterro ( id_aterro );

ALTER TABLE t_rota
    ADD CONSTRAINT t_rota_t_caminhao_fk FOREIGN KEY ( t_caminhao_id_caminhao )
        REFERENCES t_caminhao ( id_caminhao );