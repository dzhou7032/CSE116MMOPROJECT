import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.{Group, Scene}
import scalafx.scene.paint.Color._
import scalafx.scene.shape.Rectangle
import scalafx.scene.image.Image
import scalafx.scene.image.ImageView
import scalafx.scene.canvas.{Canvas, GraphicsContext}
import scalafx.scene.layout.Pane
import scalafx.scene.paint.Color.Green
import java.awt.{BasicStroke, Color, Graphics2D}
import java.awt.geom._
import java.io.{File, FileInputStream}
import java.util.Properties

import DeadmanPlayground.Types.{Player, World}
import javafx.scene.input.{KeyCode, KeyEvent}
import scalafx.animation.AnimationTimer

object FrontEnd extends JFXApp {
  var hpForNow = 100.0
  var hpEnemy = 100.0

  var thePlayer = new Player(List(1,1), List(-1,0), 100,0)
  var player2 = new Player(List(5,5), List(-1,0), 100, 0)
  var thisWorld = new World(Map(0->thePlayer, 1->player2))

  val gameCanvas = new Canvas(320,320)
  val gc = gameCanvas.graphicsContext2D

  var CharImage = new Image("charstand.png")
  var CharImageV = new ImageView(CharImage)
  var EnemyImage = new Image("charstand.png")
  var EnemyImageV = new ImageView(EnemyImage)


  var BulletsUp: Group = new Group {}
  var BulletsRight: Group = new Group {}
  var BulletsLeft: Group = new Group {}
  var BulletsDown: Group = new Group {}

  var hpRect = new Rectangle(){
    fill = Gray
  }
  var chpRect = new Rectangle(){
    fill = Red
  }
  var playeronehp = new Rectangle(){
    fill = Red
  }
  var playertwohp = new Rectangle(){
    fill = Red
  }

  def keyPressed(keyCode: KeyCode): Unit = {
    keyCode.getName match {
      case "Q" => firebullet(CharImageV)
      case "W" => thePlayer.move("w", thisWorld)//CharImageV.setLayoutY(CharImageV.getLayoutY() - 32)
        CharImageV.setRotate(0)
      case "A" => thePlayer.move("a", thisWorld)//CharImageV.setLayoutX(CharImageV.getLayoutX() - 32)
        CharImageV.setRotate(270)
      case "S" => thePlayer.move("s", thisWorld)//CharImageV.setLayoutY(CharImageV.getLayoutY() + 32)
        CharImageV.setRotate(180)
      case "D" => thePlayer.move("d", thisWorld)//CharImageV.setLayoutX(CharImageV.getLayoutX() + 32)
        CharImageV.setRotate(90)

      case "U" => firebullet(EnemyImageV)
      case "I" => player2.move("w", thisWorld)//CharImageV.setLayoutY(CharImageV.getLayoutY() - 32)
        EnemyImageV.setRotate(0)
      case "J" => player2.move("a", thisWorld)//CharImageV.setLayoutX(CharImageV.getLayoutX() - 32)
        EnemyImageV.setRotate(270)
      case "K" => player2.move("s", thisWorld)//CharImageV.setLayoutY(CharImageV.getLayoutY() + 32)
        EnemyImageV.setRotate(180)
      case "L" => player2.move("d", thisWorld)//CharImageV.setLayoutX(CharImageV.getLayoutX() + 32)
        EnemyImageV.setRotate(90)

      case _ => println(keyCode.getName + " pressed with no action")
    }
  }
  def firebullet(playershoot: ImageView): Unit ={
    var Bullets = new Image("bullets.png")
    var BulletV = new ImageView(Bullets)
    BulletV.setLayoutX(playershoot.getLayoutX())
    BulletV.setLayoutY(playershoot.getLayoutY())
    if(playershoot.getRotate == 0){
      BulletsUp.children.add(BulletV)
      print("dasf")
    }
    if(playershoot.getRotate == 90){
      BulletsRight.children.add(BulletV)
    }
    if(playershoot.getRotate == 180){
      BulletsDown.children.add(BulletV)
    }
    if(playershoot.getRotate == 270){
      BulletsLeft.children.add(BulletV)
    }
  }

  stage = new JFXApp.PrimaryStage {
    title.value = "Hartloff Fucking Hitler's Ass"
    width = 600
    height = 450
    scene = new Scene {
      hpRect.x = 0
      hpRect.y = 325
      hpRect.width = 240
      hpRect.height = 40

      chpRect.x = 5
      chpRect.y = 330
      chpRect.height = 30

      playeronehp.height = 5
      playertwohp.height = 5

      addEventHandler(KeyEvent.KEY_PRESSED, (event: KeyEvent) => keyPressed(event.getCode))
      fill = Green
      for(tr <- 0 to 9) {
        for (tc <- 0 to 9) {

          var gameImage: Image = new Image("floor.png")
          gc.drawImage(gameImage, tc * 32, tr * 32, 32, 32)
        }
      }
      for(trw <- 0 to 9){
        var gameImage: Image = new Image("walltile.png")
        gc.drawImage(gameImage, trw*32, 0, 32, 32)
      }
      for(brw <- 0 to 9){
        var gameImage: Image = new Image("walltile.png")
        gc.drawImage(gameImage, brw*32, 288, 32, 32)
      }
      for(lrw <- 0 to 9){
        var gameImage: Image = new Image("walltile.png")
        gc.drawImage(gameImage, 0, lrw*32, 32, 32)
      }
      for(rrw <- 0 to 9){
        var gameImage: Image = new Image("walltile.png")
        gc.drawImage(gameImage, 288, rrw*32, 32, 32)
      }
      content = List(gameCanvas, CharImageV, BulletsUp, BulletsRight, BulletsLeft, BulletsDown, EnemyImageV, hpRect, chpRect, playeronehp, playertwohp)
    }
    val update: Long => Unit = (time: Long) => {
      for (shape <- (BulletsUp.children).reverse) {
        shape.setLayoutY(shape.getLayoutY() - 5)
        if(shape.getLayoutY() > 0 && shape.getLayoutY() < 6){
          hpForNow -= 10
        }
        if(shape.getLayoutY < 0){
          BulletsUp.children.remove(shape)
        }
      }
      for (shape <- BulletsRight.children) {
        shape.setLayoutX(shape.getLayoutX() + 5)
      }
      for (shape <- BulletsDown.children) {
        shape.setLayoutY(shape.getLayoutY() + 5)
      }
      for (shape <- BulletsLeft.children) {
        shape.setLayoutX(shape.getLayoutX() - 5)
      }
      CharImageV.setLayoutY(thePlayer.coordinate(0) *32)
      CharImageV.setLayoutX(thePlayer.coordinate(1) *32)
      playeronehp.y = thePlayer.coordinate(0)*32
      playeronehp.x = thePlayer.coordinate(1)*32
      playeronehp.width = (hpForNow/100)*32

      EnemyImageV.setLayoutY(player2.coordinate(0) *32)
      EnemyImageV.setLayoutX(player2.coordinate(1) *32)
      playertwohp.y = player2.coordinate(0)*32
      playertwohp.x = player2.coordinate(1)*32
      playertwohp.width = (hpEnemy/100)*32

      chpRect.width = (hpForNow/100)*230
    }
    // Start Animations. Calls update 60 times per second (takes update as an argument)
    AnimationTimer(update).start()
  }


}