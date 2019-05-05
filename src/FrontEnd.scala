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

import DeadmanPlayground.Types.{Bullet, Player, World}
import javafx.scene.input.{KeyCode, KeyEvent}
import scalafx.animation.AnimationTimer
import scalafx.scene.control.TextField
import scalafx.scene.text.Font

object FrontEnd extends JFXApp {
  var hpForNow = 100.0
  var hpEnemy = 100.0

  var player1 = new Player(1, List(1,1), List(-1,0), 100,0, true, false, bulletOwnership = List())
  var player2 = new Player(2, List(2,2), List(-1,0), 100, 0, true, false, bulletOwnership = List())
  var thisWorld = new World(Map(0->player1, 1->player2))

  val gameCanvas = new Canvas(320,320)
  val gc = gameCanvas.graphicsContext2D

  var CharImage = new Image("charstand.png")
  var playerOneImage = new ImageView(CharImage)
  var EnemyImage = new Image("charstand.png")
  var playerTwoImage = new ImageView(EnemyImage)

  var BulletsUp: Group = new Group {}
  var BulletsRight: Group = new Group {}
  var BulletsLeft: Group = new Group {}
  var BulletsDown: Group = new Group {}
  var BulletList: Group = new Group{}

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
      case "Q" => firebullet(playerOneImage, player1)
      case "W" => player1.move("w", thisWorld)//CharImageV.setLayoutY(CharImageV.getLayoutY() - 32)
        playerOneImage.setRotate(0)
      case "A" => player1.move("a", thisWorld)//CharImageV.setLayoutX(CharImageV.getLayoutX() - 32)
        playerOneImage.setRotate(270)
      case "S" => player1.move("s", thisWorld)//CharImageV.setLayoutY(CharImageV.getLayoutY() + 32)
        playerOneImage.setRotate(180)
      case "D" => player1.move("d", thisWorld)//CharImageV.setLayoutX(CharImageV.getLayoutX() + 32)
        playerOneImage.setRotate(90)
      case "E" => reloadf(player1)

