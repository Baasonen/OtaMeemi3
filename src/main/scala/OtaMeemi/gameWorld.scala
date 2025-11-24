package OtaMeemi
import scala.util.Random

class GameWorld:
  private var currentTime = (8*60)+15

  val taafa     = new Area("Taafa", Vector(
                      "Saavut täffälle, betonibrutalismin kukkanen pistää silmään","Yrität mennä sisälle alaovesta, se on kiinni",
                      "Du försöker att komma in genom övre dörren men det är stängt. Tyvärr har du ingen nyckel för du är inte medlem av Teknologföreningen..."
                    ), Vector(), false)

  val smokki    = new Area("Smökki", Vector(
                      "Saavuit smökin pihaan, sisältä kuuluu musiikkia.",
                      "Käynnissä on ilmeisesti sikajuhlat, et pääse sisälle koska sinulla ei ole lippua",
                      "Yrität lahjoa portsarin, hän ei päästä sinua sisälle koska et ole tutalla",
                      "Lahjot portsarin, hän päästää sinut sisälle. Saat outoja katseita koska olet haalarit päällä frakkitapahtumassa"
                    ), Vector(), false)

  val ok20      = new Area("Ok20", Vector("Saavut Otakaari 20:n pihaan, se on tyhjä","Saavut Otakaari 20:n pihaan, yläovesta kuuluu musiikkia","Menet sisälle, käynnissä on stigulaatio. Tunnet itsesi ulkopuoliseksi koska et ole dokattu","Olet dokattu, valmistaudu hauskaan iltaan"), Vector(), false)
  val dipoli    = new Area("Dipoli", Vector(
                      "Saavut dipolille, frakkien määrän perusteella sisällä on meneillään jotain tärkeää",
                      "Kävelet sisään. Pöhinän seassa pystysi ehkä jopa verkostoitua",
                      "Pakenet paikalta ennenkuin tilanne pääsee eskaloitumaan"
                    ), Vector(), false)
  val dipoliravintola = new Area("Dipoli", Vector("Kiipeät yläkertaan syömään","Syöt ruokaa tavalliselta linjastolta"),Vector(), false)
  val knmcdonalds = new Area("Keilaniemi Mcdonalds", Vector(
                      "Saavut miljardin dollarin konserniin Keilaniemessä. Vakiotyöpaikka tutalaisille","Hei ootko säkin muuten tutalla?"), Vector(), false)
  val knmcdravintola = new Area ("Keilaniemi Mcdonalds / Olet jonossa",Vector("Menet jonoon","Tilaat cledos mealin (5,95 mäkkärist)","Syöt ruokasi, tilaat lisää pöytään juoksuttaaksesi tarjoilijaa"),Vector(), false)
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
  val designfactory = new Area("Aalto Design Factory", Vector("Saavut Design factoryn pihaan, sisältä kuuluu pöhinää","Menet sisälle pöhisemään","Hemo pöhinä bro kryptoi bro"), Vector(), false)
  val otaranta    = new Area("Otaranta", Vector("Saavut otarantaan. Kylmä tuuli puhaltaa mereltä","Menet uimaan, vesi on kylmää (yllättyneet parijonoon)"), Vector(), false)
  val taafalunch = new Area("Taafan lounasravintola",Vector("Menet spagujonoon","Edelleen spagujonossa","Tässä voi kestää hetken","Saat 1kpl spagua"),Vector(), false)
  val ttalolunch = new Area ("Subway",Vector("Menet subwayn jonoon, mieti tilauksesi huolella tai käy hassusti","Tilaat hunajaoreganosubin #canihaveapleaseburgercheese","Sait tummaan leipään tehdyn spicy italianin"),Vector(), false)
  val ablocmetro = new Area ("Metro",Vector("Menit metrolle. Minne matka?"),Vector(),false)
  val alepa = new Area ("Alepa", Vector("Menit alepaan, 2e redbull tarjous on voimassa"),Vector(),true)
  
  taafa.connections = Vector((kandi,5),(dipoli,2),(smokki,5),(taafalunch,1))
  smokki.connections = Vector((otaranta,5),(ok20,2))
  ok20.connections = Vector((kandi,10),(rantasauna,10))
  dipoli.connections = Vector((taafa,5),(kandi,10),(knmcdonalds,15))
  knmcdonalds.connections = Vector((dipoli,15),(sornainen,10),(klahtimetro,10),(knmcdravintola,0))
  sornainen.connections = Vector((knmcdonalds,30),(klahtimetro,60),(piritori,2))
  rantasauna.connections = Vector((narnia,10),(ok20,10))
  klahtimetro.connections = Vector((knmcdonalds,30),(sornainen,60))
  narnia.connections = Vector((rantasauna,10))
  abloc.connections = Vector((kandi,2),(ttalo,10),(ablocmetro,1),(alepa,1))
  kandi.connections = Vector((abloc,20),(designfactory,20),(ok20,30),(taafa,30),(dipoli,20))
  tuas.connections = Vector((ttalo,5))
  ttalo.connections = Vector((abloc,10),(designfactory,10),(ttalolunch,1))
  designfactory.connections = Vector((kandi,15),(ttalo,10))
  otaranta.connections = Vector((smokki,15),(rantasauna,10))
  piritori.connections = Vector((sornainen,2))
  taafalunch.connections = Vector((taafa,1))
  knmcdravintola.connections = Vector((knmcdonalds,0))
  ttalolunch.connections = Vector((ttalo,1))
  ablocmetro.connections = Vector((abloc,1),(klahtimetro,30),(knmcdonalds,10),(sornainen,20))
  sus.connections = Vector((ttalo,1))
  alepa.connections = Vector((abloc,1))
  private val areas =
    Vector[Area](sus,taafa,smokki,ok20,dipoli,knmcdonalds,sornainen,rantasauna,klahtimetro,narnia,abloc,kandi,tuas,ttalo,designfactory,otaranta,piritori,knmcdravintola,taafalunch,ttalolunch,ablocmetro,alepa)


  def getAreas : Vector[Area] = areas

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

  object spagumayhem extends Event("Spagumayhem"):
    override def checkActive(player: Player): Boolean =
      player.location == taafalunch && currentTime > (60 * 8) && player.inventory.length > 1 && !spagumayhem.activated && player.location.getCurrentDepth >= 1

    override def activateEvent(player: Player): String =
      object spagu extends Item("spagu","Tuttu klassikko taafalta",2.95,1):
        override def eat(player: Player): String = "Söit spagun, sinun ei nyt tarvitse syödä seuraavaan tuntiin."

        override def use(player: Player): String = "Laitoit spagun taskuun, en tiedä mitä ajattelit saavuttavasi tällä. Taskusi ovat nyt täynnä jauhelihakastiketta."

        override def combine(player: Player, combineWith: Item): String =
          if combineWith == spagu then
            "what is bro doing💀 (olet nyt puolivälissä pelin voittamista)"
          else
            "Tässä ei ole mitään nähtävää, ÄLÄ yritä yhdistää tätä spicy italianin kanssa"

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
      object kayntikortti extends Item("Käyntikortti", "Jonkun pöhinä startupin cvo:n käyntikortti", 0, 1):
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
             if player.location.getActiveEvents(player).map(_.toString.toLowerCase).contains("ttalobossi") then
               player.location.getActiveEvents(player).filter(_.toString == "ttalobossi").head.setActivated(true)
               player.removeItem("työtarjous")
               player.setNewLocation(tuas)
               "Oho, hänhän innostui työpaikasta ja juoksi pois. Voit nyt jatkaa matkaa pajalle"
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

        override def combine(player: Player, combineWith: Item): String =
          if combineWith == spagu then
            "what is bro doing💀 (olet nyt puolivälissä pelin voittamista)"
          else
            "Tässä ei ole mitään nähtävää, ÄLÄ yritä yhdistää tätä spicy italianin kanssa"

  object note1 extends Item("muistilappu","Suosittelen lukemaan, ihan hyödyllinen sisältö.",1,1):
        override def eat(player: Player): String = "Ei tätä kannata syödä"

        override def use(player: Player): String = "Tervetuloa peliin. Tavoitteesi on yksinkertainen, sinun pitää vain tehdä prokrastinoimasi sähköpajaprojekti valmiiksi tänään. Vinkki vitonen, TUAS voisi olla hyvä alkusuunta."

        override def combine(player: Player, combineWith: Item): String =
          if combineWith == spagu then 
            "Kätevää, sinulla on nyt spagu paperilapulla ja yksi vihje vähemmän."
          else 
            "Sinuna en hankkiutuisi tästä eroon esimerkiksi yhdistämällä sitä spaguun"

  object ttalobossi extends Event("ttalobossi"):
    override def checkActive(player: Player): Boolean =
      player.location.toString.toLowerCase == "tietotalo" && !activated

    override def activateEvent(player: Player): String =
      "Eteesi ilmestyy hirveän vhainen hirviö, joka ei tahdo päästää sinua kulkemaan läpi. Pystytköhän jotenkin harhauttamaan häntä?"

  ttalo.addEvent(ttalobossi)
  rantasauna.addItem(tyohakemus)
  taafalunch.addItem(spagu)
  abloc.addItem(note1)
  alepa.addItem(redbull)
  dipoli.addEvent(dipolinPohina)
  taafalunch.addEvent(spagumayhem)
  
  object redbull extends Item("Redbull", "Tarpeellinen energia aamuun",2,1):
    override def eat(player: Player): String = "Joit redbullin, nyt pärisee"

    override def combine(player: Player, combineWith: Item): String = "Äläs nyt"

    override def use(player: Player): String = "Laitoit redbullin taskuun"
    

  