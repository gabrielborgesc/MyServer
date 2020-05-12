function removeUser() {
    const username = document.getElementById("nome_cad").value;

    const removeUserCommand = {
        username: username
    }
    

    console.log(removeUserCommand);

    fetch("/removeUser",
        {
            method: 'POST',
            body: JSON.stringify(removeUserCommand),
            headers: {
                'Content-Type': 'application/json',
                'access-control-allow-origin': '*'
            }
        })
        .then(response => response.text())
        .then(data => success = data)
        .then(callback);
}

function callback() {
    if (success === "true")
        document.location.href = "userRemoved.html";
    else
        window.alert("Não foi possível realizar essa operação");
}