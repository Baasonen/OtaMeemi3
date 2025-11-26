package OtaMeemi
import scala.collection.mutable.Map
import scala.util.Random

class Player(gw: GameWorld):

  private val rng = Random()
  private var currentLocation = gw.getAreas(1)

  private var money = 5
  private var quitCommandGiven = false
  private val items = Map[String,Item]()
  private var debuffs = Vector[Debuff]()

  def activeDebuffs = debuffs

  def setNewLocation(newLoc: Area) =
    currentLocation = newLoc

  def getMoneyStatus = money

  def removeMoney(ammount: Int) = money -= ammount

  def addDebuff(debuff: Debuff) =
    debuffs = debuffs.appended(debuff)

  def hasQuit = this.quitCommandGiven

  def location = this.currentLocation
  
  def isatAbloc = this.currentLocation == gw.abloc

  def hasItem(itemName: String): Boolean =
    val invVec = items.keys.toVector
    invVec.map(_.toLowerCase).contains(itemName.toLowerCase)

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

  def removeDebuffs() =
    debuffs = Vector[Debuff]()

  def go(destination: String): String =
    val rng = Random()
    val vect = Vector(gw.klahtimetro,gw.sornainen)
    val connections = currentLocation.getConnections
    print(debuffs)
    if (debuffs.forall(_.isActive(gw.getRawTime)) && debuffs.nonEmpty) then
      setNewLocation(vect(rng.between(0,2)))
      gw.setTime((23*60)+59)
      removeDebuffs()
      "Mitemn.,,, tämne pädyin,. eo ole tekariklä"

    else if connections.map(_._1.toString.toLowerCase).contains(destination) then
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
          currentLocation = gw.getAreas(1)
          "Aamusi alkaa A blocilta, kello on 8.15, nyt on kiire luennolle. Tänään pitää ehtiä myös pajalla saattamaan projekti loppuun. Elämänohjeet kannattaa ottaa maasta ja lukea. (kyllä, peli alkaa alusta)"
      else
        s"Uh Oh, you don't know how to travel to ${destination} from here"

  def rest() =
    gw.passTime(120)
    "You rest for a while. Better get a move on, though."

  def fish() =
    object lohi extends Item("Lohi", "Kallis kala",100,1):
      override def eat(player: Player): String = 
        player.removeItem("Lohi")
        "Tämä olisi kannattanut myydä"
      override def combine(player: Player, combineWith: Item): String = "Äläs nyt"
      override def use(player: Player): String = "Laitoit kalan taskuun"

    object silakka extends Item("Silakka", "Vähän halvempi kala kuin lohi",10,1):
      override def eat(player: Player): String = 
        player.removeItem("Silakka")
        "Tämä olisi kannattanut myydä"
      override def combine(player: Player, combineWith: Item): String = "Äläs nyt"
      override def use(player: Player): String = "Laitoit kalan taskuun"

    object kalapuikko extends Item("Kalapuikko", "Kouluruokalan klassikko",3,1):
      override def eat(player: Player): String = 
        player.removeItem("Kalapuikko")
        "Tämä olisi kannattanut myydä"
      override def combine(player: Player, combineWith: Item): String = "Äläs nyt"
      override def use(player: Player): String = "Laitoit kalapuikon taskuun"

    val kalat = Vector[Item](lohi,silakka,kalapuikko)
    if this.location != gw.otaranta then
      "täällä ei voi kalastaa(otaranta bro)"
    else if !this.inventory.contains("matopurkki") then
      "Hommaa matopurkki bro, näit saa alepast"
    else
      if this.hasItem("Lohi") && this.hasItem("Silakka") && this.hasItem("Kalapuikko") then
        this.removeItem("matopurkki")
        "Madot loppu kesken, oisko mitään kävästä nopee alepas"
      else
        val instanssi = rng.between(0,3)
        val saalis = kalat(instanssi)
        this.addItem(saalis)
        "Sait kalaa"

        
        

  def sus() =
    currentLocation = gw.getAreas(0)
    "STOP POSTING ABOUT AMONG US! I'M TIRED OF SEEING IT! My friends on TikTok send me memes, on Discord its fucking memes. I was in a server, right, and ALL the channels are just Among Us stuff. I showed my Champion underwear to my girlfriend, and the logo I flipped it and I said Hey babe, when the underwear sus! HAHA! Ding Ding Ding Ding Ding Ding Ding DiDiDing! I fucking looked at a trash can and I said Thats a bit sussy! I looked at my penis, I thought of the astronauts helmet and I go PENIS? MORE LIKE PEN-SUS! AAAAAAAAAAAAAA"

  def eatItem(itemName: String): String =
    val temp = this.items(itemName)
    if hasItem(itemName) then temp.eat(this)
    else "Söit lautasellisen tyhjää. Ei tällä oikein nälkä lähde."

  def combineItems(args: String): String =
    val itemsToCombine = args.split(" ")
    println(itemsToCombine.mkString(", "))
    if itemsToCombine.length == 2 then
      if hasItem(itemsToCombine(0)) && hasItem(itemsToCombine(1)) then
        val combString = items(itemsToCombine(0)).combine(this, items(itemsToCombine(1)))
        
        combString
      else
        "Sinulla ei ole noita itemejä"
    else
      "Voit yhdistää vain kahta itemiä kerrallaan"

  def takeItem(itemToTake: String): String =
    if location.getItems.map(_.toString.toLowerCase).contains(itemToTake) then
      addItem(location.getItems(location.getItems.map(_.toString.toLowerCase).indexOf(itemToTake)))
      location.removeItem(itemToTake)
      s"Löysit ${itemToTake}"
    else
      "Ei täällä näytä olevan tollasta"

  def trade(itemToTrade: String) =
    if location.isTradingAllowed then
      if hasItem(itemToTrade) then
        money += items(itemToTrade).getValue.toInt
        removeItem(itemToTrade)
        s"Sinne meni ${itemToTrade}"
      else
        s"Sul ei oo ${itemToTrade}..."
    else
      "Et sä pysyty tääl myymään (harkitse alepaan menemistä)"

  def buy(itemToBuy: String): String =
    if this.location == gw.alepa then
      object bait extends Item("matopurkki", "Matoja. Eiköhän otarannasta nappaa jotain näillä", 1, 1):
        override def eat(player: Player): String = "Hyi"
        override def combine(player: Player, combineWith: Item): String = "Spagu madoilla? Hell nah."
        override def use(player: Player): String = "No can do"

      object gtx760 extends Item("GTX760", "Aika tykki vekotin (älä syö)", 200, 10):
        override def eat(player: Player): String =
          player.removeItem("gtx760")
          "Rousk rousk (nam)"
        override def combine(player: Player, combineWith: Item): String = "Et ole elektroniikkainsinööri"
        override def use(player: Player): String =
          if location.getActiveEvents(player).map(_.toString.toLowerCase).contains("ttalobossi") then
            if items.contains("työtarjous") then
                player.location.getActiveEvents(player).filter(_.toString == "ttalobossi").head.setActivated(true)
                player.removeItem("työtarjous")
                player.removeItem("gtx760")
                player.setNewLocation(gw.tuas)
                "Oho, hänhän innostui työpaikasta sekä näytönohjaimesta ja juoksi pois. Voit nyt jatkaa matkaa pajalle"
            else
              "Ehkä vielä työpaikka kaupan päälle auttaisi"
          else
            "Ei tarvii, omassa koneessa 5090 #köyhät ulisee"

      if itemToBuy == "matopurkki" then
        if money >= 1 then
          addItem(bait)
          removeMoney(1)
          "Ostit matopurkin"
        else
          "Ei ole varaa :("
      else if itemToBuy.toLowerCase == "gtx760" then
        if money >= 420 then
          addItem(gtx760)
          removeMoney(399)
          "Ostis GTX760"
        else
          "Ei ole varaa :("
      else
        "Ei alepa myy tollasta"
    else
      "Ei täältä voi ostaa mitään"
      
  def read(input: String)=
    if input == "elämänohjeita" then 
      useItem(input)
    else
      "ei tätä voi lukee"

  def quit() =
    this.quitCommandGiven = true
    ""
  override def toString = "Now at: " + this.location.toString

  object puhelin extends Item("Puhelin", "Kyl sä tiiät (ip 17 pro max btw)", 2000, 1):
    override def use(player: Player): String =
      player.currentLocation match
        case taafa         => "Kantsii syödä mut silleen ei tästä paikast saa muuta kun loree, Dipoli tai Rantasauna vois olla hyviä paikkoja mennä"
        case smokki        => "Et pääse tänne kuitenkaan sisälle, mene uuniin eiku Rantsulle"
        case sus            => "Googlaaminen ei auta, jos päädyit tänne olet muutenkin liikaa puhelimella bro"
        case ok20          => "Kannattaa tutkia x 5"
        case dipoli        => "Täältä saa muutakin kuin skumppaa ja kontakteja. Kantsii pitää linkedIn auki"
        case sornainen     => "Mene ylös piritorille"
        case rantasauna    => "Kalasta otarannas bro, idk what to tell you man"
        case klahtimetro   => "Mee vaan pois"
        case narnia        => ")&/=)=)(?=)(??(=)==/=(/"
        case abloc         => "brr brr patapim six seven"
        case kandi         => "Mene luennolle, siellä on hyödyllistä tekemistä"
        case tuas          => "Voitit periaatteessa pelin, mene pajalle, siellä riittää hommaa."
        case ttalo         => "Tää bossi ei päästä sua ohi"
        case otaranta      => "Kalasta bro"
        case piritori      => "Ei ehitty lisään tähän mitään"
        case dipoliravintola     => "Onko nälkä, lataa tuudo."
        case ablocmetro         => "Mene himaan, eiku venaa et pysty"
        case taafalunch         => "Spagu bro"
        case alepa         => "matoi spagun kaa bro"
        case _               => ""

    override def combine(player: Player, combineWith: Item): String = "Et osaa yhdistää tätä muuhun kuin wifiin"

    override def eat(player: Player): String = "bro what💀"

  object kuulokkeet extends Item("Kuulokkeet", "Ze blutuuth divais is redi to pair", 50, 1):
    override def use(player: Player): String = "Ei pysty ny"

    override def combine(player: Player, combineWith: Item): String =
      if combineWith == puhelin then
        "Ze blutuuth devais is konnektedt uhh sukcesfuli"
      else
        "Ei oo wifii..."

    override def eat(player: Player): String = "huh💀"

  object kolikoita extends Item("Muutama kolikko", " ", 1, 1):
    override def use(player: Player): String = "Ei näil saa ees redbull"

    override def combine(player: Player, combineWith: Item): String = "Nuh uh"

    override def eat(player: Player): String = "ei näit voi syödä"



        
      
  addItem(kolikoita)
  addItem(puhelin)
  addItem(kuulokkeet)
end Player
