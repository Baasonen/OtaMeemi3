package OtaMeemi

class OtaMeemiGame:
  println("GameInit")
  val otaniemi = new GameWorld()
  println("GameWorldInit")
  lazy val player = new Player(otaniemi)

  val title = "OtaMeemi3 (1, 2 ja 2.1 kuluivat toimivan projektin luontiin intelliJ:ssa"

  val welcomeMessage = s"Aamusi alkaa A blocilta, kello on ${otaniemi.getTime}, nyt on kiire luennolle. Tänään pitää ehtiä myös pajalla saattamaan projekti loppuun. Elämänohjeet kannattaa ottaa maasta ja lukea."
  val goodbyeMessage = "Goodbye"

  var turnCount = 0

  val turnLimit = 15

  def isComplete = player.location == otaniemi.sahkopaja

  def isOver = false

  def playTurn(command: String) =
    val action = Action(command)
    val outcomeReport = action.execute(player)
    if outcomeReport.isDefined then
      this.turnCount += 1
    outcomeReport.getOrElse(s"""Unknown command: "$command".""")