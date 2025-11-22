package OtaMeemi
import scala.util.Random

class GameWorld:
  private var currentTime = (8*60)+15

  val taafa     = new Area("Taafa", Vector(
                      "Saavut täffälle, betonibrutalismin kukkanen pistää silmään","Yrität mennä sisälle alaovesta, se on kiinni",
                      "Du försöker att komma in genom övre dörren men det är stängt. Tyvärr har du ingen nyckel för du är inte medlem av Teknologföreningen..."
                    ), Vector())

  val smokki    = new Area("Smökki", Vector(
                      "Saavuit smökin pihaan, sisältä kuuluu musiikkia.",
                      "Käynnissä on ilmeisesti sikajuhlat, et pääse sisälle koska sinulla ei ole lippua",
                      "Yrität lahjoa portsarin, hän ei päästä sinua sisälle koska et ole tutalla",
                      "Lahjot portsarin, hän päästää sinut sisälle. Saat outoja katseita koska olet haalarit päällä frakkitapahtumassa"
                    ), Vector())
  val smokkibaari = new Area ("Baari",Vector("Menet baariin"),Vector())

  val ok20      = new Area("Ok20", Vector("Saavut Otakaari 20:n pihaan, se on tyhjä","Saavut Otakaari 20:n pihaan, yläovesta kuuluu musiikkia","Menet sisälle, käynnissä on stigulaatio. Tunnet itsesi ulkopuoliseksi koska et ole dokattu","Olet dokattu, valmistaudu hauskaan iltaan"), Vector())
  
  val dipoli    = new Area("Dipoli", Vector(
                      "Saavut dipolille, frakkien määrän perusteella sisällä on meneillään jotain tärkeää",
                      "Kävelet sisään. Kävelet suoraan ulos hämmästyneenä pöhinän määrästä"
                    ), Vector())
  val dipoliravintola = new Area("Dipolin ravintola", Vector("Kiipeät yläkertaan syömään","Syöt ruokaa tavalliselta linjastolta"),Vector())
  val knmcdonalds = new Area("Keilaniemi Mcdonalds", Vector(
                      "Saavut miljardin dollarin konserniin Keilaniemessä. Vakiotyöpaikka tutalaisille","Hei ootko säkin muuten tutalla?"), Vector())
  val knmcdravintola = new Area ("Keilaniemi Mcdonalds / Olet jonossa",Vector("Menet jonoon","Tilaat cledos mealin (5,95 mäkkärist)","Syöt ruokasi, tilaat lisää pöytään juoksuttaaksesi tarjoilijaa"),Vector())
  val sornainen   = new Area("Sörnäisten metroasema", Vector(
                      "Ilmeisesti sammuit metroon Stigulaation jälkeen ja päädyit Sörnäisiin."
                    ), Vector())
  val piritori = new Area ("Piritori",Vector("Nouset ylös asemalta pahamaineiselle piritorille","Paikallinen diileri tarjoaa sinulle peukkua"),Vector())
  val rantasauna  = new Area("Rantasauna", Vector("Menet rantasaunalle. Sauna on kylmä ja ovet ovat lukossa. Et kai vaan muistanut päivää väärin?",
    "Menet rantasaunalle, sisältä kuuluu puhetta.",
    "Menet sisälle, toivottavasti muistit saunakamat, sauna on nimittäin lämmin.",
    "Menet paljuun. Paljun nykyinen maksimikapasiteetti on *aikaisempi maksimi + 1* henkilöä.",
    "Menet uimaan. Vesi ei ole kovinkaan syvää mutta kylmä siinä tulee ja nopeasti."), Vector())

  val klahtimetro = new Area("Kivenlahden metroasema", Vector("Sammuit stigulaatiossa, kiitä onneasi että päädyit tänne etkä katuojaan.","Päätepysäkki, täällä on hyvin vähän näkemisen arvoista"), Vector())
  val narnia      = new Area("Narnia", Vector("Miten tässä näin kävi?","Löydät itsesi vaatekaapin toiselta puolelta taikamaailmasta","Suureksi harmiksesi huomaat oven sulkeutuneen takanasi. Eihän tämä näin mene siinä kirjassa."), Vector())
  val sus      = new Area("Amogus", Vector("Placeholder"), Vector())
  val abloc       = new Area("A Bloc", Vector("O Block mutta aallossa","Menet syömään. Valitse ruokapaikka: Konnichiwa, Kotkot, Poke bowl","Menet metrolle. Minne matka?"), Vector())
  val kandi       = new Area("Kandikeskus", Vector("Saavut kandikeskukselle (tuttavallisemmin kandilafka)","Menet A-saliin fysiikan luennolle ja huomaat, että se on lähes tyhjä. Vastuullista sakkia nämä opiskelijat.","Pyörit ympyrää Y-siivessä koska et tiedä missä Y208b on. (vinkki vitonen, se ei ole toisessa kerroksessa)"), Vector())
  val tuas        = new Area("TUAS", Vector("Menet sähköpajalle, bambu on varattu seuraavat 5h55min. Projektisi runko on edelleen tulostamatta.","Väsäät projektia tunnin","ja toisen","ja kolmannen","Menet syömään, on keskiviikko eli tarjolla on lohta tartarkastikkeessa a 5.50€"), Vector())
  val ttalo       = new Area("Tietotalo", Vector("Täällä asuvat eivät tunne suihkun käsitettä","Menet opiskelemaan kasvihuoneeseen"), Vector())
  val designfactory = new Area("Aalto Design Factory", Vector("Saavut Design factoryn pihaan, sisältä kuuluu pöhinää","Menet sisälle pöhisemään","Hemo pöhinä bro kryptoi bro"), Vector())
  val otaranta    = new Area("Otaranta", Vector("Saavut otarantaan. Kylmä tuuli puhaltaa mereltä","Menet uimaan, vesi on kylmää (yllättyneet parijonoon)"), Vector())
  val taafalunch = new Area("Taafan lounasravintola",Vector("Menet spagujonoon","Edelleen spagujonossa","Tässä voi kestää hetken","Saat 1kpl spagua"),Vector())
  val ttalolunch = new Area ("Subway",Vector("Menet subwayn jonoon, mieti tilauksesi huolella tai käy hassusti","Tilaat hunajaoreganosubin #canihaveapleaseburgercheese","Sait tummaan leipään tehdyn spicy italianin"),Vector())
  taafa.connections = Vector((kandi,5),(dipoli,2),(smokki,5),(taafalunch,1))
  smokki.connections = Vector((otaranta,5),(ok20,2),(smokkibaari,1))
  ok20.connections = Vector((kandi,10),(rantasauna,10))
  dipoliravintola.connections = Vector((dipoli,1))
  dipoli.connections = Vector((taafa,5),(kandi,10),(knmcdonalds,15),(dipoliravintola,1))
  knmcdonalds.connections = Vector((dipoli,15),(sornainen,10),(klahtimetro,10),(knmcdravintola,0))
  sornainen.connections = Vector((knmcdonalds,30),(klahtimetro,60),(piritori,2))
  rantasauna.connections = Vector((narnia,10),(ok20,10))
  klahtimetro.connections = Vector((knmcdonalds,30),(sornainen,60))
  narnia.connections = Vector((rantasauna,10))
  abloc.connections = Vector((kandi,2),(ttalo,10),(klahtimetro,30),(knmcdonalds,10),(sornainen,20))
  kandi.connections = Vector((abloc,20),(designfactory,20),(ok20,30),(taafa,30),(dipoli,20))
  tuas.connections = Vector((ttalo,5))
  ttalo.connections = Vector((abloc,10),(designfactory,10),(ttalolunch,1))
  designfactory.connections = Vector((kandi,15),(ttalo,10))
  otaranta.connections = Vector((smokki,15),(rantasauna,10))
  piritori.connections = Vector((sornainen,2))
  taafalunch.connections = Vector((taafa,1))
  knmcdravintola.connections = Vector((knmcdonalds,0))
  ttalolunch.connections = Vector((ttalo,1))
  smokkibaari.connections = Vector((smokkibaari,1))
  sus.connections = Vector((ok20,1))

  private val areas =
    Vector[Area](sus,taafa,smokki,ok20,dipoli,knmcdonalds,sornainen,rantasauna,klahtimetro,narnia,abloc,kandi,tuas,ttalo,designfactory,otaranta,piritori,knmcdravintola,taafalunch,ttalolunch,smokkibaari,dipoliravintola)


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
      player.location == taafa && currentTime > (60 * 8) && player.inventory.length > 1 && !spagumayhem.activated && player.location.getCurrentDepth >= 1

    override def activateEvent(player: Player): String =
      val itemToLose = player.inventory(Random.between(0, player.inventory.length -1))
      player.removeItem(itemToLose)
      spagumayhem.activated = true
      s"Ojdå, ihmismassan seassa sinulta varastettiin ${itemToLose}"


  taafa.addEvent(spagumayhem)

