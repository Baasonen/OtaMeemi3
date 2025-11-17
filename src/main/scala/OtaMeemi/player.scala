package OtaMeemi
import scala.collection.mutable.Map

class Player(gw: GameWorld):

  private var currentLocation = gw.getAreas.head
  private var quitCommandGiven = false
  private val items = Map[String,Item]()
  private var debuffs = Vector[Debuff]()

  def activeDebuffs = debuffs

  def addDebuff(debuff: Debuff) =
    debuffs = debuffs.appended(debuff)

  def hasQuit = this.quitCommandGiven

  def location = this.currentLocation

  def hasItem(itemName: String): Boolean =
    items.keys.toVector.contains(itemName)

  def drop(itemName: String): String =
    if hasItem(itemName) then
      items.remove(itemName)
      s"You dropped ${itemName}"
    else
      s"You don't have ${itemName}"

  def removeItem(itemName: String): Boolean =
    if hasItem(itemName) then
      items.remove(itemName)
      true
    else
      false

  def get(itemName: String): Option[String] =
    if (this.location.availableItems.map(_.toString).contains(itemName)) then
      val itemToPick = location.items(location.availableItems.map(_.toString).indexOf(itemName))
      items.addOne(itemToPick.toString, itemToPick)
      Some(s"Picked up ${itemToPick.toString}")
    else
      None

  def examine(itemName:String): String =
    if hasItem(itemName) then
      items(itemName).examine()
    else
      s"You don't have ${itemName}"

  def inventory: Vector[String] =
    items.keys.toVector

  def movementOptions: Vector[String] =
    currentLocation.neighbors.map(_.toString)

  def go(destination: String) =
    if currentLocation.neighbors.map(_._1.toString).contains(destination) then
      currentLocation = currentLocation.neighbors(currentLocation.neighbors.map(_._1.toString).indexOf(destination))._1
      s"You travel to ${currentLocation.toString}\n \n ${currentLocation.description}"
    else
      s"Uh Oh, you don't know how to travel to ${destination} from here"

  def rest() =
    gw.passTime(120)
    "You rest for a while. Better get a move on, though."


  def quit() =
    this.quitCommandGiven = true
    ""
  override def toString = "Now at: " + this.location.toString

end Player
