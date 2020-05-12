function addUserToRoom() {
    const username = document.getElementById("nome_cad").value;
    const roomName = document.getElementById("nome_sala").value;

    const addUserToRoomCommand = {
        username: username,
        roomName: roomName
    }

    console.log(addUserToRoomCommand);

    fetch("/addUserToRoom",
        {
            method: 'POST',
            body: JSON.stringify(addUserToRoomCommand),
            headers: {
                'Content-Type': 'application/json',
                'access-control-allow-origin': '*'
            }
        })
        .then(response => response.text())
        .then(callback);
}

function callback(success) {
    if (success === "true")
        document.location.href = "userAddedToRoom.html";
    else
        window.alert("Não foi possível realizar essa operação");
}