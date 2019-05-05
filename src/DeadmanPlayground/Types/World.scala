package DeadmanPlayground.Types

class World(var players: Map[Int, Player]) {
  /*var enemies: Map,[Int, Player]= this.players
    Map(
      0 -> new Player (List(new Status(List(0,0)), new Status(List(1, 0)), new Status(List(1,1)))),
      1 -> new Player (List(new Status(List(1,0)), new Status(List(1, 0)), new Status(List(1,1)))),
      new Tile -> new Player (List(new Status(List(4,new Tile)), new Status(List(1, 0)), new Status(List(1,1))))
    )
*/

  var map: Array[Array[GameObjects]] = Array.ofDim[GameObjects](10,10)
  for(tr <- 0 to 9){
    for(x <- 0 to 9) {
      map(tr)(x) = new Tile(List(tr,x), true)
    }

  }
  for(trw <- 0 to 9){
    map(0)(trw) = new Tile(List(0,trw), false)
  }
  for(brw <- 0 to 9){
    map(9)(brw) = new Tile(List(9,brw), false)
  }
  for(lrw <- 0 to 9){
    map(lrw)(0) = new Tile(List(lrw,0), false)
  }
  for(rrw <- 0 to 9){
    map(rrw)(9) = new Tile(List(rrw,9), false)
  }
  for((k,v) <- players){
    map(v.coordinate(0))(v.coordinate(1)) = v
  }

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
        if(map(coordinate(0))(coordinate(1)).isInstanceOf[Player]) {
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
          bulletOwner.playerHit(player)
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
 /*
  var map: List[List[Tile]] = List()
  for(trw <- 0 to 9){
    var something: List[Tile] = List()
    map(0,trw)= new Tile(List(0,trw), false)
  }
  for(tr <- 1 to 9) {
    var something: List[Tile] = List()
    for (tc <- 1 to 9) {
      map(tr,tc) = new Tile(List(tr,tc), true)
    }
  }
  for(brw <- 0 to 9){
    var something: List[Tile] = List()
    map(9, brw) = new Tile(List(9,brw), false)
  }
  for(lrw <- 0 to 9){
    var something: List[Tile] = List()
    map(lrw,0) = new Tile(List(lrw,0), false)
  }
  for(rrw <- 0 to 9){
    var something: List[Tile] = List()
    map(rrw,0)= new Tile(List(rrw,0), false)
  }
*/

}

