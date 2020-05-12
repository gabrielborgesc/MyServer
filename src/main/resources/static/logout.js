var success;

function logout()
{
    fetch("/logout",
        {
            method: 'GET',
            headers: {
                'access-control-allow-origin': '*'
            }
        })
        .then(logoutCallback);
}

function logoutCallback()
{
    document.location = '/index.html';
}