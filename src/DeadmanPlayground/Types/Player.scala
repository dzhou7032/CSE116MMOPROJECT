package DeadmanPlayground.Types

class Player(var coordinate: List[Int], var direction: List[Int], var health: Int) {
  def move(/*playerDirection: String => List[Int],*/ world: World): List[Int] ={
    var map: List[List[Int]] = world.map
    var area: List[Int] = this.coordinate
    //var direction: List[Int] = playerDirection()
    var facing: List[Int] = this.direction
    val yCoordinate: Int = area(0) + facing(0)
    val xCoordinate: Int = area(1) + facing(1)
    if(map(yCoordinate)(xCoordinate) == 0){//updates players new location
      area = List(yCoordinate, xCoordinate)
      area
    }
    else{//revert the players location back to its original spot
      area
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
  /*def shotFired(): Int = {
    ammo -= 1
    ammo
  }*/


  def fire(map: List[List[Int]], direction: Array[Int], coordinate: Array[Int]): Boolean = {
    ammo -= 1
    var hit: Boolean = false
    if (direction(0) == 0) {
      //x going down
      if (direction(1) > 0) {
        coordinate(1) + 1
        if (map(coordinate(0))(coordinate(1)) != 0) {
          hit = true
        }
        else {
          hit = false
        }
      }
      else if (direction(1) < 0) {
        //x going up
        coordinate(1) - 1
        if (map(coordinate(0))(coordinate(1)) != 0) {
          hit = true
        }
        else {
          hit = false
        }
      }
    }
    else {
      if (direction(0) > 0) {
        var varifier: Boolean = false
        coordinate(0) + 1
        if (map(coordinate(0))(coordinate(1)) != 0) {
          hit = true
          varifier = true
        }
        else {
          hit = false
        }
      }
      else if (direction(0) < 0) {
        var varifier: Boolean = false
        coordinate(0) - 1
        if (varifier == false) {
          if (map(coordinate(0))(coordinate(1)) != 0) {
            hit = true
            varifier = true
          }
          else {
            hit = false
          }
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
