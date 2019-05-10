
import json
import socket
from threading import Thread
from random import randint

from flask import Flask, send_from_directory, request, render_template
from flask_socketio import SocketIO

import eventlet

eventlet.monkey_patch()

app = Flask(__name__)
socket_server = SocketIO(app)

scala_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
scala_socket.connect(('localhost', 8000))

delimiter = "~"


def listen_to_scala(the_socket):
    delimiter = "~"
    buffer = ""
    while True:
        buffer += the_socket.recv(1024).decode()
        while delimiter in buffer:
            message = buffer[:buffer.find(delimiter)]
            buffer = buffer[buffer.find(delimiter) + 1:]
            get_from_scala(message)


def send_to_scala(data):
    scala_socket.sendall((json.dumps(data) + delimiter).encode())


def get_from_scala(data):
    socket_server.emit('gameState', data, broadcast=True)



Thread(target=listen_to_scala, args=(scala_socket,)).start()


@app.route('/')
def index():
    return send_from_directory('static', 'index.html')


@app.route('/<path:filename>')
def static_files(filename):
    return send_from_directory('static', filename)


@socket_server.on('connect')
def got_message():
    print(request.sid + " connected")
    message = {"username": request.sid, "action": "connected"}
    send_to_scala(message)


@socket_server.on('disconnect')
def disconnect():
    print(request.sid + " disconnected")
    message = {"username": request.sid, "action": "disconnected"}
    send_to_scala(message)

@socket_server.on('keyStates')
def key_state(jsonKeyStates):
    key_states = json.loads(jsonKeyStates)
    x = 0.0
    if key_states["a"] and not key_states["d"]:
        x = -1.0
    elif not key_states["a"] and key_states["d"]:
        x = 1.0
    y = 0.0
    if key_states["w"] and not key_states["s"]:
        y = -1.0
    elif not key_states["w"] and key_states["s"]:
        y = 1.0
    message = {"username": request.sid, "action": "move", "x": x, "y": y}
    send_to_scala(message)

@socket_server.on('fireKey')
def key_state(jsonKeyStates):
    fkey = json.loads(jsonKeyStates)
    if(fkey == True):
        message = {"username": request.sid, "action": "fire"}
        send_to_scala(message)

@socket_server.on('tagKey')
def key_state(jsonKeyStates):
    fkey = json.loads(jsonKeyStates)
    if(fkey == True):
        print("pressed!")
        message = {"username": request.sid, "action": "tag"}
        send_to_scala(message)

print("Listening on port 8080")
socket_server.run(app, port=8080)
