import React, { useState, useEffect } from 'react';
import axios from 'axios';
import logo from "../../assets/filmy.png"


const FilmList = () => {
    const [films, setFilms] = useState([]);
    const [showForm, setShowForm] = useState(false);
    const [newFilm, setNewFilm] = useState({ tytul: '', opis: '', rokWydania: '', dlugosc: '' });
    const [isEditing, setIsEditing] = useState(false);
    const [ratings, setRatings] = useState({});
    const [selectedFilm, setSelectedFilm] = useState(null); // State for selected film details

    useEffect(() => {
        const fetchFilms = async () => {
            try {
                const response = await axios.get('http://localhost:8081/api/filmy');
                setFilms(response.data);
                const initialRatings = response.data.reduce((acc, film) => ({
                    ...acc,
                    [film.id]: { ocena: 5, komentarz: '' }
                }), {});
                setRatings(initialRatings);
            } catch (error) {
                console.error('There was an error fetching the films:', error);
            }
        };

        fetchFilms();
    }, []);

    const handleRatingChange = (filmId, field, value) => {
        setRatings(prevRatings => ({
            ...prevRatings,
            [filmId]: {
                ...prevRatings[filmId],
                [field]: value
            }
        }));
    };

    const submitRating = async (filmId) => {
        const rating = ratings[filmId];
        if (rating) {
            try {
                await axios.post(`http://localhost:8081/api/oceny`, {
                    filmId,
                    ocena: rating.ocena,
                    komentarz: rating.komentarz
                });
                alert('Ocena dodana!');
            } catch (error) {
                console.error('Failed to submit rating:', error);
            }
        }
    };

    const handleAddMovie = () => {
        setNewFilm({ tytul: '', opis: '', rokWydania: '', dlugosc: '' });
        setShowForm(true);
        setIsEditing(false);
    };

    const handleEdit = (film) => {
        setNewFilm(film);
        setShowForm(true);
        setIsEditing(true);
    };

    const handleFormChange = (e) => {
        setNewFilm({ ...newFilm, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            let response;
            if (isEditing) {
                response = await axios.put(`http://localhost:8081/api/filmy/edytujFilm/${newFilm.id}`, newFilm);
                const updatedFilms = films.map(f => f.id === newFilm.id ? response.data : f);
                setFilms(updatedFilms);
            } else {
                response = await axios.post('http://localhost:8081/api/filmy/zapiszFilm', newFilm);
                setFilms([...films, response.data]);
            }
            setShowForm(false);
            setIsEditing(false);
            setNewFilm({ tytul: '', opis: '', rokWydania: '', dlugosc: '' });
        } catch (error) {
            console.error('Failed to save film:', error);
        }
    };

    const handleDelete = async (id) => {
        try {
            await axios.delete(`http://localhost:8081/api/filmy/usunFilm/${id}`);
            setFilms(films.filter(film => film.id !== id));
        } catch (error) {
            console.error('There was an error deleting the film:', error);
        }
    };

    const handleShowDetails = async (id) => {
        try {
            const response = await axios.get(`http://localhost:8081/api/filmy/${id}`);
            setSelectedFilm(response.data);
        } catch (error) {
            console.error('There was an error fetching the film details:', error);
        }
    };

    return (
        <div>
            <div className='d-flex justify-content-between align-items-center'>
                <div className='d-flex flex-row align-items-center'>
                    <img src={logo} className='image'  alt="" />
                    <h1 className="mb-3">Lista Filmów</h1>
                </div>
                <div>
                    <button className='btn btn-primary d-flex' onClick={handleAddMovie}>
                        Dodaj Film
                    </button>
                </div>
            </div>
            {showForm && (
                <form onSubmit={handleSubmit} className="mb-3">
                    <div className="form-group">
                        <label htmlFor="tytul">Tytuł</label>
                        <input type="text" className="form-control" id="tytul" name="tytul" value={newFilm.tytul} onChange={handleFormChange} required />
                    </div>
                    <div className="form-group">
                        <label htmlFor="opis">Opis</label>
                        <input type="text" className="form-control" id="opis" name="opis" value={newFilm.opis} onChange={handleFormChange} required />
                    </div>
                    <div className="form-group">
                        <label htmlFor="rokWydania">Rok Wydania</label>
                        <input type="number" className="form-control" id="rokWydania" name="rokWydania" value={newFilm.rokWydania} onChange={handleFormChange} required />
                    </div>
                    <div className="form-group">
                        <label htmlFor="dlugosc">Długość (min)</label>
                        <input type="number" className="form-control" id="dlugosc" name="dlugosc" value={newFilm.dlugosc} onChange={handleFormChange} required />
                    </div>
                    <button type="submit" className="btn btn-success">{isEditing ? 'Zaktualizuj Film' : 'Zapisz Film'}</button>
                </form>
            )}
            {selectedFilm && (
                <div className="card mb-3">
                    <div className="card-header">
                        <h2>{selectedFilm.tytul}</h2>
                    </div>
                    <div className="card-body">
                        <p>{selectedFilm.opis}</p>
                        <p><strong>Rok Wydania:</strong> {selectedFilm.rokWydania}</p>
                        <p><strong>Długość:</strong> {selectedFilm.dlugosc} min</p>
                    </div>
                </div>
            )}
            <table className="table">
                <thead className="thead-dark">
                    <tr>
                        <th>#</th>
                        <th>Tytuł</th>
                        <th>Opis</th>
                        <th>Rok Wydania</th>
                        <th>Długość</th>
                        <th>Akcje</th>
                        <th>Ocena</th>
                    </tr>
                </thead>
                <tbody>
                    {films.map((film, index) => (
                        <tr key={film.id}>
                            <th scope="row">{index + 1}</th>
                            <td>{film.tytul}</td>
                            <td>{film.opis}</td>
                            <td>{film.rokWydania}</td>
                            <td>{film.dlugosc} min</td>
                            <td>
                                <button type='button' className='btn btn-danger' onClick={() => handleDelete(film.id)}>
                                    Usuń
                                </button>
                                <button type='button' className='btn btn-primary' onClick={() => handleEdit(film)}>
                                    Edytuj
                                </button>
                                <button type='button' className='btn btn-info' onClick={() => handleShowDetails(film.id)}>
                                    Szczegóły
                                </button>
                            </td>
                            <td>
                                {ratings[film.id] && (
                                    <>
                                        <input type="number" min="1" max="10" value={ratings[film.id].ocena}
                                            onChange={(e) => handleRatingChange(film.id, 'ocena', parseInt(e.target.value))} />
                                        <input type="text" value={ratings[film.id].komentarz}
                                            onChange={(e) => handleRatingChange(film.id, 'komentarz', e.target.value)} />
                                        <button onClick={() => submitRating(film.id)}>Submit Rating</button>
                                    </>
                                )}
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}

export default FilmList;
