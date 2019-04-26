package DeadmanPlayground.Types

class Bullet(coordinates: List[Double], playerID : Player) extends GameObjects(coordinates) {
  def bulletIncrement(map: Array[Array[Tile]], direction: List[Int], coordinate: List[Double], player: Player, bullet: Bullet, bulletOwner: Player): Boolean = {
    //assuming you give a bullet because was unable to check to see if the tile infront of it is a bullet or not
    var hit: Boolean = false

    if (direction(0) == 0) {//right
      if (direction(1) > 0) {
        coordinate(1) += .2
        if(coordinate(1) % 1 == 0) {
          /*if (map(coordinate(0))(coordinate(1) + 1).isInstanceOf[Bullet]) {
            coordinate(1) - 1
            map.toBuffer.remove(coordinate(0))(coordinate(1))
            map.toBuffer.remove(coordinate(0))(coordinate(1) + 1)
            hit = true
          }
          */
          if (map(coordinate(0).toInt)(coordinate(1).toInt + 1).listOfPlayer.length >= 1) {
            hit = true
          }
          else if (map(coordinate(0).toInt)(coordinate(1).toInt + 1).walkable == false) {
            hit = true
          }
          else {
            hit = false
          }
        }
      }
      else if (direction(1) < 0) {
        coordinate(1) -= .2
        if(coordinate(1) % 1 == 0) {
          //left
          /*if (map(coordinate(0))(coordinate(1) - 1).isInstanceOf[Bullet]) {
            coordinate(1) + 1
            map.toBuffer.remove(coordinate(0))(coordinate(1))
            map.toBuffer.remove(coordinate(0))(coordinate(1) - 1)
            hit = true
          }
          */
          if (map(coordinate(0).toInt)(coordinate(1).toInt - 1).listOfPlayer.length >= 1) {
            hit = true
          }
          else if (map(coordinate(0).toInt)(coordinate(1).toInt - 1).walkable == false) {
            hit = true
          }
          else {
            hit = false
          }
        }
      }
    }
    else {
      if (direction(0) > 0) {//down
        coordinate(0) += .2
        if(coordinate(0) % 1 == 0) {
          /*
          if (map(coordinate(0) + 1)(coordinate(1)).isInstanceOf[Bullet]) {
            coordinate(0) - 1
            map.toBuffer.remove(coordinate(0))(coordinate(1))
            map.toBuffer.remove(coordinate(0) + 1)(coordinate(1))
            hit = true
          }
          */
          if (map(coordinate(0).toInt + 1)(coordinate(1).toInt).listOfPlayer.length >= 1) {
            hit = true
          }
          else if (map(coordinate(0).toInt + 1)(coordinate(1).toInt).walkable == false) {
            hit = true
          }
          else {
            hit = false
          }
        }
      }
      else if (direction(0) < 0) {
        //up
        coordinate(0) -= .2
        if (coordinate(0) % 1 == 0) {
          /*
          if (map(coordinate(0) - 1)(coordinate(1)).isInstanceOf[Bullet]) {
            coordinate(0) + 1
            map.toBuffer.remove(coordinate(0))(coordinate(1))
            map.toBuffer.remove(coordinate(0) - 1)(coordinate(1))
            hit = true
          }
          */
          if (map(coordinate(0).toInt - 1)(coordinate(1).toInt).listOfPlayer.length >= 1) {
            hit = true
          }
          else if (map(coordinate(0).toInt - 1)(coordinate(1).toInt).walkable == false) {
            hit = true
          }
          else {
            hit = false
          }
        }
      }
    }
    hit
  }
}
