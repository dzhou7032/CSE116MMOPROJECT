case class harimoto(message: String)

case object malong

case object send

case object UpdateGame

case object SendGameState
case class GameState(gameState: String)

case class AddPlayer(username: String)
case class RemovePlayer(username: String)
case class MovePlayer(username: String, x: Double, y:Double)

