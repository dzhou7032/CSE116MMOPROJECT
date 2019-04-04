package DeadmanPlayground.Types

class World(var players: Map[Int, Player]) {
  /*var enemies: Map,[Int, Player]= this.players
    Map(
      0 -> new Player (List(new Status(List(0,0)), new Status(List(1, 0)), new Status(List(1,1)))),
      1 -> new Player (List(new Status(List(1,0)), new Status(List(1, 0)), new Status(List(1,1)))),
      new Tile -> new Player (List(new Status(List(4,new Tile)), new Status(List(1, 0)), new Status(List(1,1))))
    )
*/

  var map: List[List[GameObjects]] = List()
  for(trw <- 0 to 9){
    map(0,trw)= new Tile(List(0,trw), false)
  }
    for(tr <- 1 to 9) {
      for (tc <- 1 to 9) {
        map(tr,tc) = new Tile(List(tr,tc), true)
      }
    }
  for(brw <- 0 to 9){
    map(9, brw) = new Tile(List(9,brw), false)
  }
  for(lrw <- 0 to 9){
    map(lrw,0) = new Tile(List(lrw,0), false)
  }
  for(rrw <- 0 to 9){
    map(rrw,0)= new Tile(List(rrw,0), false)
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

