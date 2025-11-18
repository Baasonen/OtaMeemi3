package OtaMeemi
import scala.util.Random

class GameWorld:
  private var currentTime = (8*60)+15

  private var taafaConnections: Vector[(Area, Int)] = Vector()
  private var smokkiConnections: Vector[(Area, Int)] = Vector()
  private var ok20Connections: Vector[(Area, Int)] = Vector()
  private var dipoliConnections: Vector[(Area, Int)] = Vector()
  private var knmcdonaldsConnections: Vector[(Area, Int)] = Vector()
  private var sornainenConnections: Vector[(Area, Int)] = Vector()
  private var rantasaunaConnections: Vector[(Area, Int)] = Vector()
  private var klahtimetroConnections: Vector[(Area, Int)] = Vector()
  private var narniaConnections: Vector[(Area, Int)] = Vector()
  private var ablocConnections: Vector[(Area, Int)] = Vector()
  private var kandiConnections: Vector[(Area, Int)] = Vector()
  private var tuasConnections: Vector[(Area, Int)] = Vector()
  private var ttaloConnections: Vector[(Area, Int)] = Vector()
  private var designfactoryConnections: Vector[(Area, Int)] = Vector()
  private var otarantaConnections: Vector[(Area, Int)] = Vector()


  val taafa     = new Area("Taafa", Vector(
                      "Saavut täffälle, betonibrutalismin kukkanen pistää silmään",
                      "Yrität mennä sisälle. Huomaat että olet spagujonossa ilman lounaslippua. Joudut odottamaan jonossa muiden rahvaiden kanssa.",
                      "Menet sisälle. Ohitat koko jonon fastlanea käyttäen koska sinulla on lounaslippu. Oppiipahan.",
                      "Du försöker att komma in genom övre dörren men det är stängt. Tyvärr har du ingen nyckel för du är inte medlem av Teknologföreningen..."
                    ), taafaConnections)

  val smokki    = new Area("Smökki", Vector(
                      "Saavuit smökin pihaan, sisältä kuuluu musiikkia.",
                      "Käynnissä on ilmeisesti sikajuhlat, et pääse sisälle koska sinulla ei ole lippua",
                      "Yrität lahjoa portsarin, hän ei päästä sinua sisälle koska et ole tutalla",
                      "Lahjot portsarin, hän päästää sinut sisälle. Saat outoja katseita koska olet haalarit päällä frakkitapahtumassa"
                    ), smokkiConnections)

  val ok20      = new Area("Ok20", Vector(), ok20Connections)
  val dipoli    = new Area("Dipoli", Vector(
                      "Saavut dipolille, frakkien määrän perusteella sisällä on meneillään jotain tärkeää",
                      "Syöt ruokaa tavalliselta linjastolta",
                      "Kävelet sisään. Kävelet suoraan ulos hämmästyneenä pöhinän määrästä"
                    ), dipoliConnections)
  val knmcdonalds = new Area("Keilaniemi Mcdonalds", Vector(
                      "Saavut miljardin dollarin konserniin Keilaniemessä. Vakiotyöpaikka tutalaisille",
                      "Mitä saisi olla? Hei ootko sä tutalla?"
                    ), knmcdonaldsConnections)
  val sornainen   = new Area("Sörnäisten metroasema", Vector(
                      "Ilmeisesti sammuit metroon Stigulaation jälkeen ja päädyit Sörnäisiin"
                    ), sornainenConnections)
  val rantasauna  = new Area("Rantasauna", Vector(
                      "Saavut rantasaunalle, palju ja sauna ovat lämpimiä",
                      "Ice age 3 pyörii valkokankaalla, Mario Kart löytyy sivuhuoneesta",
                      "Palju on melko täynnä, onneksi paljuun mahtuu aina n+1 ihmistä",
                      "“Top kolmosessa ei ole yhtään oikeaa pelaajaa. Kaiken lisäksi switchin laturi on hukassa ja ohjaimista alkaa loppua virta"
                    ), rantasaunaConnections)
  val klahtimetro = new Area("Kivenlahden metroasema", Vector("Placeholder"), klahtimetroConnections)
  val narnia      = new Area("Narnia", Vector("Miten tässä näin kävi?"), narniaConnections)
  val abloc       = new Area("A Bloc", Vector("O Block mutta aallossa"), ablocConnections)
  val kandi       = new Area("Kandikeskus", Vector("Placeholder"), kandiConnections)
  val tuas        = new Area("TUAS", Vector("Placeholder"), tuasConnections)
  val ttalo       = new Area("Tietotalo", Vector(
                      "Saavut tietotalolle, täällä asuville suihku on vieras käsite",
                      "Käyt syömässä subwayssa hunajaoreganosubin #pleaseburgercheese"
                    ), ttaloConnections)
  val designfactory = new Area("Aalto Design Factory", Vector(
                      "Saavut Design factoryn pihaan, sisältä kuuluu pöhinää",
                      "Menet sisälle pöhisemään"
                    ), designfactoryConnections)
  val otaranta    = new Area("Otaranta", Vector(
                      "Saavut otarantaan. Kylmä tuuli puhaltaa mereltä",
                      "Menet uimaan, vesi on kylmää (yllättyneet parijonoon)"
                    ), otarantaConnections)


  taafaConnections = Vector((kandi,5),(dipoli,2))
  smokkiConnections = Vector((otaranta,5),(ok20,2))
  ok20Connections = Vector((kandi,10),(rantasauna,10))
  dipoliConnections = Vector((taafa,5),(kandi,10),(knmcdonalds,15))
  knmcdonaldsConnections = Vector((dipoli,15),(sornainen,10),(klahtimetro,10))
  sornainenConnections = Vector((knmcdonalds,30),(klahtimetro,60))
  rantasaunaConnections = Vector((narnia,1200),(ok20,10))
  klahtimetroConnections = Vector((knmcdonalds,30),(sornainen,60))
  narniaConnections = Vector((rantasauna,1200))
  ablocConnections = Vector((kandi,2),(ttalo,10),(klahtimetro,30),(knmcdonalds,10),(sornainen,20))
  kandiConnections = Vector((abloc,20),(designfactory,20),(ok20,30),(taafa,30),(dipoli,20))
  tuasConnections = Vector((ttalo,5))
  ttaloConnections = Vector((abloc,10),(designfactory,10))
  designfactoryConnections = Vector((kandi,15),(ttalo,10))
  otarantaConnections = Vector((smokki,15),(rantasauna,10))

  private val areas =
    Vector[Area](taafa,smokki,ok20,dipoli,knmcdonalds,sornainen,rantasauna,klahtimetro,narnia,abloc,kandi,tuas,ttalo,designfactory,otaranta)


  def getAreas : Vector[Area] = areas

  def getTime = currentTime

  def passTime(timeToPass: Int) =
    currentTime += timeToPass