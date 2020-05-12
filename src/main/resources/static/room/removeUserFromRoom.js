function removeUserFromRoom() {
    const username = document.getElementById("nome_cad").value;
    const roomName = document.getElementById("nome_sala").value;

    const removeUserFromRoomCommand = {
        username: username,
        roomName: roomName
    }

    console.log(removeUserFromRoomCommand);

    fetch("/removeUserFromRoom",
        {
            method: 'POST',
            body: JSON.stringify(removeUserFromRoomCommand),
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
        document.location.href = "userRemovedFromRoom.html";
    else
        window.alert("Não foi possível realizar essa operação");
}