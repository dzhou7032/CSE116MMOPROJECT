package DeadmanPlayground.Types

abstract class GameObjects(var coordinates: List[Double]) {
  var destroyed: Boolean = false

  def destroy(): Unit = {
    destroyed = true
  }
}
