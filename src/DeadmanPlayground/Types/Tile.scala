package DeadmanPlayground.Types

class Tile(coordinate: List[Double],var walkable: Boolean) {
  var listOfPlayer: List[Player] = List()
  var listOfBullet: List[Bullet] = List()
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
