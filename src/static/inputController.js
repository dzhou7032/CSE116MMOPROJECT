var keyStates = {
    "w": false,
    "a": false,
    "s": false,
    "d": false
};

var fire = false


function setState(key, toSet){
    if(keyStates[key] !== toSet){
        keyStates[key] = toSet;
        socket.emit("keyStates", JSON.stringify(keyStates));
    }
}

function handleEvent(event, toSet){
    if(event.key === "w" || event.key === "ArrowUp"){
        setState("w", toSet);
    }else if(event.key === "a" || event.key === "ArrowLeft"){
        setState("a", toSet);
    }else if(event.key === "s" || event.key === "ArrowDown"){
        setState("s", toSet);
    }else if(event.key === "d" || event.key === "ArrowRight"){
        setState("d", toSet);
    }else if(event.key === "f"){
        fire = toSet
        socket.emit("tagKey", JSON.stringify(fire));
    }else if(event.key === "r"){
        setState("r", toSet);
    }
}

document.addEventListener("keydown", function (event) {
    handleEvent(event, true);
});

document.addEventListener("keyup", function (event) {
    handleEvent(event, false);
});
