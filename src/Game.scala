import DeadmanPlayground.Types.{Player, Tile, World}
import play.api.libs.json.{JsValue, Json}

class Game {

  var players: Map[String, Player] = Map()

  val world: World = new World(players)

  var lastUpdateTime: Long = System.nanoTime()

  var walls:List[Tile] =List()

  def addPlayer(id: String): Unit = {
    val player = new Player(id, startingLocation(), faceSouth(), 100, 0, true)
    players +=(id -> player)
    world.objects = player::world.objects
  }
    def update(): Unit = {
//    val time: Long = System.nanoTime()
//    val dt = (time - this.lastUpdateTime) / 1000000000.0
//    Physics.updateWorld(this.world, dt)
//    checkForPlayerHits()
//    checkForBaseDamage()
//    projectiles = projectiles.filter(po => !po.destroyed)
//    this.lastUpdateTime = time
    }
  def gameState(): String = {
    val gameState: Map[String, JsValue] = Map(
      "gridSize" -> Json.toJson(Map("x" -> 320, "y" -> 320)),
//      "walls" -> Json.toJson(this.walls.map({ w => Json.toJson(Map("x" -> w.coordinate(0), "y" -> w.coordinate(1))) })),
      "players" -> Json.toJson(this.players.map({ case (k, v) => Json.toJson(Map(
        "x" -> Json.toJson(v.coordinate(0)),
        "y" -> Json.toJson(v.coordinate(1)),
        "v_d" -> Json.toJson(v.direction),
        "ammo" -> Json.toJson(v.ammo),
        "health" -> Json.toJson(v.health),
        "id" -> Json.toJson(k))) }))
//      "projectiles" -> Json.toJson(this.projectiles.map({ po => Json.toJson(Map("x" -> po.location.x, "y" -> po.location.y)) }))
    )

    return Json.stringify(Json.toJson(gameState))
  }

  def removePlayer(id: String): Unit ={
    players(id).destroy()
    players -= id
  }

  def placeWall(x: Int, y: Int): Unit = {
    walls = new Tile(List(x,y), false) :: walls
  }

  def faceSouth(): List[Int] ={
    return List(-1,0)
  }

  def startingLocation(): List[Double] ={
    var start: List[Double] = List(1,1)
    return start
  }
  def lastMan(contestants: World): Boolean /*String*/ = {
    //var winner: String = ""
    if (contestants.players.size == 1) {
      //for((key, value) <- contestant){
      //winner = "player " + key.toString() + " win!"
      //}
      true
    }
    else {
      false
    }
  }
  /*
  def moveValidation(contestant: World): Boolean/*Status*/={
    val everyone: Map[Int, Player] = contestant.players
    var validation: Boolean = true
    for((key, value) <- everyone){
      val coordinateMoveX: Array.ofDim[Int]{2,2} = value(1)(0)
      val coordinateMoveY: Int = value(1)(1)
      val coordinateX: Int = value(0)(0)
      val coordinateY: Int = value(0)(1)
      val coordinateUpdatedX: Int = coordinateX + coordinateMoveX
      val coordinateUpdatedY: Int = coordinateY + coordinateMoveY
      if(contestant.map(coordinateUpdatedX)(coordinateUpdatedY) == 0){//updates players new location
        validation = true
      }
      else{//revert the players location back to its original spot
        validation = false
      }
    }
    validation
  }
  */

}




  /*
  def hitScan(map: Array[Array[Int]], direction: Array[Array[Int]]): Boolean ={
    var hit: Boolean = false
    if(direction(1)(0) == 0){
      if(direction(1)(1) > 0){
        var varifier: Boolean = false
        for (down <- range(direction(0)(1), map.length, direction(1)(1))) {
          if (map(down)(direction(0)(0)) != 0) {
            hit = true
            varifier = true
          }
          else {
            hit = false
          }
        }
      }
      else if(direction(1)(1) < 0){
        var varifier: Boolean = false
        for (up <- range(direction(0)(1), -1, direction(1)(1))) {
          if (map(up)(direction(0)(0)) != 0) {
            hit = true
            varifier = true
          }
          else {
            hit = false
          }
        }
      }
    }
    else{
      if(direction(1)(0) > 0){
        var varifier: Boolean = false
        for (right <- range(direction(0)(0), map(0).length, direction(1)(0))) {
          if (map(direction(0)(1))(right) != 0) {
            hit = true
            varifier = true
          }
          else {
            hit = false
          }
        }
      }
      else if(direction(1)(0) < 0) {
        var varifier: Boolean = false
        for (left <- range(direction(0)(0), -1, direction(1)(0))) {
          if (varifier == false){
            if (map(direction(0)(1))(left) != 0) {
              hit = true
              varifier = true
            }
            else {
              hit = false
            }
          }
        }
      }
    }
    hit
  }
}
*/
