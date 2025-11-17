package OtaMeemi
import scala.collection.mutable.Map

class Player(startingArea: Area):

  private var currentLocation = startingArea
  private var quitCommandGiven = false
  private val items = Map[String,Item]()

  def hasQuit = this.quitCommandGiven


  def location = this.currentLocation

  def has(itemName: String): Boolean = this.items.contains(itemName)

  def drop(itemName: String): String =

  def get(itemName: String): String =


  def examine(itemName:String):String =

  def inventory: String =

  def go(direction: String) =

  def rest() = "You rest for a while. Better get a move on, though."

  def quit() =
    this.quitCommandGiven = true
    ""
  override def toString = "Now at: " + this.location.toString

end Player
