import React, { useState, useEffect } from 'react';
import axios from 'axios';

const LogList = () => {
    const [logs, setLogs] = useState([]);

    useEffect(() => {
        const fetchLogs = async () => {
            try {
                const response = await axios.get('http://localhost:8081/api/logs');
                setLogs(response.data);
            } catch (error) {
                console.error('There was an error fetching the logs:', error);
            }
        };

        fetchLogs();
    }, []);

    return (
        <div>
            <div className='d-flex justify-content-between'>
                <div className='d-flex flex-row align-items-center'>

                    <h1 className="mb-3">Lista logów</h1>
                </div>
            </div>
            <table className="table">
                <thead className="thead-dark">
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Data</th>
                        <th scope="col">Poziom</th>
                        <th scope="col">Logger</th>
                        <th scope="col">Wiadomość</th>
                        <th scope="col">Wyjątek</th>
                    </tr>
                </thead>
                <tbody>
                    {logs.map((log, index) => (
                        <tr key={log.id}>
                            <th scope="row">{index + 1}</th>
                            <td>{log.eventDate}</td>
                            <td>{log.level}</td>
                            <td>{log.logger}</td>
                            <td>{log.message}</td>
                            <td>{log.exception || 'Brak'}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}

export default LogList;
