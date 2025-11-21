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
    "STOP POSTING ABOUT AMONG US! I'M TIRED OF SEEING IT! My friends on TikTok send me memes, on Discord its fucking memes. I was in a server, right, and ALL the channels are just Among Us stuff. I showed my Champion underwear to my girlfriend, and the logo I flipped it and I said Hey babe, when the underwear sus! HAHA! Ding Ding Ding Ding Ding Ding Ding DiDiDing! I fucking looked at a trash can and I said Thats a bit sussy! I looked at my penis, I thought of the astronauts helmet and I go PENIS? MORE LIKE PEN-SUS! AAAAAAAAAAAAAA"

  def combineItems(args: String) =
    val itemsToCombine = args.split(" ")
    println(itemsToCombine.mkString(", "))
    if itemsToCombine.length == 2 then
      if hasItem(itemsToCombine(0)) && hasItem(itemsToCombine(1)) then
        items(itemsToCombine(0)).combine(this, items(itemsToCombine(1)))
      else
        "Sinulla ei ole noita itemejä"
    else
      "Voit yhdistää vain kahta itemiä kerrallaan"


  def quit() =
    this.quitCommandGiven = true
    ""
  override def toString = "Now at: " + this.location.toString

  object puhelin extends Item("Puhelin", "Kyl sä tiiät (ip 17 pro max btw)", 2000, 1):
    override def use(player: Player): String = "Kelasta päivää..."

    override def combine(player: Player, combineWith: Item): String = "Et osaa yhdistää tätä muuhun kuin wifiin"

  object kuulokkeet extends Item("Kuulokkeet", "Hemo päheet", 50, 1):
    override def use(player: Player): String = "Ei pysty ny"

    override def combine(player: Player, combineWith: Item): String =
      if combineWith == puhelin then
        "Ze blutuuth devais is konnektedt"
      else
        "Ei oo wifii..."

  object kolikoita extends Item("Muutama kolikko", " ", 1, 1):
    override def use(player: Player): String = "Ei näil saa ees redbull"

    override def combine(player: Player, combineWith: Item): String = "Nuh uh"

  addItem(kolikoita)
  addItem(puhelin)
  addItem(kuulokkeet)

end Player
