package OtaMeemi


class GameWorld:


  object taafa extends Area("Taafa"):
    def neighbors = Vector(kandi,dipoli)
    def subDesc = Vector()

  object smokki extends Area("Smökki"):
    def neighbors = Vector()
    def subDesc = Vector()

  object ok20 extends Area("Ok20"):
    def neighbors = Vector()
    def subDesc = Vector()

  object dipoli extends Area("Dipoli"):
    def neighbors = Vector()
    def subDesc = Vector()

  object knmcdonalds extends Area("Keilaniemi Mcdonalds"):
    def neighbors = Vector()
    def subDesc = Vector()

  object sornainen extends Area("Sörnäisten metroasema"):
    def neighbors = Vector()
    def subDesc = Vector()

  object rantasauna extends Area("Rantasauna"):
    def neighbors = Vector()
    def subDesc = Vector()

  object klahtimetro extends Area("Kivenlahden metroasema"):
    def neighbors = Vector()
    def subDesc = Vector()

  object narnia extends Area("Narnia"):
    def neighbors = Vector()
    def subDesc = Vector()

  object abloc extends Area("A Bloc"):
    def neighbors = Vector()
    def subDesc = Vector()

  object kandi extends Area("Kandikeskus"):
    def neighbors = Vector()
    def subDesc = Vector()

  object tuas extends Area("TUAS"):
    def neighbors = Vector()
    def subDesc = Vector()

  object ttalo extends Area("Tietotalo"):
    def neighbors = Vector()
    def subDesc = Vector()

  object designfactory extends Area("Aalto Design Factory")
    def neighbors = Vector()
    def subDesc = Vector()

  object otaranta extends Area("Otaranta")
    def neighbors = Vector()
    def subDesc = Vector()
    
  private val areas = 
    Vector[Area](taafa,smokki,ok20,dipoli,knmcdonalds,sornainen,rantasauna,klahtimetro,narnia,abloc,kandi,tuas,ttalo,designfactory,otaranta)
  def getAreas : Vector[Area] = areas