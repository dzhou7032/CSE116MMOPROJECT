package DeadmanPlayground

import Array._
import DeadmanPlayground.Types.World
import DeadmanPlayground.Types.Player

class Game {
  def lastMan(contestants: World):Boolean/*String*/ = {
    //var winner: String = ""
    if(contestants.players.size == 1){
      //for((key, value) <- contestant){
        //winner = "player " + key.toString() + " win!"
      //}
      true
    }
    else {
      false
    }
  }
  /*
  def moveValidation(contestant: World): Boolean/*Status*/={
    val everyone: Map[Int, Player] = contestant.players
    var validation: Boolean = true
    for((key, value) <- everyone){
      val coordinateMoveX: Array.ofDim[Int]{2,2} = value(1)(0)
      val coordinateMoveY: Int = value(1)(1)
      val coordinateX: Int = value(0)(0)
      val coordinateY: Int = value(0)(1)
      val coordinateUpdatedX: Int = coordinateX + coordinateMoveX
      val coordinateUpdatedY: Int = coordinateY + coordinateMoveY
      if(contestant.map(coordinateUpdatedX)(coordinateUpdatedY) == 0){//updates players new location
        validation = true
      }
      else{//revert the players location back to its original spot
        validation = false
      }
    }
    validation
  }
  */

 def moveTest(Letter: String): List[Int] ={
   var Move: List[Int] = List(0, 0)
   if(Letter == "w"){
     val Move1: List[Int] = List(0,-1)
     Move = Move1
   }
   else if(Letter == "s"){
     val Move2: List[Int] = List(0,1)
     Move = Move2
   }
   else if(Letter == "a"){
     val Move3: List[Int] = List(-1,0)
     Move = Move3
   }
   else if(Letter == "d"){
     val Move4: List[Int] = List(1,0)
     Move = Move4
   }
   Move/*!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!*/
 }
  def shoot(map: Array[Array[Int]], direction: Array[Array[Int]]): Boolean ={
    var hit: Boolean = false
    if(direction(1)(0) == 0){
      if(direction(1)(1) > 0){
        var varifier: Boolean = false
        for (down <- range(direction(0)(1), map.length, direction(1)(1))) {
          if (map(down)(direction(0)(0)) != 0) {
            hit = true
            varifier = true
          }
          else {
            hit = false
          }
        }
      }
      else if(direction(1)(1) < 0){
        var varifier: Boolean = false
        for (up <- range(direction(0)(1), -1, direction(1)(1))) {
          if (map(up)(direction(0)(0)) != 0) {
            hit = true
            varifier = true
          }
          else {
            hit = false
          }
        }
      }
    }
    else{
      if(direction(1)(0) > 0){
        var varifier: Boolean = false
        for (right <- range(direction(0)(0), map(0).length, direction(1)(0))) {
          if (map(direction(0)(1))(right) != 0) {
            hit = true
            varifier = true
          }
          else {
            hit = false
          }
        }
      }
      else if(direction(1)(0) < 0) {
        var varifier: Boolean = false
        for (left <- range(direction(0)(0), -1, direction(1)(0))) {
          if (varifier == false){
            if (map(direction(0)(1))(left) != 0) {
              hit = true
              varifier = true
            }
            else {
              hit = false
            }
          }
        }
      }
    }
    hit
  }
}

