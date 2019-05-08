package DeadmanPlayground.Types

import scala.collection.mutable.ListBuffer

class Tile(var coordinate: List[Double],var walkable: Boolean) {

  var listOfPlayer: ListBuffer[Player] = new ListBuffer()
  var listOfBullet: ListBuffer[Bullet] = new ListBuffer()

  def additionOfObjects(objects: GameObjects): Unit ={
    if(objects.isInstanceOf[Player] && walkable == true){
      listOfPlayer :+ objects
      walkable = false
    }
    else{
      listOfBullet :+ objects
    }
  }
}
