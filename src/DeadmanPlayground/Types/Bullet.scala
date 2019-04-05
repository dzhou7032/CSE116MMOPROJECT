package DeadmanPlayground.Types

class Bullet(coordinates: List[Int], playerID : Player, walkable: Boolean) extends GameObjects(coordinates, walkable) {

  def bulletIncrement(map: Array[Array[GameObjects]], direction: List[Int], coordinate: List[Int], player: Player, bullet: Bullet, bulletOwner: Player): Boolean = {
    //assuming you give a bullet because was unable to check to see if the tile infront of it is a bullet or not
  var hit: Boolean = false
  if (direction(0) == 0) {//right
    if (direction(1) > 0) {
      if (map(coordinate(0))(coordinate(1)+1) == bullet) {
        coordinate(1) - 1
        map.toBuffer.remove(coordinate(0))(coordinate(1))
        map.toBuffer.remove(coordinate(0))(coordinate(1)+1)
        hit = true
      }
      coordinate(1) + 1
      if(map(coordinate(0))(coordinate(1))/*.isInstanceOf[Player]*/ == player) {
        player.playerHit(player)
        hit = true
      }
      else if(map(coordinate(0))(coordinate(1)).walkable == false){
        hit = true
      }
      else {
        hit = false
      }
    }
    else if (direction(1) < 0) {//left
      if (map(coordinate(0))(coordinate(1)-1) == bullet) {
        coordinate(1) + 1
        map.toBuffer.remove(coordinate(0))(coordinate(1))
        map.toBuffer.remove(coordinate(0))(coordinate(1)+1)
        hit = true
      }
      coordinate(1) - 1
      if(map(coordinate(0))(coordinate(1)) == player) {
        player.playerHit(player)
        hit = true
      }
      else if(map(coordinate(0))(coordinate(1)).walkable == false){
        hit = true
      }
      else {
        hit = false
      }
    }
  }
  else {
    if (direction(0) > 0) {//down
      if (map(coordinate(0)+1)(coordinate(1)) == bullet) {
        coordinate(1) - 1
        map.toBuffer.remove(coordinate(0))(coordinate(1))
        map.toBuffer.remove(coordinate(0))(coordinate(1)+1)
        hit = true
      }
      coordinate(0) + 1
      if(map(coordinate(0))(coordinate(1)) == player) {
        player.playerHit(player)
        hit = true
      }
      else if(map(coordinate(0))(coordinate(1)).walkable == false){
        hit = true
      }
      else {
        hit = false
      }
    }
    else if (direction(0) < 0) {//up
      if (map(coordinate(0)-1)(coordinate(1)) == bullet) {
        coordinate(1) + 1
        map.toBuffer.remove(coordinate(0))(coordinate(1))
        map.toBuffer.remove(coordinate(0))(coordinate(1)+1)
        hit = true
      }
      coordinate(0) - 1
      if(map(coordinate(0))(coordinate(1)) == player) {
        player.playerHit(player)
        hit = true
      }
      else if(map(coordinate(0))(coordinate(1)).walkable == false){
        hit = true
      }
      else {
        hit = false
      }
    }
  }
  hit
}
}
