package Tests

import DeadmanPlayground.Game
import DeadmanPlayground.Types.{World,Status,Player}
import org.scalatest._

class TestGame extends FunSuite {
  test("testing for winner") {
    val victor: Game = new Game
    val units: World = new World(Map(
      0 -> new Player (List(new Status(List(0,0)), new Status(List(1, 0)), new Status(List(1,1)))),
      1 -> new Player (List(new Status(List(1,0)), new Status(List(1, 0)), new Status(List(1,1)))),
      2 -> new Player (List(new Status(List(4,2)), new Status(List(1, 0)), new Status(List(1,1))))))
    assert(victor.lastMan(units) == false,"failed testing")
    val units2 = new World(Map(
      0 -> new Player (List(new Status(List(0,0)), new Status(List(1, 0)), new Status(List(1,1))))))
    assert(victor.lastMan(units2) == true, "failed testing")
    val unit3 = new World(Map())
    assert(victor.lastMan(unit3) == false, "failed testing")
  }

  /*test("testing move function"){
    var player1: Player = new Player(List(new Status(List(0,0)), new Status(List(1, 0)), new Status(List(1,1))))
    player1.move("w")
    assert(new Status(player1(1)) == new Status(List(0,-1)),"failed testing")
    player1.move("s")
    assert(new Status(player1(1)) == new Status(List(0,1)),"failed testing")
    player1.move("a")
    assert(new Status(player1(1)) == new Status(List(-1,0)),"failed testing")
    player1.move("d")
    assert(new Status(player1(1)) == new Status(List(1,0)),"failed testing")
  }
  */
  test("testing testMove function"){
    val action: Game = new Game
    assert(action.moveTest("w") == List(0,-1),"failed testing")
    assert(action.moveTest("s") == List(0, 1),"failed testing")
    assert(action.moveTest("a") == List(-1, 0),"failed testing")
    assert(action.moveTest("d") == List(1,0),"failed testing")
    assert(action.moveTest("q") == List(0, 0), "failed testing")
  }
  test("testing testFire function") {
    val map1: Array[Array[Int]] = Array(
      Array(0, 0, 2, 0, 0),
      Array(0, 0, 0, 0, 0),
      Array(0, 2, 0, 0, 0),
      Array(0, 0, 0, 0, 0),
      Array(0, 0, 0, 0, 0))
    val direction1: Array[Array[Int]] = Array(
      Array(2, 2), Array(0, 1))
    val shot: Game = new Game
    assert(shot.shoot(map1, direction1) == false, "failed testing")
    val direction2: Array[Array[Int]] = Array(
      Array(2, 2), Array(-1, 0))
    assert(shot.shoot(map1, direction2) == true, "failed testing")
    val direction3: Array[Array[Int]] = Array(
      Array(2, 2), Array(0, -1))
    assert(shot.shoot(map1, direction3) == true, "failed testing")
    val direction4: Array[Array[Int]] = Array(
      Array(0,0), Array(1, 0))
    assert(shot.shoot(map1, direction4) == false, "failed testing")
  }
}
