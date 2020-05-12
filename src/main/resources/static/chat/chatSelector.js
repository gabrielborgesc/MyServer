var success;

function appendChat(chat)
{
	// Cria o elemento para uma nova linha da tabela
	var row = document.createElement("tr");					

	// Cria a coluna de nome
	var nameColumn = document.createElement("td");
	var name = document.createTextNode(chat.name);
	nameColumn.appendChild(name);
	row.appendChild(nameColumn);

	// Cria a coluna do botão
	var buttonColumn = document.createElement("td");
	var button = document.createElement("button");
//	button.setAttribute('onclick', 'document.location = "chat.html";')
	button.setAttribute('onclick', 'selectChat(' + chat.id + ');')

	var buttonText = document.createTextNode("Entrar");
	buttonColumn.appendChild(button);
	button.appendChild(buttonText);
	row.appendChild(buttonColumn);

	// Adiciona a linha criada na tabela
	document.getElementById("chatTable").appendChild(row);
}

function selectChat(chatId)
{
	fetch("/selectChat",
	        {
	            method: 'POST',
	            body: JSON.stringify({'chatId': chatId}),
	            headers: {
	                'Content-Type': 'application/json',
	                'access-control-allow-origin': '*'
	            }
	        })
	        .then(response => response.text())
	        .then(data => success = data)
	        .then(callback);
}

function callback()
{
	if(success === "true")
		document.location = "chat.html";
	else
		console.log("voce falhou");
}

function retrieveChat()
{
	fetch("/chats",
	        {
	            method: 'GET',
	            headers: {
	                'Content-Type': 'application/json',
	                'access-control-allow-origin': '*'
	            }
	        })
	        .then(response => response.json())
	        .then(data => chatList = data)
	        .then(loadChat);
}

function loadChat()
{
	chatList.forEach(appendChat);
}

$("document").ready(retrieveChat);