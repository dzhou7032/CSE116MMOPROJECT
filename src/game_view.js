var socket = io.connect({transports: ['websocket']});

socket.on('connect', function (event) { // connected to server
});

socket.on('message', function (event) {
// received a message from the server console.log(event);
    socket.emit("ack", "thanks for the message!");
});
