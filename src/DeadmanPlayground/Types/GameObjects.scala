package DeadmanPlayground.Types

abstract class GameObjects(var coordinates: Array[Double]) {
  var destroyed: Boolean = false

  def destroy(): Unit = {
    destroyed = true
  }
}
