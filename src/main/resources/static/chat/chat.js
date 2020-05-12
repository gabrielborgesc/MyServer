var chatInfo;

var messages;


// Função que adiciona uma mensagem na conversa
function addMessage(message)
{
	// Verificação se a mensagem foi enviada pelo próprio usuário ou por outro
	if(message.userId === chatInfo.userId)
		var messageContainer = CreateMessageContainer("sent", message.content);
	else
		var messageContainer = CreateMessageContainer("received", message.username + ': ' + message.content);


	document.getElementById("container").appendChild(messageContainer);
	updateScroll();
}

// Função que gera a mensagem a ser adicionada dependendo de quem enviou
function CreateMessageContainer(type, content)
{
	var messageContainer = document.createElement("div");
	messageContainer.classList.add("message-container");
	messageContainer.classList.add("message-" + type + "-container");

	var message = document.createElement("div");
	message.classList.add("message");
	message.classList.add("message-" + type);

	var messageContent = document.createTextNode(content);

	message.appendChild(messageContent);
	messageContainer.appendChild(message);

	return messageContainer;
}


// Função que carrega as mensagens iniciais
function loadMessages()
{
	messages.forEach(addMessage);
}

// Função que carrega a mensagem enviada pelo usuário na conversa
//function sendMessage()
//{
//	var messageSent = {};
//
//	messageSent.sender = username;
//	messageSent.content = $('input[name=message]').val();
//
//	addMessage(messageSent);
//	$('input[name=message]').val('');
//}







var stompClient = null;

function connect() {
    var socket = new SockJS('/mywebsockets');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/' + chatInfo.roomId, function (message) {
            addMessage(JSON.parse(message.body));
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    console.log("Disconnected");
}

function sendMessage() {
	var message = {
			content: $('input[name=message]').val(),
			username: chatInfo.username,
			userId: chatInfo.userId,
			roomId: chatInfo.roomId
	};
	stompClient.send("/app/message", {}, JSON.stringify(message));
	$('input[name=message]').val('');
}

function processMessage(message) {
	console.log(message.content);
}

function getChatInfo()
{
    fetch("/chatInfo",
            {
                method: 'GET',
                headers: {
                    'access-control-allow-origin': '*'
                }
            })
            .then(response => response.json())
            .then(data => chatInfo = data)
            .then(getMessages)
            .then(connect)
            .then(prepareHeader);
}

function prepareHeader()
{
	$('#title').html(chatInfo.roomName);
}

function getMessages()
{
	fetch("/messages",
            {
                method: 'GET',
                headers: {
                    'access-control-allow-origin': '*'
                }
            })
            .then(response => response.json())
            .then(data => messages = data)
            .then(loadMessages);
}

function updateScroll()
{
	var objDiv = document.getElementById("container");
	objDiv.scrollTop = objDiv.scrollHeight;
}

$("document").ready(getChatInfo);




//window.setInterval(function(){
//	addMessage({sender:'Gabilu', content:'oioioi'});
//}, 5000);