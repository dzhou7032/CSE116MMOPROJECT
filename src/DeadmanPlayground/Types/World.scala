package DeadmanPlayground.Types

class World(var players: Map[Int, Player]) {
  /*var enemies: Map[Int, Player]= this.players
    Map(
      0 -> new Player (List(new Status(List(0,0)), new Status(List(1, 0)), new Status(List(1,1)))),
      1 -> new Player (List(new Status(List(1,0)), new Status(List(1, 0)), new Status(List(1,1)))),
      2 -> new Player (List(new Status(List(4,2)), new Status(List(1, 0)), new Status(List(1,1))))
    )
*/
  def hit(ID: Int): World ={
    var hitPlayer: World = map(ID, new Player(new Status(players(ID).coordinate), players(ID).health - 1))
    hitPlayer
  }
  var map: List[List[Int]] = List(
    List(0,0,2,0,0),
    List(0,0,0,0,0),
    List(0,2,0,0,0),
    List(0,0,0,0,0),
    List(0,0,0,0,0))
}
