DROP TABLE IF EXISTS livros;
DROP TABLE IF EXISTS emprestimos;
DROP TABLE IF EXISTS usuarios;

CREATE TABLE usuarios (
    id long primary key AUTO_INCREMENT,
    nome VARCHAR(250) NOT NULL,
    idade integer NOT NULL,
    telefone VARCHAR(250) NOT NULL
);

CREATE TABLE emprestimos (
    id long primary key AUTO_INCREMENT,
    usuario_id long not null references usuarios(id),
    dias integer not null,
    data_entrega datetime,
    data_devolucao datetime
);

CREATE TABLE livros (
    id long primary key AUTO_INCREMENT,
    titulo VARCHAR(250) NOT NULL,
    resumo TEXT NOT NULL,
    isbn VARCHAR(45) NOT NULL,
    autor VARCHAR(255),
    ano integer,
    emprestimo_id long references emprestimos(id)
);