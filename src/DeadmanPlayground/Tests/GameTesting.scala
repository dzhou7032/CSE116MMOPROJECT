//package DeadmanPlayground.Tests
//
//import DeadmanPlayground.Game
//import org.scalatest._
//import DeadmanPlayground.Types._
//
//class GameTesting extends FunSuite{
// test("Test Player Movement"){
//   var player1 = new Player(1, List(1,1), List(-1,0), 100,0, true, false, bulletOwnership = List())
//   var player2 = new Player(2, List(2,2), List(-1,0), 100, 0, true, false, bulletOwnership = List())
//   var thisWorld = new World(Map(0->player1, 1->player2))
//   player1.move("w", thisWorld)
//   assert(player1.coordinate == List(1,1),"failed")//did not move
//   player1.move("s", thisWorld)
//   assert(player1.coordinate == List(2,1), "failed")//moved
//   player1.move("s", thisWorld)
//   player1.move("d", thisWorld)
//   assert(player1.coordinate == List(3,2), "failed")//multiple steps
//   player2.move("a", thisWorld)
//   player2.move("d", thisWorld)
//   player2.move("w", thisWorld)
//   assert(player2.coordinate == List(1,2), "failed")//player 2 testing
// }
//  test("Test Game"){
//    val player1 = new Player(1, List(1,1), List(-1,0), 100,0, true, false, bulletOwnership = List())
//    val player2 = new Player(2, List(2,2), List(-1,0), 100, 0, true, false, bulletOwnership = List())
//    val player3 = new Player(3, List(2,4), List(-1,0), 100, 0, true, false, bulletOwnership = List())
//    val contestants: World = new World(Map(1 -> player1, 2 -> player2, 3 -> player3))//Three players left
//    val round: Game = new Game
//    assert(round.lastMan(contestants) == false, "failed")
//    val contestantsOneDeath: World = new World(Map(1 -> player1, 2 -> player2))//Two players left
//    assert(round.lastMan(contestantsOneDeath) == false, "failed")
//    val contestantsTwoDeath: World = new World(Map(1 -> player1))
//    assert(round.lastMan(contestantsTwoDeath) == true, "failed")//One victor
//    val contestantsThreeDeath: World = new World(Map())
//    assert(round.lastMan(contestantsThreeDeath) == false, "failed")//No players
//  }
//  test("Test Bullet"){
//    val player2 = new Player(2, List(9,9), List(-1,0), 100, 0, true, false, bulletOwnership = List())
//    val player1 = new Player(1, List(1,1), List(-1,0), 100,0, true, false, bulletOwnership = List())
//    val bulletPresentWorld: World = new World(Map(1 -> player1, 2 -> player2))
//    var bulletOne: Bullet = new Bullet(List(1,2), player1, false)
//    val bulletTwo: Bullet = new Bullet(List(1,3), player1, false)
//    assert(bulletPresentWorld.map(1)(1).isInstanceOf[Player])
//    assert(bulletOne.bulletIncrement(bulletPresentWorld.map, List(0,-1), List(1,2), player2, null, player1) == true, "failed")//hits player
//    assert(bulletOne.bulletIncrement(bulletPresentWorld.map, List(-1,0), List(1,2), player2, null, player1) == true, "failed")//hits wall
//    assert(bulletOne.bulletIncrement(bulletPresentWorld.map, List(0,1), List(1,2), player2, null, player1) == false,"failed")//hits bullet
//    assert(bulletOne.bulletIncrement(bulletPresentWorld.map, List(1,0), List(1,2), player2, null, player1) ==  false,"failed")//hits nothing
//  }
//}
