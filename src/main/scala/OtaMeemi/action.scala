package OtaMeemi

class Action(input: String):
  private val commandText = input.trim.toLowerCase
  private val verb        = commandText.takeWhile( _ != ' ' )
  private val modifiers   = commandText.drop(verb.length).trim

  def execute(actor: Player): Option[String] =
    this.verb match
      case "mene"        => Some(actor.go(this.modifiers))
      case "lepää"      => Some(actor.rest())
      case "lopeta"      => Some(actor.quit())
      case "tutki"   => 
        if this.modifiers.isEmpty then Some(actor.location.examine(actor))
        else Some(actor.examineItem(this.modifiers))
      case "pakene"    => Some(actor.location.escape)
      case "inventory" => Some(actor.inventory.mkString(", "))
      case "käytä"       => Some(actor.useItem(this.modifiers))
      case "amogus"    => Some(actor.sus())
      case "yhdistä"   => Some(actor.combineItems(this.modifiers))
      case "apua"   => Some("Voit koittaa näitä komentoja: mene, lepää, lopeta, tutki, pakene, inventory, käytä, amogus, yhdistä, apua, syö, myy, ota")
      case "syö"    => Some(actor.eatItem(this.modifiers))
      case "myy"      => Some(actor.trade(this.modifiers))
      case "ota"      => Some(actor.takeItem(this.modifiers))
      //case "kalasta" => Some(actor.fish())
      case other       => None
