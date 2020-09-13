import React, { useState, useEffect } from 'react';
import { toast } from 'react-toastify';
import { Table } from 'react-bootstrap';

import api from '../../services/api';
import Container from '../../components/Container';
import { FormContainer, SubmitButton, TableContainer } from './styles';

export default function Home() {
    const [tiposAgenda, setTiposAgenda] = useState([]);
    const [tipoSelecionado, setTipoSelecionado] = useState("");
    const [descricao, setDescricao] = useState("");
    const [dataAgenda, setDataAgenda] = useState("");

    const [agendas, setAgendas] = useState([]);

    async function loadAgendas() {
        const response = await api.get('/agendas');

        setAgendas(response.data.data);
    }

    useEffect(() => {
        async function loadTiposAgenda() {
            const response = await api.get('/agendas/tipos');

            setTiposAgenda(response.data.data);
        }

        Promise.all([loadTiposAgenda(), loadAgendas()]);
        
    }, []);

    async function handleSubmit(e) {
        e.preventDefault();
        
        const response = await api.post('agendas', {
            tipoAgenda: tipoSelecionado,
            descricao,
            dataAgenda
        });

        setDescricao("");
        setTipoSelecionado("");
        setDataAgenda("");

        loadAgendas();

        toast.success("Agenda criada com sucesso!");
    }

    return (
        <Container>
             <h5>Nova Agenda</h5>
            <FormContainer onSubmit={handleSubmit}>
                <select 
                    value={tipoSelecionado} 
                    onChange={e => setTipoSelecionado(e.target.value)}>

                    <option value="">Tipo de Agenda</option>
                    {tiposAgenda.map(tipo => (
                        <option key={tipo.valor} value={tipo.valor}>
                            {tipo.descricao}
                        </option>    
                    ))}
                </select>

                <input 
                    type="date" 
                    pattern="[0-9]{2}/[0-9]{2}/[0-9]{4}" 
                    placeholder="Data da agenda" 
                    value={dataAgenda}
                    onChange={e => setDataAgenda(e.target.value)} />

                <textarea 
                    placeholder="Descrição"
                    value={descricao} 
                    onChange={e => setDescricao(e.target.value)} />

                <SubmitButton>Salvar</SubmitButton>
            </FormContainer>
                    
            <TableContainer>
                <Table striped bordered hover>
                    <thead>
                        <tr>
                            <th>Data de Início</th>
                            <th>Status</th>
                        </tr>
                    </thead>
                    <tbody>
                        {agendas.map(agenda => (
                            <tr key={agenda.id}>
                                <td>{agenda.dataInicio}</td>
                                <td>{agenda.status}</td>
                            </tr>
                        ))}
                    </tbody>
                </Table>
            </TableContainer>
        </Container>
    );
}