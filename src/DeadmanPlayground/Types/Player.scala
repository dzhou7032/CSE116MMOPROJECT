package DeadmanPlayground.Types

class Player(val ID: String, var coordinate: Array[Double], var direction: Array[Int], var health: Double) extends GameObjects(coordinate) {
  def move(directions: Array[Int], world: World): Array[Double] = {
    val usedMap: Array[Array[Tile]] = world.map
    var area: Array[Double] = this.coordinate
    //var direction: List[Int] = playerDirection()
    var facing: Array[Int] = directions
    val yCoordinate: Int = area(0).toInt + facing(0)
    val xCoordinate: Int = area(1).toInt + facing(1)
    if (usedMap(yCoordinate)(xCoordinate).walkable == true) {
      //updates players new location
      usedMap(this.coordinate(0).toInt)(this.coordinate(1).toInt) = new Tile(Array(this.coordinate(0), this.coordinate(1)), true)
      area = Array(yCoordinate, xCoordinate)
      this.coordinate = area
      usedMap(this.coordinate(0).toInt)(this.coordinate(1).toInt).listOfPlayer += this
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
  def dead(hitPlayer: Player): Boolean = {
    var checkDead: Boolean = false
    if(hitPlayer.health <= 0){
      hitPlayer.destroy()
      checkDead = true
    }
    checkDead
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
    val usedMap: Array[Array[Tile]] = world.map
    ammo -= 1
    val initialDirection: Array[Int] = this.direction
    val spawnedCoordinate: Array[Double] = Array(this.coordinate(0) + initialDirection(0).toDouble, this.coordinate(1) + initialDirection(1).toDouble)
    var createdBullet: Bullet = new Bullet(spawnedCoordinate, this)
    usedMap(spawnedCoordinate(0).toInt)(spawnedCoordinate(1).toInt).listOfBullet += createdBullet
  }



  /*def playerHit(hitPlayer: Player): Unit = {
    hitPlayer.health -= 10
    if (hitPlayer.health <= 0) {
      this.score += 1
      hitPlayer.alive = false
    }
  }
  */
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