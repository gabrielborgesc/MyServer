var success;

function deleteUserFromList(username){
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
		.then(response => location.reload());
}

function appendUser(user)
{
	// Cria o elemento para uma nova linha da tabela
	var row = document.createElement("tr");					

	// Cria a coluna de nome
	var nameColumn = document.createElement("td");
	var name = document.createTextNode(user.username);
	nameColumn.appendChild(name);
	row.appendChild(nameColumn);

	// Cria a coluna de email
	var emailColumn = document.createElement("td");
	var email = document.createTextNode(user.email);
	emailColumn.appendChild(email);
	row.appendChild(emailColumn);

	// Cria a coluna do tipo
	var typeColumn = document.createElement("td");
	var type = document.createTextNode(user.type);
	typeColumn.appendChild(type);
	row.appendChild(typeColumn);

	// Cria a coluna do botão
	var buttonColumn = document.createElement("td");
	var button = document.createElement("button");
//	button.setAttribute('onclick', 'document.location = "chat.html";')
	button.setAttribute('onclick', 'deleteUserFromList("' + user.username + '");')

	var buttonText = document.createTextNode("Deletar");
	buttonColumn.appendChild(button);
	button.appendChild(buttonText);
	row.appendChild(buttonColumn);

	// Adiciona a linha criada na tabela
	document.getElementById("listTable").appendChild(row);
}

function retrieveList()
{
	fetch("/getUserList",
	        {
	            method: 'GET',
	            headers: {
	                'access-control-allow-origin': '*'
	            }
	        })
	        .then(response => response.json())
	        .then(data => userList = data)
	        .then(loadList);
}

function loadList()
{
	userList.forEach(appendUser);
}

$("document").ready(retrieveList);