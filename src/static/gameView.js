var socket = io.connect({transports: ['websocket']});
socket.on('gameState', parseGameState);

const tileSize = 30;

var canvas = document.getElementById("canvas");
var context = canvas.getContext("2d");
context.globalCompositeOperation = 'source-over';

function parseGameState(event) {
    // console.log(event);
    const gameState = JSON.parse(event);

    drawGameBoard(gameState['gridSize']);

    for (let player of gameState['players']) {
        var img = new Image();
        img.src = "charstand.png"
        context.drawImage(img, player["x"]*32, player['y']*32)
//        placeCircle(player['x'], player['y'], player['id'] === socket.id ? '#ffff00' : '#56bcff', 2.0);
    }

    for (let wall of gameState['walls']) {
        var img = new Image();
        img.src = "walltile.png"
        context.drawImage(img, wall["x"]*32, wall['y']*32)
//        placeSquare(wall['x'], wall['y'], 'grey');
    }

}

function drawGameBoard(gridSize) {

    const gridWidth = gridSize['x'];
    const gridHeight = gridSize['y'];

//    context.clearRect(0, 0, gridWidth, gridHeight);

    canvas.setAttribute("width", gridWidth );
    canvas.setAttribute("height", gridHeight);

    // context.strokeStyle = '#bbbbbb';
    // for (let j = 0; j <= gridWidth; j++) {
    //     context.beginPath();
    //     context.moveTo(j * tileSize, 0);
    //     context.lineTo(j * tileSize, gridHeight * tileSize);
    //     context.stroke();
    // }
    // for (let k = 0; k <= gridHeight; k++) {
    //     context.beginPath();
    //     context.moveTo(0, k * tileSize);
    //     context.lineTo(gridWidth * tileSize, k * tileSize);
    //     context.stroke();
    // }

}

