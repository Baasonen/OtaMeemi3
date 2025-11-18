package OtaMeemi

import OtaMeemi.*
import scala.io.StdIn.*

/** The singleton object `AdventureTextUI` represents a fully text-based version of the
  * Adventure game application. The object serves as an entry point for the game, and
  * it can be run to start up a user interface that operates in the text console.
  * @see [[AdventureGUI]] */
object AdventureTextUI extends App:
  println("Start")
  private val game = OtaMeemiGame()
  println("Game Init")
  lazy val player = game.player
  println("GameINitSuccess")
  this.run()

  /** Runs the game. First, a welcome message is printed, then the player gets the chance to
    * play any number of turns until the game is over, and finally a goodbye message is printed. */
  private def run() =
    println("Run")
    println(this.game.welcomeMessage)
    println("AfterWelcome")
    while !this.game.isOver do
      println("PLayTurn")
      //this.printAreaInfo()
      this.playTurn()
    println("\n" + this.game.goodbyeMessage)


  /** Prints out a description of the player character’s current location, as seen by the character. */
  private def printAreaInfo() =
    println("AreaInfo")
    val area = this.player.location
    println("GotArea")
    println("\n\n" + area.toString)
    println("-" * area.toString.length)
    println(area.initialDescription + "\n")


  /** Requests a command from the player, plays a game turn accordingly, and prints out a
    * report of what happened.  */
  private def playTurn() =
    println("Turn")
    val command = readLine("Command: ")
    val turnReport = this.game.playTurn(command)
    if turnReport.nonEmpty then
      println(turnReport)

end AdventureTextUI

