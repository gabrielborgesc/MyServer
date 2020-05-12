function createRoom() {
    const roomName = document.getElementById("nome_cad").value;

    const createRoomCommand = {
        roomName: roomName
    }

    console.log(createRoomCommand);

    fetch("/createRoom",
        {
            method: 'POST',
            body: JSON.stringify(createRoomCommand),
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
    console.log("callback", success);
    if (success === "true")
        document.location.href = "roomCreated.html";
    else
        window.alert("Não foi possível realizar essa operação");
}