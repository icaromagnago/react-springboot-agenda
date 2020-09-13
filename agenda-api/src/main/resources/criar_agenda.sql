CREATE TABLE agenda (
	id IDENTITY PRIMARY KEY,
	data_agenda DATE NOT NULL,
	data_inicio DATE NOT NULL,
	tipo_agenda VARCHAR(15) NOT NULL,
	descricao VARCHAR(255),
	status VARCHAR(30)
);