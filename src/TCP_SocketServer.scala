import java.net.InetSocketAddress

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import akka.io.{IO, Tcp}
import akka.util.ByteString
import play.api.libs.json.{JsValue, Json}



class TCP_SocketServer(game_actor: ActorRef) extends Actor {

  import Tcp._
  import context.system

  IO(Tcp) ! Bind(self, new InetSocketAddress("localhost", 8000))

  var webServers: Set[ActorRef] = Set()
  var buffer: String = ""
  val delimiter: String = "~"

  override def receive: Receive = {
    case b: Bound => println("Listening on port: " + b.localAddress.getPort)

    case c: Connected =>
      println("Client Connected: " + c.remoteAddress)
      this.webServers = this.webServers + sender()
      sender() ! Register(self)

    case PeerClosed =>
      println("Client Disconnected: " + sender())
      this.webServers = this.webServers - sender()

    case r: Received =>
      buffer += r.data.utf8String
      while (buffer.contains(delimiter)) {
        val curr = buffer.substring(0, buffer.indexOf(delimiter))
        buffer = buffer.substring(buffer.indexOf(delimiter) + 1)
        handleMessageFromWebServer(curr)
      }

    case SendGameState =>
      game_actor ! SendGameState

    case gs: GameState =>
      this.webServers.foreach((client: ActorRef) => client ! Write(ByteString(gs.gameState + delimiter)))

    //    case send => this.game_actor ! harimoto("shut up once in a while")

  }


  def handleMessageFromWebServer(messageString: String): Unit = {
    val message: JsValue = Json.parse(messageString)
    val username = (message \ "username").as[String]
    val messageType = (message \ "action").as[String]

    messageType match {
      case "connected" => game_actor ! AddPlayer(username)
      case "disconnected" => game_actor ! RemovePlayer(username)
      case "move" =>
        val x = (message \ "x").as[Double]
        val y = (message \ "y").as[Double]
        game_actor ! MovePlayer(username, x, y)
      case "fire" =>
        game_actor ! fire(username)
      case "reload" =>
        game_actor ! reload(username)
    }


  }
}






object TCP_SocketServer {

  def main(args: Array[String]): Unit = {

//    val system = ActorSystem()
    val actorSystem = ActorSystem()
//    import system.dispatcher
    import actorSystem.dispatcher
    import scala.concurrent.duration._

//    val tomokazu_harimoto = system.actorOf(Props(classOf[game_actor]))

    val gameActor = actorSystem.actorOf(Props(classOf[game_actor]))
    val server = actorSystem.actorOf(Props(classOf[TCP_SocketServer], gameActor))

    actorSystem.scheduler.schedule(16.milliseconds, 32.milliseconds, gameActor, UpdateGame)
    actorSystem.scheduler.schedule(32.milliseconds, 32.milliseconds, server, SendGameState)

//    val ma_long = system.actorOf(Props(classOf[game_actor]))
//
//    val server = system.actorOf(Props(classOf[TCP_SocketServer], ma_long))

//    actorSystem.scheduler.schedule(16.milliseconds, 32.milliseconds, game_actor, harimoto)
//    system.scheduler.schedule(16.milliseconds, 32.milliseconds, server, send)
  }

}

