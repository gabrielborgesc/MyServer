function signup() {
    const username = document.getElementById("nome_cad").value;
    const email = document.getElementById("email_cad").value;
    const password = document.getElementById("senha_cad").value;
    const retype = document.getElementById("confsenha_cad").value;
    var type = document.getElementById('type_cad_r').value;

    if (document.getElementById('type_cad_a').checked) {
    type = document.getElementById('type_cad_a').value;
    }

    
    const signupCommand = {
        username: username,
        email: email,
        password: password,
        retype: retype,
        type: type
    }
    

    console.log(signupCommand);

    fetch("/createUser",
        {
            method: 'POST',
            body: JSON.stringify(signupCommand),
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
        document.location.href = "userSignedup.html";
    else
        window.alert("Não foi possível realizar essa operação");
}