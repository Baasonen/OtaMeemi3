package OtaMeemi
import scala.collection.mutable.Map

class Player(gw: GameWorld):


  private var currentLocation = gw.getAreas(9)


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

  //def get(itemName: String): Option[String] =
    //if (this.location.availableItems.map(_.toString).contains(itemName)) then
    //  val itemToPick = location.items(location.availableItems.map(_.toString).indexOf(itemName))
    //  items.addOne(itemToPick.toString, itemToPick)
    //  Some(s"Picked up ${itemToPick.toString}")
    //else
   //   None

  def examine(itemName:String): String =
    if hasItem(itemName) then
      items(itemName).examine()
    else
      s"You don't have ${itemName}"

  def inventory: Vector[String] =
    items.keys.toVector

  def movementOptions: Vector[String] =
    currentLocation.getConnections.map(_._1.toString)

  def go(destination: String) =
    val connections = currentLocation.getConnections
    print(connections)
    if connections.map(_._1.toString.toLowerCase).contains(destination) then
      val destinationArea = connections(connections.map(_._1.toString.toLowerCase).indexOf(destination))._1
      val timeToPass = connections(connections.map(_._1.toString.toLowerCase).indexOf(destination))._2
      if gw.passTime(timeToPass) then
        currentLocation = destinationArea
        s"You travel to ${currentLocation.toString}, the time is ${gw.getTime}\n \n ${currentLocation.initialDescription}"
      else
        currentLocation = gw.getAreas(9)
        "It is getting late, you fall a sleep and wake up am METROASEMAAAA"
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
