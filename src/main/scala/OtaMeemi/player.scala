package OtaMeemi
import scala.collection.mutable.Map

class Player(gw: GameWorld):


  private var currentLocation = gw.getAreas(10)


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

  def addItem(item: Item) =
    items.addOne(item.toString.toLowerCase, item)

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

  def useItem(itemName: String): String =
    if hasItem(itemName) then
      items(itemName).use(this)
    else
      "Ei sul oo tollast bro"

  def examineItem(itemName:String): String =
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
      if destination == "narnia" then 
        gw.setTime((23*60)+59) 
        currentLocation = gw.narnia
        "Menit narniaan. Päiväsi päättyy tähän"
      else
        val destinationArea = connections(connections.map(_._1.toString.toLowerCase).indexOf(destination))._1
        val timeToPass = connections(connections.map(_._1.toString.toLowerCase).indexOf(destination))._2
        if gw.passTime(timeToPass) then
          currentLocation = destinationArea
          s"You travel to ${currentLocation.toString}, the time is ${gw.getTime}\n \n ${currentLocation.initialDescription}"
        else
          currentLocation = gw.getAreas(10)
          "It is getting late, you fall a sleep and wake up am METROASEMAAAA"
      else
        s"Uh Oh, you don't know how to travel to ${destination} from here"

  def rest() =
    gw.passTime(120)
    "You rest for a while. Better get a move on, though."

  def sus() =
    currentLocation = gw.getAreas(0)
    ""
  
  def quit() =
    this.quitCommandGiven = true
    ""
  override def toString = "Now at: " + this.location.toString

  object puhelin extends Item("Puhelin", "Kyl sä tiiät (ip 17 pro max btw)", 2000, 1):
    override def use(player: Player): String = "Kelasta päivää..."

  object kuulokkeet extends Item("Sony whqxmqxms69420", "Hemo päheet", 50, 1):
    override def use(player: Player): String = "Ei pysty ny"

  object kolikoita extends Item("Muutama kolikko", " ", 1, 1):
    override def use(player: Player): String = "Ei näil saa ees redbull"

  addItem(kolikoita)
  addItem(puhelin)
  addItem(kuulokkeet)

end Player
