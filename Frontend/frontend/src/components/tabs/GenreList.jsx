import React, { useState, useEffect } from 'react';
import axios from 'axios';
import logo from "../../assets/gatunki.png"


const GenreList = () => {
    const [genres, setGenres] = useState([]);
    const [showForm, setShowForm] = useState(false);
    const [newGenre, setNewGenre] = useState({ nazwa: '', opis: '' });
    const [isEditing, setIsEditing] = useState(false);

    useEffect(() => {
        const fetchGenres = async () => {
            try {
                const response = await axios.get('http://localhost:8081/api/gatunki');
                setGenres(response.data);
            } catch (error) {
                console.error('There was an error fetching the genres:', error);
            }
        };

        fetchGenres();
    }, []);

    const handleAddGenre = () => {
        setNewGenre({ nazwa: '', opis: '' });
        setShowForm(true);
        setIsEditing(false);
    };

    const handleEdit = (genre) => {
        setNewGenre(genre);
        setShowForm(true);
        setIsEditing(true);
    };

    const handleFormChange = (e) => {
        setNewGenre({ ...newGenre, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            let response;
            if (isEditing) {
                response = await axios.put(`http://localhost:8081/api/gatunki/${newGenre.id}`, newGenre);
                const updatedGenres = genres.map(g => g.id === newGenre.id ? response.data : g);
                setGenres(updatedGenres);
            } else {
                response = await axios.post('http://localhost:8081/api/gatunki', newGenre);
                setGenres([...genres, response.data]);
            }
            setShowForm(false);
            setIsEditing(false);
            setNewGenre({ nazwa: '', opis: '' });
        } catch (error) {
            console.error('Failed to save genre:', error);
        }
    };

    const handleDelete = async (id) => {
        try {
            await axios.delete(`http://localhost:8081/api/gatunki/${id}`);
            setGenres(genres.filter(genre => genre.id !== id));
        } catch (error) {
            console.error('There was an error deleting the genre:', error);
        }
    };

    return (
        <div>
            <div className='d-flex justify-content-between'>
            <div className='d-flex flex-row align-items-center'>
                    <img src={logo} className='image'  alt="" />
                    <h1 className="mb-3">Lista gatunków</h1>
                </div>
                <div>
                    <button className='btn btn-primary' onClick={handleAddGenre}>
                        Dodaj Gatunek
                    </button>
                </div>
            </div>
            {showForm && (
                <form onSubmit={handleSubmit} className="mb-3">
                    <div className="form-group">
                        <label htmlFor="nazwa">Nazwa</label>
                        <input type="text" className="form-control" id="nazwa" name="nazwa" value={newGenre.nazwa} onChange={handleFormChange} required />
                    </div>
                    <div className="form-group">
                        <label htmlFor="opis">Opis</label>
                        <input type="text" className="form-control" id="opis" name="opis" value={newGenre.opis} onChange={handleFormChange} required />
                    </div>
                    <button type="submit" className="btn btn-success">{isEditing ? 'Zaktualizuj Gatunek' : 'Zapisz Gatunek'}</button>
                </form>
            )}
            <table className="table">
                <thead className="thead-dark">
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Nazwa</th>
                        <th scope="col">Opis</th>
                        <th scope="col">Akcje</th>
                    </tr>
                </thead>
                <tbody>
                    {genres.map((genre, index) => (
                        <tr key={genre.id}>
                            <th scope="row">{index + 1}</th>
                            <td>{genre.nazwa}</td>
                            <td>{genre.opis}</td>
                            <td>
                                <button type='button' className='btn btn-danger' onClick={() => handleDelete(genre.id)}>
                                    Usuń
                                </button>
                                <button type='button' className='btn btn-primary' onClick={() => handleEdit(genre)}>
                                    Edytuj
                                </button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}

export default GenreList;
