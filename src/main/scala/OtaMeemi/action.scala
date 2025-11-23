package OtaMeemi

class Action(input: String):
  private val commandText = input.trim.toLowerCase
  private val verb        = commandText.takeWhile( _ != ' ' )
  private val modifiers   = commandText.drop(verb.length).trim

  def execute(actor: Player): Option[String] =
    this.verb match
      case "go"        => Some(actor.go(this.modifiers))
      case "rest"      => Some(actor.rest())
      case "quit"      => Some(actor.quit())
      case "examine"   => 
        if this.modifiers.isEmpty then Some(actor.location.examine(actor))
        else Some(actor.examineItem(this.modifiers))
      case "escape"    => Some(actor.location.escape)
      case "inventory" => Some(actor.inventory.mkString(", "))
      case "use"       => Some(actor.useItem(this.modifiers))
      case "amogus"    => Some(actor.sus())
      case "combine"   => Some(actor.combineItems(this.modifiers))
      case "help"   => Some("Valid commands include but are not limited to: go, rest, quit, examine (item or location), escape, inventory, use (item), combine")
      case "eat"    => Some(actor.eatItem(this.modifiers))
      case "sell"      => Some(actor.trade(this.modifiers))
      case "take"      => Some(actor.takeItem(this.modifiers))
      case other       => None
