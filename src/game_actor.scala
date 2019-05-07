import akka.actor.{Actor, ActorRef, PoisonPill, Props}


class game_actor extends Actor {

  override def receive: Receive = {

    case message: harimoto => println(message.message)

    case malong => println("the dragon breathes fire")

  }




}

