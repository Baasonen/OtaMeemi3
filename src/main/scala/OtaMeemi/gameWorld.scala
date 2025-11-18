package OtaMeemi
import scala.util.Random

class GameWorld:
  private var currentTime = (8*60)+15

  val taafa     = new Area("Taafa", Vector(
                      "Saavut täffälle, betonibrutalismin kukkanen pistää silmään","Yrität mennä sisälle. Huomaat että olet spagujonossa ilman lounaslippua. Joudut odottamaan jonossa muiden rahvaiden kanssa.",
                      "Menet sisälle. Ohitat koko jonon fastlanea käyttäen koska sinulla on lounaslippu. Oppiipahan.",
                      "Du försöker att komma in genom övre dörren men det är stängt. Tyvärr har du ingen nyckel för du är inte medlem av Teknologföreningen..."
                    ), Vector())

  val smokki    = new Area("Smökki", Vector(
                      "Saavuit smökin pihaan, sisältä kuuluu musiikkia.",
                      "Käynnissä on ilmeisesti sikajuhlat, et pääse sisälle koska sinulla ei ole lippua",
                      "Yrität lahjoa portsarin, hän ei päästä sinua sisälle koska et ole tutalla",
                      "Lahjot portsarin, hän päästää sinut sisälle. Saat outoja katseita koska olet haalarit päällä frakkitapahtumassa"
                    ), Vector())

  val ok20      = new Area("Ok20", Vector(), Vector())
  val dipoli    = new Area("Dipoli", Vector(
                      "Saavut dipolille, frakkien määrän perusteella sisällä on meneillään jotain tärkeää",
                      "Syöt ruokaa tavalliselta linjastolta",
                      "Kävelet sisään. Kävelet suoraan ulos hämmästyneenä pöhinän määrästä"
                    ), Vector())
  val knmcdonalds = new Area("Keilaniemi Mcdonalds", Vector(
                      "Saavut miljardin dollarin konserniin Keilaniemessä. Vakiotyöpaikka tutalaisille",
                      "Mitä saisi olla? Hei ootko sä tutalla?"
                    ), Vector())
  val sornainen   = new Area("Sörnäisten metroasema", Vector(
                      "Ilmeisesti sammuit metroon Stigulaation jälkeen ja päädyit Sörnäisiin"
                    ), Vector())
  val rantasauna  = new Area("Rantasauna", Vector(
                      "Saavut rantasaunalle, palju ja sauna ovat lämpimiä",
                      "Ice age 3 pyörii valkokankaalla, Mario Kart löytyy sivuhuoneesta",
                      "Palju on melko täynnä, onneksi paljuun mahtuu aina n+1 ihmistä",
                      "“Top kolmosessa ei ole yhtään oikeaa pelaajaa. Kaiken lisäksi switchin laturi on hukassa ja ohjaimista alkaa loppua virta"
                    ), Vector())
  val klahtimetro = new Area("Kivenlahden metroasema", Vector("Placeholder"), Vector())
  val narnia      = new Area("Narnia", Vector("Miten tässä näin kävi?"), Vector())
  val abloc       = new Area("A Bloc", Vector("O Block mutta aallossa"), Vector())
  val kandi       = new Area("Kandikeskus", Vector("Placeholder"), Vector())
  val tuas        = new Area("TUAS", Vector("Placeholder"), Vector())
  val ttalo       = new Area("Tietotalo", Vector(
                      "Saavut tietotalolle, täällä asuville suihku on vieras käsite",
                      "Käyt syömässä subwayssa hunajaoreganosubin #pleaseburgercheese"
                    ), Vector())
  val designfactory = new Area("Aalto Design Factory", Vector(
                      "Saavut Design factoryn pihaan, sisältä kuuluu pöhinää",
                      "Menet sisälle pöhisemään"
                    ), Vector())
  val otaranta    = new Area("Otaranta", Vector(
                      "Saavut otarantaan. Kylmä tuuli puhaltaa mereltä",
                      "Menet uimaan, vesi on kylmää (yllättyneet parijonoon)"
                    ), Vector())


  taafa.connections = Vector((kandi,5),(dipoli,2))
  smokki.connections = Vector((otaranta,5),(ok20,2))
  ok20.connections = Vector((kandi,10),(rantasauna,10))
  dipoli.connections = Vector((taafa,5),(kandi,10),(knmcdonalds,15))
  knmcdonalds.connections = Vector((dipoli,15),(sornainen,10),(klahtimetro,10))
  sornainen.connections = Vector((knmcdonalds,30),(klahtimetro,60))
  rantasauna.connections = Vector((narnia,1200),(ok20,10))
  klahtimetro.connections = Vector((knmcdonalds,30),(sornainen,60))
  narnia.connections = Vector((rantasauna,1200))
  abloc.connections = Vector((kandi,2),(ttalo,10),(klahtimetro,30),(knmcdonalds,10),(sornainen,20))
  kandi.connections = Vector((abloc,20),(designfactory,20),(ok20,30),(taafa,30),(dipoli,20))
  tuas.connections = Vector((ttalo,5))
  ttalo.connections = Vector((abloc,10),(designfactory,10))
  designfactory.connections = Vector((kandi,15),(ttalo,10))
  otaranta.connections = Vector((smokki,15),(rantasauna,10))

  private val areas =
    Vector[Area](taafa,smokki,ok20,dipoli,knmcdonalds,sornainen,rantasauna,klahtimetro,narnia,abloc,kandi,tuas,ttalo,designfactory,otaranta)


  def getAreas : Vector[Area] = areas

  def getTime = s"${currentTime/60}.${currentTime%60}"

  def passTime(timeToPass: Int) =
    currentTime += timeToPass