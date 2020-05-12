const BASE_URL = 'http://localhost:8081/';

export const sendCommand =
    (path, payload) =>
        fetch(BASE_URL + path,
            {
                method: 'POST',
                body: JSON.stringify(payload),
                headers: {
                    'Content-Type': 'application/json',
                    'access-control-allow-origin': '*'
                }
            });
