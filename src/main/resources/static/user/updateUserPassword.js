function updateUserPassword() {
    const username = document.getElementById("nome_cad").value;
    const password = document.getElementById("senha_cad").value;

    const updateUserPasswordCommand = {
        username: username,
        password: password
    }
    

    console.log(updateUserPasswordCommand);

    fetch("/updateUserPassword",
        {
            method: 'POST',
            body: JSON.stringify(updateUserPasswordCommand),
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
        document.location.href = "userUpdated.html";
    else
        window.alert("Não foi possível realizar essa operação");
}