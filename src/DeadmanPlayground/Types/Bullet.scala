//package DeadmanPlayground.Types
//
//class Bullet(coordinates: List[Double], playerID : Player) extends GameObjects(coordinates) {
//  def bulletIncrement(map: Array[Array[Tile]], direction: List[Int], coordinate: List[Double]): List[Any] = {
//    //assuming you give a bullet because was unable to check to see if the tile infront of it is a bullet or not
//    val usedMap: Array[Array[Tile]] = map
//    if (direction(0) == 0) {//right
//      if (direction(1) > 0) {
//        coordinate(1) += .2
//        if(coordinate(1) % 1 == 0) {
//          /*if (map(coordinate(0))(coordinate(1) + 1).isInstanceOf[Bullet]) {
//            coordinate(1) - 1
//            map.toBuffer.remove(coordinate(0))(coordinate(1))
//            map.toBuffer.remove(coordinate(0))(coordinate(1) + 1)
//            hit = true
//          }
//          */
//          if (usedMap(coordinate(0).toInt)(coordinate(1).toInt + 1).listOfPlayer.length >= 1) {
//            usedMap(coordinate(0).toInt)(coordinate(1).toInt - 1).listOfBullet -= this//removes bullet
//            usedMap(coordinate(0).toInt)(coordinate(1).toInt + 1).listOfPlayer(0).damaged(usedMap(coordinate(0).toInt)(coordinate(1).toInt - 1).listOfPlayer(0))//damages player
//            this.destroy()
//            if(usedMap(coordinate(0).toInt)(coordinate(1).toInt).listOfPlayer(0).dead(usedMap(coordinate(0).toInt)(coordinate(1).toInt).listOfPlayer(0))){//checks if player is dead or not
//              usedMap(coordinate(0).toInt)(coordinate(1).toInt).listOfPlayer -= usedMap(coordinate(0).toInt)(coordinate(1).toInt).listOfPlayer(0)
//            }
//          }
//          else if (usedMap(coordinate(0).toInt)(coordinate(1).toInt + 1).walkable == false) {
//            usedMap(coordinate(0).toInt)(coordinate(1).toInt - 1).listOfBullet -= this//bullet collided with a wall
//            this.destroy()
//          }
//          else {
//            usedMap(coordinate(0).toInt)(coordinate(1).toInt).listOfBullet -= this//assumes that "this" is the bullet removed
//            usedMap(coordinate(0).toInt)(coordinate(1).toInt + 1).listOfBullet += this//assumes that "this" is the bullet added
//          }
//        }
//      }
//      else if (direction(1) < 0) {
//        coordinate(1) -= .2
//        if(coordinate(1) % 1 == 0) {
//          //left
//          /*if (map(coordinate(0))(coordinate(1) - 1).isInstanceOf[Bullet]) {
//            coordinate(1) + 1
//            map.toBuffer.remove(coordinate(0))(coordinate(1))
//            map.toBuffer.remove(coordinate(0))(coordinate(1) - 1)
//            hit = true
//          }
//          */
//          if (usedMap(coordinate(0).toInt)(coordinate(1).toInt - 1).listOfPlayer.length >= 1) {
//            usedMap(coordinate(0).toInt)(coordinate(1).toInt + 1).listOfBullet -= this
//            usedMap(coordinate(0).toInt)(coordinate(1).toInt - 1).listOfPlayer(0).damaged(usedMap(coordinate(0).toInt)(coordinate(1).toInt - 1).listOfPlayer(0))
//            this.destroy()
//            if(usedMap(coordinate(0).toInt)(coordinate(1).toInt).listOfPlayer(0).dead(usedMap(coordinate(0).toInt)(coordinate(1).toInt).listOfPlayer(0))){
//              usedMap(coordinate(0).toInt)(coordinate(1).toInt).listOfPlayer -= usedMap(coordinate(0).toInt)(coordinate(1).toInt).listOfPlayer(0)
//            }
//          }
//          else if (usedMap(coordinate(0).toInt)(coordinate(1).toInt - 1).walkable == false) {
//            usedMap(coordinate(0).toInt)(coordinate(1).toInt + 1).listOfBullet -= this
//            this.destroy()
//          }
//          else {
//            usedMap(coordinate(0).toInt)(coordinate(1).toInt).listOfBullet -= this
//            usedMap(coordinate(0).toInt)(coordinate(1).toInt - 1).listOfBullet += this
//          }
//        }
//      }
//    }
//    else {
//      if (direction(0) > 0) {//down
//        coordinate(0) += .2
//        if(coordinate(0) % 1 == 0) {
//          /*
//          if (map(coordinate(0) + 1)(coordinate(1)).isInstanceOf[Bullet]) {
//            coordinate(0) - 1
//            map.toBuffer.remove(coordinate(0))(coordinate(1))
//            map.toBuffer.remove(coordinate(0) + 1)(coordinate(1))
//            hit = true
//          }
//          */
//          if (usedMap(coordinate(0).toInt + 1)(coordinate(1).toInt).listOfPlayer.length >= 1) {
//            usedMap(coordinate(0).toInt - 1)(coordinate(1).toInt).listOfBullet -= this
//            usedMap(coordinate(0).toInt + 1)(coordinate(1).toInt).listOfPlayer(0).damaged(usedMap(coordinate(0).toInt)(coordinate(1).toInt - 1).listOfPlayer(0))
//            this.destroy()
//            if(usedMap(coordinate(0).toInt)(coordinate(1).toInt).listOfPlayer(0).dead(usedMap(coordinate(0).toInt)(coordinate(1).toInt).listOfPlayer(0))){
//              usedMap(coordinate(0).toInt)(coordinate(1).toInt).listOfPlayer -= usedMap(coordinate(0).toInt)(coordinate(1).toInt).listOfPlayer(0)
//            }
//          }
//          else if (usedMap(coordinate(0).toInt + 1)(coordinate(1).toInt).walkable == false) {
//            usedMap(coordinate(0).toInt - 1)(coordinate(1).toInt).listOfBullet -= this
//            this.destroy()
//          }
//          else {
//            usedMap(coordinate(0).toInt)(coordinate(1).toInt).listOfBullet -= this
//            usedMap(coordinate(0 + 1).toInt)(coordinate(1).toInt).listOfBullet += this
//          }
//        }
//      }
//      else if (direction(0) < 0) {
//        //up
//        coordinate(0) -= .2
//        if (coordinate(0) % 1 == 0) {
//          /*
//          if (map(coordinate(0) - 1)(coordinate(1)).isInstanceOf[Bullet]) {
//            coordinate(0) + 1
//            map.toBuffer.remove(coordinate(0))(coordinate(1))
//            map.toBuffer.remove(coordinate(0) - 1)(coordinate(1))
//            hit = true
//          }
//          */
//          if (usedMap(coordinate(0).toInt - 1)(coordinate(1).toInt).listOfPlayer.length >= 1) {
//            usedMap(coordinate(0).toInt + 1)(coordinate(1).toInt - 1).listOfBullet -= this
//            usedMap(coordinate(0).toInt - 1)(coordinate(1).toInt + 1).listOfPlayer(0).damaged(usedMap(coordinate(0).toInt)(coordinate(1).toInt - 1).listOfPlayer(0))
//            this.destroy()
//            if(usedMap(coordinate(0).toInt)(coordinate(1).toInt).listOfPlayer(0).dead(usedMap(coordinate(0).toInt)(coordinate(1).toInt).listOfPlayer(0))){
//              usedMap(coordinate(0).toInt)(coordinate(1).toInt).listOfPlayer -= usedMap(coordinate(0).toInt)(coordinate(1).toInt).listOfPlayer(0)
//            }
//          }
//          else if (usedMap(coordinate(0).toInt - 1)(coordinate(1).toInt).walkable == false) {
//            usedMap(coordinate(0).toInt + 1)(coordinate(1).toInt).listOfBullet -= this
//            this.destroy()
//          }
//          else {
//            usedMap(coordinate(0).toInt)(coordinate(1).toInt).listOfBullet -= this
//            usedMap(coordinate(0).toInt - 1)(coordinate(1).toInt).listOfBullet += this
//          }
//        }
//      }
//    }
//    List(this.coordinates, usedMap)
//  }
//}
