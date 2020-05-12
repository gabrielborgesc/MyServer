function updateUserEmail() {
    const username = document.getElementById("nome_cad").value;
    const email = document.getElementById("email_cad").value;

    const updateUserEmailCommand = {
        username: username,
        email: email
    }
    

    console.log(updateUserEmailCommand);

    fetch("/updateUserEmail",
        {
            method: 'POST',
            body: JSON.stringify(updateUserEmailCommand),
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