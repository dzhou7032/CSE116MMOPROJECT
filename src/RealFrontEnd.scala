import DeadmanPlayground.Types.{Player, World}
import io.socket.client.{IO, Socket}
import io.socket.emitter.Emitter
import javafx.scene.input.{KeyCode, KeyEvent}
import play.api.libs.json.{JsValue, Json}
import scalafx.animation.AnimationTimer
import scalafx.application
import scalafx.application.{JFXApp, Platform}
import scalafx.scene.canvas.Canvas
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.paint.Color.{Gray, Green, Red}
import scalafx.scene.shape.Rectangle
import scalafx.scene.{Group, Scene}

object RealFrontEnd extends JFXApp {

  var socket: Socket = IO.socket("http://localhost:8080/")
  socket.on("gameState", new HandleMessagesFromPython)
  socket.connect()

  //  socket.emit("register", "myUsername")

  val gameCanvas = new Canvas(320,320)
  val playerCanvas = new Canvas(320, 320)
  val gc = gameCanvas.graphicsContext2D
  val playergc = playerCanvas.graphicsContext2D

  var theplayersPos: Map[String, ImageView] = Map()
  var playerList: Group = new Group{}

  stage = new JFXApp.PrimaryStage{
    title.value = "DEADMAN PLAYGROUND"
    width = 500
    height = 500
    scene = new Scene{
      content = List(gameCanvas, playerList, playerCanvas)
      addEventHandler(KeyEvent.KEY_PRESSED, (event: KeyEvent) => keyPressed(event.getCode))
    }
  }
  println("hello")
  class HandleMessagesFromPython() extends Emitter.Listener {
    theplayersPos = Map()

    override def call(objects: Object*): Unit = {
      playergc.clearRect(0, 0, 500, 500)
      val message = objects.apply(0).toString
      val messagex: JsValue = Json.parse(message)
      var gridSize: Map[String, Int] = (messagex \ "gridSize").as[Map[String, Int]]
      var walls: List[Map[String, Int]] = (messagex \ "walls").as[List[Map[String, Int]]]
      var players: List[Map[String, JsValue]] = (messagex \ "players").as[List[Map[String, JsValue]]]

      stage.width = gridSize("x") + 100
      stage.height = gridSize("y") + 100
      for (w <- walls) {
        var gameImage: Image = new Image("static/walltile.png")
        gc.drawImage(gameImage, w("x") * 32, w("y") * 32)
      }
      for (j <- players) {
        var surprise = new ImageView(new Image("static/charstand.png"))
        if (j("it").as[Boolean] == true) {
          surprise = new ImageView(new Image("static/charstandit.png"))
        }
        if (checking(j)) {
          Platform.runLater {
            if (j("it").as[Boolean] == true) {
              theplayersPos(j("id").toString()).setImage(new Image("static/charstandit.png"))
            } else {
              theplayersPos(j("id").toString()).setImage(new Image("static/charstand.png"))
            }
            theplayersPos(j("id").toString()).setLayoutX(j("x").as[Double] * 32)
            theplayersPos(j("id").toString()).setLayoutY(j("y").as[Double] * 32)
          }
        } else {
          Platform.runLater {
            surprise.setLayoutX(j("x").as[Double] * 32)
            surprise.setLayoutY(j("y").as[Double] * 32)
            theplayersPos += (j("id").toString() -> surprise)
          }
          //          var gameImage: Image = new Image("static/charstand.png")
          //          playergc.drawImage(gameImage, j("x").as[Int]*32, j("y").as[Int]*32)
        }
      }
      Platform.runLater {
        if (theplayersPos.size > 0) {
          for ((k, v) <- theplayersPos) {
            if (playerList.children.contains(v)) {

            } else {
              playerList.children.add(v)

            }
          }
        }
        for ((k, v) <- theplayersPos) {
          if (checkers(players, k)) {

          } else {
            playerList.children.remove(v)
          }
        }
      }
    }
  }
  def checkers(players: List[Map[String, JsValue]], Idd: String): Boolean ={
      for(jj <- players){
        if(jj("id").toString() == Idd){
          return true
        }
      }
    return false
  }
  def checking(themap: Map[String,JsValue]): Boolean ={
      for((k,v) <- theplayersPos){
        if(themap("id").toString() == k){
          return true
        }
      }
    return false
  }

  def keyPressed(keyCode: KeyCode): Unit = {
    keyCode.getName match {
      case "W" =>
        var keyss: Map[String, Boolean] = Map("w" -> true, "s" -> false, "a" -> false, "d" ->  false)
        socket.emit("keyStates", Json.stringify(Json.toJson(keyss)))
      case "A" => var keyss: Map[String, Boolean] = Map("w" -> false, "s" -> false, "a" -> true, "d" ->  false)
        socket.emit("keyStates", Json.stringify(Json.toJson(keyss)))
      case "S" => var keyss: Map[String, Boolean] = Map("w" -> false, "s" -> true, "a" -> false, "d" ->  false)
        socket.emit("keyStates", Json.stringify(Json.toJson(keyss)))
      case "D" => var keyss: Map[String, Boolean] = Map("w" -> false, "s" -> false, "a" -> false, "d" ->  true)
        socket.emit("keyStates", Json.stringify(Json.toJson(keyss)))
      case "F" => socket.emit("tagKey", Json.stringify(Json.toJson(true)))
    }
  }
  val update: Long => Unit = (time: Long) => {

  }


}
