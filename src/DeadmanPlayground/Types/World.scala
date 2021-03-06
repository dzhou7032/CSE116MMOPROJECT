package DeadmanPlayground.Types

class World(var players: Map[String, Player]) {
  /*var enemies: Map,[Int, Player]= this.players
    Map(
      0 -> new Player (List(new Status(List(0,0)), new Status(List(1, 0)), new Status(List(1,1)))),
      1 -> new Player (List(new Status(List(1,0)), new Status(List(1, 0)), new Status(List(1,1)))),
      new Tile -> new Player (List(new Status(List(4,new Tile)), new Status(List(1, 0)), new Status(List(1,1))))
    )
*/

  var objects: List[GameObjects] = List()

  var wallsx: List[Tile] = List()

  var map: Array[Array[Tile]] = Array.ofDim[Tile](10,10)
  for(tr <- 0 to 9){
    for(x <- 0 to 9) {
      map(tr)(x) = new Tile(Array(tr,x), true)
    }
  }
  for(trw <- 0 to 9){
    map(0)(trw) = new Tile(Array(0,trw), false)
    wallsx = map(0)(trw) :: wallsx
  }
  for(brw <- 0 to 9){
    map(9)(brw) = new Tile(Array(9,brw), false)
    wallsx = map(9)(brw) :: wallsx
  }
  for(lrw <- 0 to 9){
    map(lrw)(0) = new Tile(Array(lrw,0), false)
    wallsx = map(lrw)(0) :: wallsx
  }
  for(rrw <- 0 to 9){
    map(rrw)(9) = new Tile(Array(rrw,9), false)
    wallsx = map(rrw)(9) :: wallsx
  }
//  for((k,v) <- players){
//    map(v.coordinate(0))(v.coordinate(1)) = v
//  }


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

