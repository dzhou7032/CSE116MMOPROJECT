package DeadmanPlayground.Types

class Player(var coordinate: List[Int], var direction: List[Int], var health: Int, var score: Int, var alive: Boolean) {
  def move(directions: String, world: World): List[Int] ={
    var map: List[List[Int]] = world.map
    var area: List[Int] = this.coordinate
    //var direction: List[Int] = playerDirection()
    var facing: List[Int] = playerDirection(directions)
    val yCoordinate: Int = area(0) + facing(0)
    val xCoordinate: Int = area(1) + facing(1)
    if(map(yCoordinate)(xCoordinate) == 0){//updates players new location
      area = List(yCoordinate, xCoordinate)
      this.coordinate = area
      coordinate
    }
    else{//revert the players location back to its original spot
      this.coordinate = area
      coordinate
    }
  }

  def playerDirection(Letter: String): List[Int] = {
    var direction: List[Int] = List(1, 0)
    if (Letter == "w") {
      val direction1: List[Int] = List(-1, -0)//
      direction = direction1
    }
    else if (Letter == "s") {
      val direction2: List[Int] = List(1, 0)
      direction = direction2
    }
    else if (Letter == "a") {
      val direction3: List[Int] = List(0, -1)
      direction = direction3
    }
    else if (Letter == "d") {
      val direction4: List[Int] = List(0, 1)
      direction = direction4
    }
    direction /*!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!*/
  }

  val maxAmmo: Int = 10
  var ammo: Int = 10
  def reload(): Int ={
    ammo = maxAmmo
    ammo
  }

  val maxHealth: Int = 100
  this.health = maxHealth
  def damaged(hitPlayer: Player): Unit ={
    hitPlayer.health -= 10
    hitPlayer.health
  }

  def fire(world: World): Unit = {
    ammo -= 1
    val initialDirection: List[Int] = this.direction
    val spawnedCoordinate: List[Int] = List(this.coordinate(0) + this.direction(0), this.coordinate(1) + this.direction(1))
    bulletIncrement(world.map, initialDirection, spawnedCoordinate, null)
  }

  score = 0
  def scoreIncrement(eliminated: Player): Int ={
    score += 1
    score
  }

  alive = true

  def playerHit(hitPlayer: Player): Unit ={
    hitPlayer.health -= 10
    if(hitPlayer.health < 0){
      this.score += 1
      hitPlayer.alive = false
    }
  }

  def bulletIncrement(map: List[List[Int]], direction: List[Int], coordinate: List[Int], player: Player): Boolean = {
    var hit: Boolean = false
    if (direction(0) == 0) {//right
      if (direction(1) > 0) {
        if (map(coordinate(0))(coordinate(1)+1) == 3) {
          coordinate(1) - 1
          map(coordinate(0))(coordinate(1)) = 0
          map(coordinate(0))(coordinate(1)+1) = 0
          hit = true
        }
        coordinate(1) + 1
        if(map(coordinate(0))(coordinate(1)) == 1) {
          playerHit(player)
          hit = true
        }
        else if(map(coordinate(0))(coordinate(1)) == 2){
          hit = true
        }
        else {
          hit = false
        }
      }
      else if (direction(1) < 0) {//left
        if (map(coordinate(0))(coordinate(1)-1) == 3) {
          coordinate(1) + 1
          map(coordinate(0))(coordinate(1)) = 0
          map(coordinate(0))(coordinate(1)-1) = 0
          hit = true
        }
        coordinate(1) - 1
        if(map(coordinate(0))(coordinate(1)) == 1) {
          playerHit(player)
          hit = true
        }
        else if(map(coordinate(0))(coordinate(1)) == 2){
          hit = true
        }
        else {
          hit = false
        }
      }
    }
    else {
      if (direction(0) > 0) {//down
        if (map(coordinate(0)+1)(coordinate(1)) == 3) {
          coordinate(1) - 1
          map(coordinate(0))(coordinate(1)) = 0
          map(coordinate(0)+1)(coordinate(1)) = 0
          hit = true
        }
        coordinate(0) + 1
        if(map(coordinate(0))(coordinate(1)) == 1) {
          playerHit(player)
          hit = true
        }
        else if(map(coordinate(0))(coordinate(1)) == 2){
          hit = true
        }
        else {
          hit = false
        }
      }
      else if (direction(0) < 0) {//up
        if (map(coordinate(0)-1)(coordinate(1)) == 3) {
          coordinate(1) + 1
          map(coordinate(0))(coordinate(1)) = 0
          map(coordinate(0)-1)(coordinate(1)) = 0
          hit = true
        }
        coordinate(0) - 1
        if(map(coordinate(0))(coordinate(1)) == 1) {
          playerHit(player)
          hit = true
        }
        else if(map(coordinate(0))(coordinate(1)) == 2){
          hit = true
        }
        else {
          hit = false
        }
      }
    }
    hit
  }
  /*
  def move(Letter: String): List[Status] ={
    var Move: Status = new Status(List(0,0))
    if(Letter == "w"){
      val Move1: Status = new Status(List(0,-1))
      Move = Move1
      }
    else if(Letter == "s"){
      val Move2: Status = new Status(List(0,1))
      Move = Move2
      }
    else if(Letter == "a"){
      val Move3: Status = new Status(List(-1,0))
      Move = Move3
      }
    else if(Letter == "d"){
      val Move4: Status = new Status(List(1,0))
      Move = Move4
      }
    player.patch(1, Seq(Move), 1)/*!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!*/
  }
  */
}
//fire generates a bullet linked to that player and subtracts 1 ammo
//the new bullet will be called 60 times per second and will keep on traveling until it hits an object