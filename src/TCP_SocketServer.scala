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

    case send => this.game_actor ! harimoto("shut up once in a while")


  }


  def handleMessageFromWebServer(messageString:String):Unit = {}



}






object TCPSocketServer {

  def main(args: Array[String]): Unit = {

    val system = ActorSystem()

    import system.dispatcher
    import scala.concurrent.duration._

//    val tomokazu_harimoto = system.actorOf(Props(classOf[game_actor]))
    val ma_long = system.actorOf(Props(classOf[game_actor]))

    val server = system.actorOf(Props(classOf[TCP_SocketServer], ma_long))

//    actorSystem.scheduler.schedule(16.milliseconds, 32.milliseconds, game_actor, harimoto)
    system.scheduler.schedule(16.milliseconds, 32.milliseconds, server, send)
  }

}

