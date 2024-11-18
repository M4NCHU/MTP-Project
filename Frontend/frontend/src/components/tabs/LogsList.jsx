import React, { useState, useEffect } from 'react';
import axios from 'axios';

const LogList = () => {
    const [logs, setLogs] = useState([]);
    const [sortField, setSortField] = useState('timestamp');
    const [sortOrder, setSortOrder] = useState('asc');
    const [isLoading, setIsLoading] = useState(false);
    const [error, setError] = useState(null);

    const username = 'admin';
    const password = 'adminpassword';
    const authHeader = `Basic ${btoa(`${username}:${password}`)}`;

    

    useEffect(() => {
        const fetchLogs = async () => {
            setIsLoading(true);
            setError(null);
            try {
                
                const response = await axios.get('https://localhost:8081/api/logs', {
                    headers: {
                        Authorization: authHeader,
                    },
                });

                setLogs(response.data.content || []);
            } catch (err) {
                setError(err.response?.data?.message || 'Wystąpił błąd podczas pobierania logów');
                console.error('Error fetching logs:', err);
            } finally {
                setIsLoading(false);
            }
        };

        fetchLogs();
    }, [authHeader]);

    const handleSort = (field) => {
        const order = sortField === field && sortOrder === 'asc' ? 'desc' : 'asc';
        setSortField(field);
        setSortOrder(order);

        const sortedLogs = [...logs].sort((a, b) => {
            if (order === 'asc') {
                return a[field] > b[field] ? 1 : -1;
            } else {
                return a[field] < b[field] ? 1 : -1;
            }
        });

        setLogs(sortedLogs);
    };

    return (
        <div>
            <div className="d-flex justify-content-between">
                <h1 className="mb-3">Lista logów</h1>
                <div>
                    <button
                        className="btn btn-primary me-2"
                        onClick={() => handleSort('timestamp')}
                    >
                        Sortuj według daty {sortField === 'timestamp' && (sortOrder === 'asc' ? '↑' : '↓')}
                    </button>
                    <button
                        className="btn btn-secondary"
                        onClick={() => handleSort('level')}
                    >
                        Sortuj według poziomu {sortField === 'level' && (sortOrder === 'asc' ? '↑' : '↓')}
                    </button>
                </div>
            </div>

            {isLoading && <p>Ładowanie logów...</p>}
            {error && <p className="text-danger">{error}</p>}

            {!isLoading && !error && (
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
                        {logs.length > 0 ? (
                            logs.map((log, index) => (
                                <tr key={log.id}>
                                    <th scope="row">{index + 1}</th>
                                    <td>{new Date(log.timestamp).toLocaleString()}</td>
                                    <td>{log.level}</td>
                                    <td>{log.logger}</td>
                                    <td>{log.message}</td>
                                    <td>{log.exception || 'Brak'}</td>
                                </tr>
                            ))
                        ) : (
                            <tr>
                                <td colSpan="6">Brak danych</td>
                            </tr>
                        )}
                    </tbody>
                </table>
            )}
        </div>
    );
};

export default LogList;
