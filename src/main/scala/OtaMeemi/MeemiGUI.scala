package OtaMeemi

import scala.swing.*
import scala.swing.event.*
import javax.swing.UIManager
import java.awt.{Point, Insets, Dimension, Font as AwtFont, Image, Graphics2D}
import scala.language.adhocExtensions // enable extension of Swing classes
import javax.swing.ImageIcon

////////////////// NOTE TO STUDENTS //////////////////////////
// For the purposes of our course, it’s not necessary
// that you understand or even look at the code in this file.
//////////////////////////////////////////////////////////////

/** The singleton object `AdventureGUI` represents a GUI-based version of the Adventure
  * game application. The object serves as a possible entry point for the game app, and can
  * be run to start up a user interface that operates in a separate window. The GUI reads
  * its input from a text field and displays information about the game world in uneditable
  * text areas.

  * **NOTE TO STUDENTS: In this course, you don’t need to understand how this object works
  * on the inside. It’s enough to know that you can use this file to start the program.**
  *
  * @see [[AdventureTextUI]] */
object OtameemiGUI extends SimpleSwingApplication:
  UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName)

  def top = new MainFrame:

    // Access to the application’s internal logic:

    val game = OtaMeemiGame()
    val player = game.player

    private val world = game.otaniemi
    import world.*

    private val otaniemiIcon = new ImageIcon(getClass.getResource("/pot.png"))
    private val dipoliIcon = new ImageIcon(getClass.getResource("/dipoli.png"))
    private val amogus = new ImageIcon(getClass.getResource("/sus.png"))
    private val taafaIcon         = new ImageIcon(getClass.getResource("/taafa.png"))
    private val smokkiIcon        = new ImageIcon(getClass.getResource("/smokki.png"))
    private val ok20Icon          = new ImageIcon(getClass.getResource("/ok20.png"))
    private val knmcdonaldsIcon   = new ImageIcon(getClass.getResource("/knmcdonalds.png"))
    private val sornainenIcon     = new ImageIcon(getClass.getResource("/sornainen.png"))
    private val rantasaunaIcon    = new ImageIcon(getClass.getResource("/rantasauna.png"))
    private val klahtimetroIcon   = new ImageIcon(getClass.getResource("/klahtimetro.png"))
    private val narniaIcon        = new ImageIcon(getClass.getResource("/narnia.png"))
    private val ablocIcon         = new ImageIcon(getClass.getResource("/abloc.png"))
    private val kandiIcon         = new ImageIcon(getClass.getResource("/kandikeskus.png"))
    private val tuasIcon          = new ImageIcon(getClass.getResource("/tuas.png"))
    private val ttaloIcon         = new ImageIcon(getClass.getResource("/ttalo.png"))
    private val designfactoryIcon = new ImageIcon(getClass.getResource("/designfactory.png"))
    private val otarantaIcon      = new ImageIcon(getClass.getResource("/otaranta.png"))
    private val piritoriIcon      = new ImageIcon(getClass.getResource("/piritori.png"))
    private val taafalunchIcon      = new ImageIcon(getClass.getResource("/taafalunch.png"))
    private val subiIcon      = new ImageIcon(getClass.getResource("/subi.png"))
    private val dipolilunchIcon = new ImageIcon(getClass.getResource("/dipoliravintola.png"))
    private val ablocmetroIcon = new ImageIcon(getClass.getResource("/ablocmetro.png"))
    private val tripleTIcon = new ImageIcon(getClass.getResource("/triplet.png")).getImage
    // Components:
    val vaihtuvalabel = new Label:
      icon = otaniemiIcon
    val locationInfo = new TextArea(7, 40):
      editable = false
      wordWrap = true
      lineWrap = true
    val turnOutput = new TextArea(7, 40):
      editable = false
      wordWrap = true
      lineWrap = true
    val input = new TextField(40):
      minimumSize = preferredSize
    this.listenTo(input.keys)
    val turnCounter = Label()

    // Layout:

    val gamePanel = new GridBagPanel:
      import scala.swing.GridBagPanel.Anchor.*
      import scala.swing.GridBagPanel.Fill
      layout += vaihtuvalabel          -> Constraints(0, 0, 1, 1, 0, 0, NorthWest.id, Fill.None.id, Insets(5, 5, 5, 5), 0, 0)
      layout += Label("Location:") -> Constraints(0, 0, 1, 1, 0, 1, NorthWest.id, Fill.None.id, Insets(8, 5, 5, 5), 0, 0)
      layout += Label("Command:")  -> Constraints(0, 1, 1, 1, 0, 0, NorthWest.id, Fill.None.id, Insets(8, 5, 5, 5), 0, 0)
      layout += Label("Events:")   -> Constraints(0, 2, 1, 1, 0, 0, NorthWest.id, Fill.None.id, Insets(8, 5, 5, 5), 0, 0)
      layout += turnCounter        -> Constraints(0, 3, 2, 1, 0, 0, NorthWest.id, Fill.None.id, Insets(8, 5, 5, 5), 0, 0)
      layout += locationInfo       -> Constraints(1, 0, 1, 1, 1, 1, NorthWest.id, Fill.Both.id, Insets(5, 5, 5, 5), 0, 0)
      layout += input              -> Constraints(1, 1, 1, 1, 1, 0, NorthWest.id, Fill.None.id, Insets(5, 5, 5, 5), 0, 0)
      layout += turnOutput         -> Constraints(1, 2, 1, 1, 1, 1, SouthWest.id, Fill.Both.id, Insets(5, 5, 5, 5), 0, 0)

    // alkunäyttöpaneeli
    val titleLabel = new Label("OTAMEEMI"):
      font = new Font(font.getName, java.awt.Font.BOLD, 40)
      horizontalAlignment =Alignment.Center

    val startButton = new Button("Aloita peli")

    val startPanel = new BackgroundBoxPanel(Orientation.Vertical, tripleTIcon):
      contents += Swing.VStrut(100)
      contents +=  new FlowPanel(FlowPanel.Alignment.Center)(titleLabel)
      contents += Swing.VStrut(100)
      contents += new FlowPanel(FlowPanel.Alignment.Center)(startButton)
      border = Swing.EmptyBorder(40, 40, 40, 40)

    this.listenTo(startButton)


    // Events:

    this.reactions += {
      case keyEvent: KeyPressed =>
        if keyEvent.source == this.input && keyEvent.key == Key.Enter && !this.game.isOver then
          val command = this.input.text.trim
          if command.nonEmpty then
            this.input.text = ""
            this.playTurn(command)
      case ButtonClicked(`startButton`) =>
        // Vaihtaa alkunäytöstä pelin ui:hin
        this.contents = gamePanel
        this.pack()
        this.input.requestFocusInWindow()

    }
    // Menu:
    this.menuBar = new MenuBar:
      contents += new Menu("Program"):
        val quitAction = Action("Quit")( dispose() )
        contents += MenuItem(quitAction)

    // Set up the GUI’s initial state:
    this.title = game.title
    this.updateInfo(this.game.welcomeMessage)
    this.contents = startPanel
    this.updateStatusLabel()
    this.preferredSize = new Dimension(300, 400)
    this.location = Point(50, 50)
    this.minimumSize = Dimension(200, 200)
    this.pack()
    this.input.requestFocusInWindow()

    
    def updateStatusLabel(): Unit =
      val newIcon = player.location match
        case `taafa`         => taafaIcon
        case `smokki`        => smokkiIcon
        case `sus`            => amogus
        case `ok20`          => ok20Icon
        case `dipoli`        => dipoliIcon
        case `knmcdonalds`   => knmcdonaldsIcon
        case `sornainen`     => sornainenIcon
        case `rantasauna`    => rantasaunaIcon
        case `klahtimetro`   => klahtimetroIcon
        case `narnia`        => narniaIcon
        case `abloc`         => ablocIcon
        case `kandi`         => kandiIcon
        case `tuas`          => tuasIcon
        case `ttalo`         => ttaloIcon
        case `designfactory` => designfactoryIcon
        case `otaranta`      => otarantaIcon
        case `piritori`      => piritoriIcon
        case `ttalolunch`          => subiIcon
        case `dipoliravintola`     => dipolilunchIcon
        case `ablocmetro`         => ablocmetroIcon
        case _               => otaniemiIcon
      vaihtuvalabel.icon = newIcon

    def playTurn(command: String) =
      val turnReport = this.game.playTurn(command)
      if this.player.hasQuit then
        this.dispose()
      else
        this.updateInfo(turnReport)
        this.updateStatusLabel()
        this.input.enabled = !this.game.isOver


    def updateInfo(info: String) =
      if !this.game.isOver then
        this.turnOutput.text = info
      else
        this.turnOutput.text = info + "\n\n" + this.game.goodbyeMessage
      this.locationInfo.text = s"Tämänhetkinen sijainti: ${player.location.toString}, kello on: ${game.otaniemi.getTime}\nVoit tutkia aluetta tarkemmin tai liikkua: ${player.location.connections.map(_._1).mkString(", ")}\n\nReppusi sisältää ${player.inventory.mkString(", ")}\n\nTilillä rahaa ${player.getMoneyStatus} euroa"
      this.turnCounter.text = "Turns played: " + this.game.turnCount

  end top

  // apuluokka taustakuvaa varten
  class BackgroundBoxPanel(orientation: Orientation.Value, bg: Image) extends BoxPanel(orientation):
    override def paintComponent(g: Graphics2D): Unit =
      super.paintComponent(g)
      g.drawImage(bg, 0, 0, size.width, size.height, peer)


  // Enable this code to work even under the -language:strictEquality compiler option:
  private given CanEqual[Component, Component] = CanEqual.derived
  private given CanEqual[Key.Value, Key.Value] = CanEqual.derived

end OtameemiGUI