      case "U" => firebullet(playerTwoImage, player2)
      case "I" => player2.move("w", thisWorld)//CharImageV.setLayoutY(CharImageV.getLayoutY() - 32)
        playerTwoImage.setRotate(0)
      case "J" => player2.move("a", thisWorld)//CharImageV.setLayoutX(CharImageV.getLayoutX() - 32)
        playerTwoImage.setRotate(270)
      case "K" => player2.move("s", thisWorld)//CharImageV.setLayoutY(CharImageV.getLayoutY() + 32)
        playerTwoImage.setRotate(180)
      case "L" => player2.move("d", thisWorld)//CharImageV.setLayoutX(CharImageV.getLayoutX() + 32)
        playerTwoImage.setRotate(90)
      case "O" => reloadf(player2)
      case _ => println(keyCode.getName + " pressed with no action")
    }
  }
  def reloadf(player:Player): Unit={
    player.reload()
    var bulletty  = new Image("bullets.png")
    for(xd <- 1 to player1.ammo){
      var buleet = new ImageView(bulletty)
      buleet.setLayoutX((32*xd)-32)
      buleet.setLayoutY(380)
      BulletList.children.add(buleet)
    }
  }
  def firebullet(playershoot: ImageView, player: Player): Unit ={
    if(player.shoot() == false){
      return
    }
    BulletList.children.remove(0,BulletList.children.size())
    var bulletty  = new Image("bullets.png")
    for(xd <- 1 to player1.ammo){
      var buleet = new ImageView(bulletty)
      buleet.setLayoutX((32*xd)-32)
      buleet.setLayoutY(380)
      BulletList.children.add(buleet)
    }
    var Bullets = new Image("bullets.png")
    var BulletV = new ImageView(Bullets)
    BulletV.setLayoutX(playershoot.getLayoutX())
    BulletV.setLayoutY(playershoot.getLayoutY())
    if(playershoot == playerOneImage){
      if(playershoot.getRotate == 0){
        BulletV.id="0"
        //player1.bulletOwnership = player1.bulletOwnership :+ new Bullet(this.player1.coordinate, this.player1, this.player1.walkable)
        BulletsUp.children.add(BulletV)
        print("dasf")
      }
      if(playershoot.getRotate == 90){
        BulletV.id="0"
        //player1.bulletOwnership = player1.bulletOwnership :+ new Bullet(this.player1.coordinate, this.player1, this.player1.walkable)
        BulletsRight.children.add(BulletV)
      }
      if(playershoot.getRotate == 180){
        BulletV.id="0"
        //player1.bulletOwnership = player1.bulletOwnership :+ new Bullet(this.player1.coordinate, this.player1, this.player1.walkable)
        BulletsDown.children.add(BulletV)
      }
      if(playershoot.getRotate == 270){
        BulletV.id="0"
        //player1.bulletOwnership = player1.bulletOwnership :+ new Bullet(this.player1.coordinate, this.player1, this.player1.walkable)
        BulletsLeft.children.add(BulletV)
      }
    }
    else{
      if(playershoot.getRotate == 0){
        BulletV.id="1"
        //player2.bulletOwnership = player2.bulletOwnership :+ new Bullet(this.player2.coordinate, this.player2, this.player2.walkable)
        BulletsUp.children.add(BulletV)
        print("dasf")
      }
      if(playershoot.getRotate == 90){
        BulletV.id="1"
        //player2.bulletOwnership = player2.bulletOwnership :+ new Bullet(this.player2.coordinate, this.player2, this.player2.walkable)
        BulletsRight.children.add(BulletV)
      }
      if(playershoot.getRotate == 180){
        BulletV.id="1"
        //player2.bulletOwnership = player2.bulletOwnership :+ new Bullet(this.player2.coordinate, this.player2, this.player2.walkable)
        BulletsDown.children.add(BulletV)
      }
      if(playershoot.getRotate == 270){
        BulletV.id="1"
        //player2.bulletOwnership = player2.bulletOwnership :+ new Bullet(this.player2.coordinate, this.player2, this.player2.walkable)
        BulletsLeft.children.add(BulletV)
      }
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

      var bulletty  = new Image("bullets.png")
      for(xd <- 1 to player1.ammo){
        var buleet = new ImageView(bulletty)
        buleet.setLayoutX((32*xd)-32)
        buleet.setLayoutY(380)
        BulletList.children.add(buleet)
      }

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
      content = List(gameCanvas, playerOneImage, BulletsUp, BulletsRight, BulletsLeft, BulletsDown, playerTwoImage, hpRect, chpRect, playeronehp, playertwohp, BulletList)
    }
    val update: Long => Unit = (time: Long) => {
      for (shape <- (BulletsUp.children).reverse) {
        shape.setLayoutY(shape.getLayoutY() - 5)
        for((k,v) <- thisWorld.players){
          if(shape.getLayoutY() > v.coordinate(0)*32 && shape.getLayoutY() < (v.coordinate(0)*32)+6 ){
            if(shape.getLayoutX() == v.coordinate(1)*32){
              thisWorld.players(shape.getId().toInt).playerHit(v)
              BulletsUp.children.remove(shape)
            }
          }
        }
        if(shape.getLayoutY() < 32){
          BulletsUp.children.remove(shape)
        }
      }
      for (shape <- (BulletsRight.children).reverse) {
        shape.setLayoutX(shape.getLayoutX() + 5)
        for((k,v) <- thisWorld.players){
          if(v.ID != thisWorld.players(shape.getId().toInt).ID)
          if(shape.getLayoutX() > v.coordinate(1)*32 && shape.getLayoutX() < (v.coordinate(1)*32)+6 ){
            if(shape.getLayoutY() == v.coordinate(0)*32){
              thisWorld.players(shape.getId().toInt).playerHit(v)
              BulletsRight.children.remove(shape)
            }
          }
        }
        if(shape.getLayoutX() > 256){
          BulletsRight.children.remove(shape)
        }
      }
      for (shape <- (BulletsDown.children).reverse) {
        shape.setLayoutY(shape.getLayoutY() + 5)
        for((k,v) <- thisWorld.players){
          if(v.ID != thisWorld.players(shape.getId().toInt).ID)
          if(shape.getLayoutY() > v.coordinate(0)*32 && shape.getLayoutY() < (v.coordinate(0)*32)+6 ){
            if(shape.getLayoutX() == v.coordinate(1)*32){
              thisWorld.players(shape.getId().toInt).playerHit(v)

              BulletsDown.children.remove(shape)
            }
          }
        }
        if(shape.getLayoutY() > 256){
          BulletsDown.children.remove(shape)
        }
      }
      for (shape <- (BulletsLeft.children).reverse) {
        shape.setLayoutX(shape.getLayoutX() - 5)
        for((k,v) <- thisWorld.players){
          if(shape.getLayoutX() > v.coordinate(1)*32 && shape.getLayoutX() < (v.coordinate(1)*32)+6 ){
            if(shape.getLayoutY() == v.coordinate(0)*32){
              thisWorld.players(shape.getId().toInt).playerHit(v)
              BulletsLeft.children.remove(shape)
            }
          }
        }
        if(shape.getLayoutX() < 32){
          BulletsLeft.children.remove(shape)
        }
      }
//      for((k,v) <- thisWorld.players){
//        if(v.alive == false){
//          thisWorld.players = thisWorld.players.-(k)
//        }
//      }
      playerOneImage.setLayoutY(player1.coordinate(0) *32)
      playerOneImage.setLayoutX(player1.coordinate(1) *32)
      playeronehp.y = player1.coordinate(0)*32
      playeronehp.x = player1.coordinate(1)*32
      playeronehp.width = (player1.health/100).toDouble*32

      playerTwoImage.setLayoutY(player2.coordinate(0) *32)
      playerTwoImage.setLayoutX(player2.coordinate(1) *32)
      playertwohp.y = player2.coordinate(0)*32
      playertwohp.x = player2.coordinate(1)*32
      playertwohp.width = (player2.health/100)*32

      chpRect.width = (player1.health/100)*230
    }
    // Start Animations. Calls update 60 times per second (takes update as an argument)
    AnimationTimer(update).start()
  }


}