import React, { useState, useEffect } from 'react';
import axios from 'axios';

const Weather = () => {
    const [weather, setWeather] = useState(null);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchWeather = async () => {
            try {
                const response = await axios.get('https://api.open-meteo.com/v1/forecast', {
                    params: {
                        latitude: 49.689,
                        longitude: 21.764, 
                        current_weather: true,
                        timezone: 'Europe/Warsaw'
                    },
                });
                setWeather(response.data.current_weather);
                setError(null);
            } catch (error) {
                console.error('Error fetching weather data:', error);
                setError('Nie udało się załadować danych pogodowych.');
            }
        };

        fetchWeather();
    }, []);

    return (
        <div>
            <h1>Aktualna pogoda</h1>
            {error ? (
                <p>{error}</p>
            ) : weather ? (
                <div>
                    <p>Temperatura: {weather.temperature}°C</p>
                    <p>Prędkość wiatru: {weather.windspeed} km/h</p>
                    <p>Kierunek wiatru: {weather.winddirection}°</p>
                </div>
            ) : (
                <p>Ładowanie danych pogodowych...</p>
            )}
        </div>
    );
};

export default Weather;
