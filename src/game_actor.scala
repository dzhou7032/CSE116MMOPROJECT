import akka.actor.{Actor, ActorRef, PoisonPill, Props}


class game_actor extends Actor {

  var players:Map[String, ActorRef] =Map()

  var game: Game = new Game()


  override def receive: Receive = {
    case message: AddPlayer => game.addPlayer(message.username)
    case message: RemovePlayer => game.removePlayer(message.username)
    case message: MovePlayer => game.players(message.username). move(Array(message.x.toInt, message.y.toInt), game.world)
    case message: fire => game.players(message.username).fire(game.world)
    case message: reload => game.players(message.username).reload()

    case UpdateGame =>game.update()

    case SendGameState => sender() ! GameState(game.gameState())

    case message: harimoto => println(message.message)

    case malong => println("the dragon breathes fire")

  }




}

