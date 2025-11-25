package OtaMeemi
import scala.util.Random

class GameWorld:

  // ALUEIDEN KUVAUKSET (IHA HEMO SPAGETTI)
  val taafa     = new Area("Taafa", Vector(
                      "Saavut täffälle, betonibrutalismin kukkanen pistää silmään","Yrität mennä sisälle alaovesta, se on kiinni",
                      "Du försöker att komma in genom övre dörren men det är stängt. Tyvärr har du ingen nyckel för du är inte medlem av Teknologföreningen..."
                    ), Vector(), false)

  val smokki    = new Area("Smökki", Vector(
                      "Saavuit smökin pihaan, sisältä kuuluu musiikkia.",
                      "Käynnissä on ilmeisesti sikajuhlat, et pääse sisälle koska sinulla ei ole lippua",
                      "Yrität lahjoa portsarin, hän ei päästä sinua sisälle koska et ole tutalla",
                    ), Vector(), false)

  val ok20      = new Area("Ok20", Vector("Saavut Otakaari 20:n pihaan, yläovesta kuuluu musiikkia","Menet sisälle, käynnissä on stigulaatio. Tunnet itsesi ulkopuoliseksi koska et ole dokattu","Olet dokattu, valmistaudu hauskaan iltaan", "Varam kamnattaisi jatkam matka rmatasugewmaunaaa"), Vector(), false)
  val dipoli    = new Area("Dipoli", Vector(
                      "Saavut dipolille, frakkien määrän perusteella sisällä on meneillään jotain tärkeää",
                      "Kävelet sisään. Pöhinän seassa pystysi ehkä jopa verkostoitua",
                      "Pakenet paikalta ennenkuin tilanne pääsee eskaloitumaan"
                    ), Vector(), false)
  val dipoliravintola = new Area("Dipoli", Vector("Kiipeät yläkertaan syömään","Syöt ruokaa tavalliselta linjastolta"),Vector(), false)
  val sornainen   = new Area("Sörnäisten metroasema", Vector(
                      "Ilmeisesti sammuit metroon Stigulaation jälkeen ja päädyit Sörnäisiin."
                    ), Vector(), false)
  val piritori = new Area ("Piritori",Vector("Nouset ylös asemalta pahamaineiselle piritorille","Paikallinen diileri tarjoaa sinulle peukkua"),Vector(), false)
  val rantasauna  = new Area("Rantasauna", Vector("Menet rantasaunalle. Sauna on kylmä ja ovet ovat lukossa. Et kai vaan muistanut päivää väärin?",
    "Menet rantasaunalle, sisältä kuuluu puhetta.",
    "Menet sisälle, toivottavasti muistit saunakamat, sauna on nimittäin lämmin.",
    "Menet paljuun. Paljun nykyinen maksimikapasiteetti on *aikaisempi maksimi + 1* henkilöä.",
    "Menet uimaan. Vesi ei ole kovinkaan syvää mutta kylmä siinä tulee ja nopeasti."), Vector(), false)

  val klahtimetro = new Area("Kivenlahden metroasema", Vector("Sammuit stigulaatiossa, kiitä onneasi että päädyit tänne etkä katuojaan.","Päätepysäkki, täällä on hyvin vähän näkemisen arvoista"), Vector(), false)
  val narnia      = new Area("Narnia", Vector("Miten tässä näin kävi?","Löydät itsesi vaatekaapin toiselta puolelta taikamaailmasta","Suureksi harmiksesi huomaat oven sulkeutuneen takanasi. Eihän tämä näin mene siinä kirjassa."), Vector(), false)
  val sus      = new Area("Amogus", Vector("Placeholder"), Vector(), false)
  val abloc       = new Area("A Bloc", Vector("O Block mutta aallossa","Menet syömään. Valitse ruokapaikka: Konnichiwa, Kotkot, Poke bowl"), Vector(), true)
  val kandi       = new Area("Kandikeskus", Vector("Saavut kandikeskukselle (tuttavallisemmin kandilafka)","Menet A-saliin fysiikan luennolle ja huomaat, että se on lähes tyhjä. Vastuullista sakkia nämä opiskelijat.","Pyörit ympyrää Y-siivessä koska et tiedä missä Y208b on. (vinkki vitonen, se ei ole toisessa kerroksessa)"), Vector(), false)
  val tuas        = new Area("TUAS", Vector("Menet sähköpajalle, bambu on varattu seuraavat 5h55min. Projektisi runko on edelleen tulostamatta.","Väsäät projektia tunnin","ja toisen","ja kolmannen","Menet syömään, on keskiviikko eli tarjolla on lohta tartarkastikkeessa a 5.50€"), Vector(), false)
  val ttalo       = new Area("Tietotalo", Vector("Täällä asuvat eivät tunne suihkun käsitettä","Menet opiskelemaan kasvihuoneeseen"), Vector(), false)
  val otaranta    = new Area("Otaranta", Vector("Saavut otarantaan. Kylmä tuuli puhaltaa mereltä","Menet uimaan, vesi on kylmää (yllättyneet parijonoon)"), Vector(), false)
  val taafalunch = new Area("Taafan lounasravintola",Vector("Menet spagujonoon","Edelleen spagujonossa","Tässä voi kestää hetken","Saat 1kpl spagua"),Vector(), false)
  val ablocmetro = new Area ("Abloc metro",Vector("Menit metrolle. Minne matka?"),Vector(),false)
  val alepa = new Area ("Alepa", Vector("Menit alepaan, 2e redbull tarjous on voimassa"),Vector(),true)
  val sahkopaja = new Area ("Sähköpaja",Vector("Saavuit pajalle. GG."),Vector(),false)

  // ALUEIDEN YHTEYDET
  taafa.connections = Vector((kandi,5),(dipoli,2),(smokki,5),(taafalunch,1))
  smokki.connections = Vector((ok20,2))
  ok20.connections = Vector((kandi,10),(rantasauna,10))
  dipoli.connections = Vector((taafa,5),(kandi,10))
  sornainen.connections = Vector((piritori,2),(ablocmetro,20))
  rantasauna.connections = Vector((narnia,10),(ok20,10),(otaranta,10))
  narnia.connections = Vector((rantasauna,10))
  abloc.connections = Vector((kandi,2),(ttalo,10),(ablocmetro,1),(alepa,1))
  kandi.connections = Vector((abloc,20),(ok20,30),(taafa,30),(dipoli,20))
  tuas.connections = Vector((sahkopaja,1))
  ttalo.connections = Vector((abloc,10))
  otaranta.connections = Vector((rantasauna,10))
  piritori.connections = Vector((sornainen,2))
  taafalunch.connections = Vector((taafa,1))
  ablocmetro.connections = Vector((abloc,1))
  sus.connections = Vector((ttalo,1))
  alepa.connections = Vector((abloc,1))
  klahtimetro.connections = Vector((ablocmetro,40))
  private val areas =
    Vector[Area](sus, ablocmetro,abloc,taafa,smokki,ok20,dipoli,sornainen,rantasauna,klahtimetro,narnia,kandi,tuas,ttalo,otaranta,piritori,taafalunch,alepa,sahkopaja)


  def getAreas : Vector[Area] = areas

  // AJANHALLINTA
  private var currentTime = (8*60)+15

  def getRawTime = currentTime

  def getTime =
    if currentTime%60 >9 then
      s"${currentTime/60}.${currentTime%60}"
    else
      s"${currentTime/60}.0${currentTime%60}"

  def passTime(timeToPass: Int): Boolean =
    currentTime += timeToPass

    if currentTime > (24*60) then
      currentTime = ((8*60)+15)
      false
    else
      true
  def setTime(timeToSet: Int): Unit =
    currentTime = timeToSet

    // EVENTIT

  object spagumayhem extends Event("Spagumayhem"):
    override def checkActive(player: Player): Boolean =
      player.location == taafalunch && currentTime > (60 * 8) && player.inventory.length > 1 && !spagumayhem.activated && player.location.getCurrentDepth >= 1

    override def activateEvent(player: Player): String =
      object spagu extends Item("spagu","Tuttu klassikko taafalta",2.95,1):
        override def eat(player: Player): String = "Söit spagun, sinun ei nyt tarvitse syödä seuraavaan tuntiin."

        override def use(player: Player): String = "Laitoit spagun taskuun, en tiedä mitä ajattelit saavuttavasi tällä. Taskusi ovat nyt täynnä jauhelihakastiketta."

        override def combine(player: Player, combineWith: Item): String = "Bruh"

      val itemToLose = player.inventory(Random.between(0, player.inventory.length -1))
      player.removeItem(itemToLose)
      spagumayhem.activated = true
      if player.getMoneyStatus > 3 then
        player.addItem(spagu)
        player.removeMoney(3)
        s"Ojdå, ihmismassan seassa sinulta varastettiin ${itemToLose}, sentään ostit spagun (ei ois varmaa kannattanu nakkaa sitä reppuun :("
      else
        s"Ojdå, ihmismassan seassa sinulta varastettiin ${itemToLose}"



  object dipolinPohina extends Event("Dipolinpöhinä"):
    override def checkActive(player: Player): Boolean =
      player.location == dipoli && !dipolinPohina.activated && player.location.getCurrentDepth > 0

    override def activateEvent(player: Player): String =
      dipolinPohina.activated = true
      object kayntikortti extends Item("Käyntikortti", "Jonkun pöhinä startupin cvo:n käyntikortti. Vois olla visio ettii työtarjous tän kanssa", 0, 1):
        override def eat(player: Player): String = "Et nyt kuitenkaa viitti alkaa paperii syömään"

        override def use(player: Player): String = "Ei tällä tee muuta ku heitä vesilintua"

        override def combine(player: Player, combineWith: Item): String =
          if combineWith.toString.toLowerCase == "puhelin" then
            "Äh, ei mulle vastata"
          else if combineWith.toString.toLowerCase == "työhakemus" then
            player.removeItem("käyntikortti")
            player.removeItem("työhakemus")

            object tyotarjous extends Item("Työtarjous", "Oho ehkä pääsenkin oikeasti töihin", 1000000, 1):
              override def eat(player: Player): String = "Ei tätä kannata syödä"

              override def use(player: Player): String = "Onglemana on ettet tiedä yrityksestä mitään, edes sitä missä se sijaitsee"

              override def combine(player: Player, combineWith: Item): String = "Et pysty ydistämään tätä mihinkiään, vaikkakin spagu työhakemuksella kuulostaa houkuttelevalta"

            player.addItem(tyotarjous)

            "Oho, hakemus olikin saman tyypin. Oisoitit kiinnostusta työpaikkaa kohtaan ja sait työtarjouksen"
          else
            "Ei pysty"
      player.addItem(kayntikortti)
      "Oho, joku pöhisijä antoi sulle käyntikorttinsa"



  object tyohakemus extends Item("Työhakemus", "Joku työhakemus startuppiin mistä et oo kuullukaan", 1, 1):
     override def eat(player: Player): String = "Ei sitä nyt herranjumala kuitenkaan kannata syödä"

     override def use(player: Player): String =
       if player.location.getActiveEvents(player).map(_.toString.toLowerCase).contains("ttalobossi") then
         player.setNewLocation(narnia)
         passTime(100*60)
         "Annoit työtarjouksen hirviölle, hän suuttui enemmän ja heitti sinut narniaan"
       else
         "Et kyllä tiedä mitä tällä tehdä"

     override def combine(player: Player, combineWith: Item): String =
       if combineWith.toString.toLowerCase == "käyntikortti" then
         player.removeItem("käyntikortti")
         player.removeItem("työhakemus")

         object tyotarjous extends Item("Työtarjous", "Oho ehkä pääsenkin oikeasti töihin", 1000000, 1):
           override def eat(player: Player): String = "Ei tätä kannata syödä"

           override def use(player: Player): String =
             println(player.location.getActiveEvents(player).map(_.toString.toLowerCase))
             if player.location.getActiveEvents(player).map(_.toString.toLowerCase).contains("ttalobossi") && player.inventory.map(_.toString.toLowerCase).contains("gtx760") then
               if player.inventory.map(_.toString.toLowerCase).contains("gtx760") then
                player.location.getActiveEvents(player).filter(_.toString == "ttalobossi").head.setActivated(true)
                player.removeItem("työtarjous")
                player.setNewLocation(tuas)
                "Oho, hänhän innostui työpaikasta sekä näytönohjaimesta ja juoksi pois. Voit nyt jatkaa matkaa pajalle"
               else
                 "Hmm, pystyisinköhän lahjoa häntä sillä hienolla GTX760 näytönohjaimella jonka näin aamulla alepassa myynnissä?"
             else
              "Onglemana on ettet tiedä yrityksestä mitään, edes sitä missä se sijaitsee"

           override def combine(player: Player, combineWith: Item): String = "Et pysty ydistämään tätä mihinkiään, vaikkakin spagu työhakemuksella kuulostaa houkuttelevalta"

         player.addItem(tyotarjous)
         "Oho, hakemus olikin saman tyypin. Oisoitit kiinnostusta työpaikkaa kohtaan ja sait työtarjouksen"
       else
         "Ei kyllä tuu onnistumaan"



  object spagu extends Item("spagu","Tuttu klassikko taafalta",2.95,1):
        override def eat(player: Player): String = "Söit spagun, sinun ei nyt tarvitse syödä seuraavaan tuntiin."

        override def use(player: Player): String = "Laitoit spagun taskuun, en tiedä mitä ajattelit saavuttavasi tällä. Taskusi ovat nyt täynnä jauhelihakastiketta."

        override def combine(player: Player, combineWith: Item): String = "Bruh"



  object note1 extends Item("elämänohjeita","Suosittelen lukemaan, ihan hyödyllinen sisältö.",1,1):
        override def eat(player: Player): String = "Ei tätä kannata syödä"

        override def use(player: Player): String = "Otarannalt saa muuten huhujen mukaan kalaa. Pistää miettii. (Tämä voisi olla hyvä vaihtoehto töissä käymiselle)"

        override def combine(player: Player, combineWith: Item): String = "Bruh"



  object ttalobossi extends Event("ttalobossi"):
    override def checkActive(player: Player): Boolean =
      player.location.toString.toLowerCase == "tietotalo" && !activated

    override def activateEvent(player: Player): String =
      "Eteesi ilmestyy hirveän vihainen hirviö, joka ei tahdo päästää sinua kulkemaan läpi. Pystytköhän jotenkin harhauttamaan häntä?. Vinkki vitonen, hommaa työhakemus ja työtarjous. Dipoli voi olla hyvä suunta."

  object stigulaatio extends Event("Stigulaatio"):
    override def checkActive(player: Player): Boolean =
      (player.location == ok20) && (currentTime > (8 * 60)) && (player.location.getCurrentDepth > 2)

    override def activateEvent(player: Player): String =
      object dokattu extends DokattuDebuff(900 + currentTime, currentTime)
      player.addDebuff(dokattu)
      "Olemn slae:_ vähmnäm, dokatu,,,(mut ei sle plajon))"




  // LISÄÄ EVENTIT OIKEISIIN ALUESIIN
  ok20.addEvent(stigulaatio)
  ttalo.addEvent(ttalobossi)
  rantasauna.addItem(tyohakemus)
  taafalunch.addItem(spagu)
  ablocmetro.addItem(note1)
  dipoli.addEvent(dipolinPohina)
  taafalunch.addEvent(spagumayhem)


  