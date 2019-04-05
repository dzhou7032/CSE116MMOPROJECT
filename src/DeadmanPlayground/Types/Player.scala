package DeadmanPlayground.Types

class Player(val ID: Int, var coordinate: List[Int], var direction: List[Int], var health: Double, var score: Int, var alive: Boolean, walkable: Boolean, var bulletOwnership: List[Bullet]) extends GameObjects(coordinate, walkable) {
  def move(directions: String, world: World): List[Int] = {
    var map: Array[Array[GameObjects]] = world.map
    var area: List[Int] = this.coordinate
    //var direction: List[Int] = playerDirection()
    var facing: List[Int] = playerDirection(directions)
    val yCoordinate: Int = area(0) + facing(0)
    val xCoordinate: Int = area(1) + facing(1)
    if (map(yCoordinate)(xCoordinate).walkable == true) {
      //updates players new location
      map(this.coordinate(0))(this.coordinate(1)) = new Tile(List(this.coordinate(0), this.coordinate(1)), true)
      area = List(yCoordinate, xCoordinate)
      this.coordinate = area
      map(this.coordinate(0))(this.coordinate(1)) = this
      coordinate
    }
    else {
      //revert the players location back to its original spot
      this.coordinate = area
      coordinate
    }
  }
  def playerDirection(Letter: String): List[Int] = {
    var direction: List[Int] = List(1, 0)
    if (Letter == "w") {
      val direction1: List[Int] = List(-1, -0) //
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

  def reload(): Int = {
    ammo = maxAmmo
    ammo
  }

  val maxHealth: Int = 100
  this.health = maxHealth

  def damaged(hitPlayer: Player): Unit = {
    hitPlayer.health -= 10
  }
  def shoot(): Boolean={
    if(ammo>0){
      ammo-=1
      return true
    }
    else{
      return false
    }
  }

  def fire(world: World): Unit = {
    ammo -= 1
    val initialDirection: List[Int] = this.direction
    val spawnedCoordinate: List[Int] = List(this.coordinate(0) + this.direction(0), this.coordinate(1) + this.direction(1))
    if (world.map(spawnedCoordinate(0))(spawnedCoordinate(1)).walkable == true) {
      //set it equal to the objects walkable boolean
      bulletOwnership :+ new Bullet(spawnedCoordinate, this, true)
    }

    //bulletIncrement(world.map, initialDirection, spawnedCoordinate, null)
  }

  score = 0

  def scoreIncrement(eliminated: Player): Int = {
    score += 1
    score
  }

  def playerHit(hitPlayer: Player): Unit = {
    hitPlayer.health -= 10
    if (hitPlayer.health <= 0) {
      this.score += 1
      hitPlayer.alive = false
    }
  }
  /*
  def hit(): Unit = {
    health -= 10
    if (health < 0) {
      alive = false
    }
  }
  */
}

//write in bullet method
//the new bullet will be called 60 times per second and will keep on traveling until it hits an object