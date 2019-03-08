package DeadmanPlayground

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
  def moveValidation(contestant: World): Boolean/*Status*/={
    val everyone: Map[Int, Player] = contestant.players
    var validation: Boolean = true
    for((key, value) <- everyone){
      val coordinateMoveX: Int = value(1)(0)
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
  /*
 def moveTest(Letter: String): List[Int] ={
   var Move: List[Int] = List(0,0)
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
 */
}

