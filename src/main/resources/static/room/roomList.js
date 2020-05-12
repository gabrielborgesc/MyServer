var success;

function deleteRoomFromList(roomName){
    const removeRoomCommand = {
            roomName: roomName
    }

    console.log(removeRoomCommand);

    fetch("/removeRoom",
        {
            method: 'POST',
            body: JSON.stringify(removeRoomCommand),
            headers: {
                'Content-Type': 'application/json',
                'access-control-allow-origin': '*'
            }
        })
        .then(response => location.reload());
}


function appendRoom(roomObj)
{
	// Cria o elemento para uma nova linha da tabela
	var row = document.createElement("tr");					

	// Cria a coluna de sala
	var roomColumn = document.createElement("td");
	var room = document.createTextNode(roomObj.roomName);
	roomColumn.appendChild(room);
	row.appendChild(roomColumn);
	
	// Cria a coluna do botão
	var buttonColumn = document.createElement("td");
	var button = document.createElement("button");
//	button.setAttribute('onclick', 'document.location = "chat.html";')
	button.setAttribute('onclick', 'deleteRoomFromList("' + roomObj.roomName + '");')

	var buttonText = document.createTextNode("Deletar");
	buttonColumn.appendChild(button);
	button.appendChild(buttonText);
	row.appendChild(buttonColumn);


	// Adiciona a linha criada na tabela
	document.getElementById("listTable").appendChild(row);
}

function retrieveList()
{
	fetch("/getRoomList",
	        {
	            method: 'GET',
	            headers: {
	                'access-control-allow-origin': '*'
	            }
	        })
	        .then(response => response.json())
	        .then(data => roomList = data)
	        .then(loadList);
}

function loadList()
{
	roomList.forEach(appendRoom);
}

$("document").ready(retrieveList);